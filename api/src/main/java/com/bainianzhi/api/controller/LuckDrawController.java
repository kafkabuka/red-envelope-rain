package com.bainianzhi.api.controller;

import com.bainianzhi.api.config.LuaScript;
import com.bainianzhi.common.config.RabbitKeys;
import com.bainianzhi.common.config.RedisKeys;
import com.bainianzhi.common.entity.*;
import com.bainianzhi.common.utils.R;
import com.bainianzhi.common.utils.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 抽奖控制器
 */
@RestController
@RequestMapping("rain/luckdraw")
public class LuckDrawController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private LuaScript luaScript;

    /**
     * 参与抽奖
     * @param activityid 活动id
     * @return
     */
    @GetMapping("/go/{activityid}")
    public R luckDraw(@PathVariable Integer activityid, HttpServletRequest request) {
        /**
         * 一、信息校验
         */
        Date now = new Date();
        // 从redis中获取活动基本信息
        ActivityEntity activity = (ActivityEntity)redisUtil.get(RedisKeys.INFO + activityid);
        /**
         * 判断活动是否开始
         * 1、如果活动信息未加载进redis，无效
         * 2、如果活动预热完成，但是开始时间 > 当前时间，无效
         */
        if(activity == null || activity.getStarttime().after(now)) {
            return R.error(-1,"活动未开始！");
        }
        // 判断活动是否结束
        if(activity.getEndtime().before(now)) {
            return R.error(-1,"活动已结束!");
        }
        // 获取当前用户
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)redisUtil.get(RedisKeys.SESSIONID);
        // 判断用户是否登录
        if(user == null) { // 未登录
            // 给出提示消息
            return R.error(-1,"你还没有登录，请登录后重试");
        } else { // 已登录
            // 判断用户是否已参加该活动
            if(!redisUtil.hasKey(RedisKeys.USER_ACTIVITY + user.getId() + "_" + activityid)) {
                // 未参加
                redisUtil.set(RedisKeys.USER_ACTIVITY + user.getId() + "_" + activityid,
                        1,(activity.getEndtime().getTime() - now.getTime()) / 1000);
                // 持久化用户抽奖记录，使用消息队列处理
                UserActivityEntity userActivityEntity = new UserActivityEntity(null,user.getId(),activityid,new Date());
                rabbitTemplate.convertAndSend(RabbitKeys.QUEUE_PLAY,userActivityEntity);
            }
        }
        // 用户已中奖次数
        Integer count = (Integer) redisUtil.get(RedisKeys.USER_HIT + activityid + "_" + user.getId());
        if(count == null) {
            // 从未中奖，设置初始值为0
            count = 0;
            // 将用户中奖次数保存到redis中
            redisUtil.set(RedisKeys.USER_HIT + activityid + "_" + user.getId(),
                    count,
                    (activity.getEndtime().getTime() - now.getTime()) / 1000);
        }
        // 根据用户的等级，获取本次活动允许的最大中奖次数
        Integer maxhit = (Integer) redisUtil.hget(RedisKeys.MAX_HIT_TIMES + activityid, user.getLevel().toString());
        // 如果未设置，默认为0，代表最大中奖次数无限制
        maxhit = maxhit==null ? 0 : maxhit;
        // 次数对比
        if(maxhit != 0 && count >= maxhit) {
            // 达到最大数，不允许继续抽奖
            return R.error(-1,"你已达到最大中奖数！");
        }

        /**
         * 二、校验通过，参与抽奖
         */
        /*// 从最左边取出令牌桶中的数据
        Long token = (Long) redisUtil.leftPop(RedisKeys.TOKENS + activityid);
        if(token == null) {
            // 令牌桶为空，说明奖品已经全都抢光了
            return R.error(-1,"奖品已抢光！");
        }
        // 判断令牌时间戳大小，即是否中奖，但要注意：取出的令牌要除以1000
        if(now.getTime() < token/1000) {
            // 当前时间戳小于令牌时间戳，说明奖品未到发放时间点，放回令牌，提示未中奖
            redisUtil.leftPush(RedisKeys.TOKENS + activityid,token);
            return R.error(0,"未中奖！");
        }*/
        // 保证上述操作为原子操作，使用lua脚本，否则高并发情况下会出问题
        Long token = luaScript.tokenCheck(RedisKeys.TOKENS + activityid, String.valueOf(now.getTime()));
        if(token == 0) {
            return R.error(-1,"奖品已抢光！");
        } else if(token == 1) {
            return R.error(-1,"未中奖！");
        }

        /**
         * 三、已中奖
         */
        // 获取抽中的奖品
        PrizesEntity prizes = (PrizesEntity) redisUtil.get(RedisKeys.TOKEN + activityid + "_" + token);
        // 中奖次数加1
        redisUtil.incr(RedisKeys.USER_HIT + activityid + "_" + user.getId(),1);
        // 消息队列处理用户中奖记录
        UserHitEntity userHit = new UserHitEntity(null,activityid,user.getId(),prizes.getId(),now);
        rabbitTemplate.convertAndSend(RabbitKeys.EXCHANGE_DIRECT,RabbitKeys.QUEUE_HIT,userHit);

        // 返回中奖信息
        return R.ok("恭喜，中奖了！").setData(prizes);
    }

}
