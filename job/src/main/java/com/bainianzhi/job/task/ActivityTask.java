package com.bainianzhi.job.task;

import com.alibaba.fastjson.TypeReference;
import com.bainianzhi.common.config.RedisKeys;
import com.bainianzhi.common.entity.ActivityEntity;
import com.bainianzhi.common.entity.ActivityPrizesEntity;
import com.bainianzhi.common.entity.ActivityRulesEntity;
import com.bainianzhi.common.entity.PrizesEntity;
import com.bainianzhi.common.entity.dto.PrizesDto;
import com.bainianzhi.common.utils.R;
import com.bainianzhi.common.utils.RedisUtil;
import com.bainianzhi.job.annotation.ElasticSimpleJob;
import com.bainianzhi.job.dao.ActivityDao;
import com.bainianzhi.job.feign.ApiServiceFeign;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 活动信息预热，每隔1分钟执行一次
 * 查找未来1分钟内（含），要开始的活动
 * 活动信息包括活动基本信息，活动奖品信息，活动策略信息
 */
@Component
@ElasticSimpleJob(cron = "0 * * * * ?")
@Slf4j
public class ActivityTask implements SimpleJob {

    @Autowired
    private ApiServiceFeign apiServiceFeign;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ActivityDao activityDao;

    @Override
    public void execute(ShardingContext shardingContext) {
        //1、获取当前时间
        Date now = new Date();
        //2、构造查询条件
        QueryWrapper<ActivityEntity> wrapper = new QueryWrapper<>();
        wrapper.gt("starttime",now);
        wrapper.le("starttime", DateUtils.addMinutes(now,1));
        //3、查询
        List<ActivityEntity> activities = activityDao.selectList(wrapper);
        // 1、调用远程活动接口服务查询未来1分钟内（包含1分钟）即将开始的活动
//       R r = apiServiceFeign.getActivities();
//        List<ActivityEntity> activities = r.getData(new TypeReference<List<ActivityEntity>>() {
//        });
        // 不存在即将开始的活动，日志记录并直接返回
        if(activities.size() == 0) {
            log.info("没有即将开始的活动");
            return;
        }
        // 日志记录
        log.info("有{}个活动即将开始",activities.size());

        // 2、存在即将开始的活动，循环遍历，预热活动信息
        activities.forEach(activity -> {
            // 活动开始时间,单位为毫秒
            long start = activity.getStarttime().getTime();
            // 活动结束时间,单位为毫秒
            long end = activity.getEndtime().getTime();
            // 计算活动结束时间到现在还有多少秒，并将其作为redis key 的过期时间,单位为秒
            long expire = (end - now.getTime()) / 1000;
            // 活动持续时间,单位为毫秒
            long duration = end -start;
            // 2.1、预热活动基本信息
            // 修改活动状态
            activity.setStatus(1);
            // 保存活动基本信息到redis，并设置永不过期
            redisUtil.set(RedisKeys.INFO + activity.getId(),activity,-1);
            log.info("活动基本信息，活动id：{}，活动标题：{}，活动开始时间：{}，活动结束时间：{}",
                    activity.getId(),activity.getTitle(),activity.getStarttime(),activity.getEndtime());

            // 2.2、预热活动奖品信息（活动关联的奖品信息及其数量）
            // 调用远程接口查询活动关联的奖品信息
            R prizes = apiServiceFeign.getPrizes(activity.getId());
            List<PrizesDto> prizesDtoList = prizes.getData(new TypeReference<List<PrizesDto>>() {
            });
            // 将查询到奖品List集合转为Map，key为奖品的id，value为奖品包装类信息（包含奖品基本信息和奖品数量）
            Map<Integer,PrizesEntity> prizesEntityMap = new HashMap<>(prizesDtoList.size());
            prizesDtoList.forEach(prizesDto -> {
                prizesEntityMap.put(prizesDto.getId(),prizesDto);
                log.info("活动关联的奖品数量：{}，奖品名称：{}，奖品简介：{}，奖品市场价：{}，奖品数量：{}",
                        prizesDtoList.size(),prizesDto.getName(),prizesDto.getInfo(),prizesDto.getPrice(),prizesDto.getAmount());
            });

            // 活动奖品关联信息
            R activityPrizes = apiServiceFeign.getActivityPrizes(activity.getId());
            List<ActivityPrizesEntity> activityPrizesList = activityPrizes.getData(new TypeReference<List<ActivityPrizesEntity>>() {
            });
            // 令牌桶
            List<Long> tokenList = new ArrayList<>();
            activityPrizesList.forEach(activityPrizesEntity -> {
                // 生成amount个start到end之间的随机时间戳做令牌
                for (int i = 0; i < activityPrizesEntity.getAmount(); i++) {
                    long rnd = start + new Random().nextInt((int)duration);
                    // rnd乘以1000再加上一个随机数，防止活动时间段内奖品过多时令牌重复
                    long token = rnd * 1000 + new Random().nextInt(999);
                    // 将令牌放入令牌桶
                    tokenList.add(token);
                    // 以令牌做key，对应的奖品做value，放入redis缓存,使token与实际奖品之间建立映射关系
                    redisUtil.set(RedisKeys.TOKEN + activity.getId() + "_" + token,
                            prizesEntityMap.get(activityPrizesEntity.getPrizesid()),expire);
                    log.info("令牌桶 -> 活动 : {} -> {}",token/1000 ,prizesEntityMap.get(activityPrizesEntity.getPrizesid()).getName());
                }
            });

            // 对令牌桶进行排序后放入redis队列
            Collections.sort(tokenList);
            log.info("令牌桶信息：{}",tokenList);
            // 从右侧压入队列，从左到右时间戳逐渐增大
            redisUtil.rightPushAll(RedisKeys.TOKENS + activity.getId(),tokenList);
            // 令牌设置过期时间
            redisUtil.expire(RedisKeys.TOKENS + activity.getId(),expire);

            // 2.3、预热活动奖品策略配置信息
            R activityRules = apiServiceFeign.getActivityRules(activity.getId());
            List<ActivityRulesEntity> activityRulesEntityList = activityRules.getData(new TypeReference<List<ActivityRulesEntity>>() {
            });
            // 遍历策略，放入redis hset中
            activityRulesEntityList.forEach(activityRulesEntity -> {
                // 最大可抽奖次数
                redisUtil.hset(RedisKeys.MAX_ENTER_TIMES + activity.getId(),activityRulesEntity.getUserlevel().toString(),activityRulesEntity.getEnterTimes());
                // 最大中奖次数
                redisUtil.hset(RedisKeys.MAX_HIT_TIMES + activity.getId(),activityRulesEntity.getUserlevel().toString(),activityRulesEntity.getGoalTimes());
                // 日志记录活动奖品策略信息
                log.info("活动奖品策略信息：用户等级：{}，最大可抽奖次数：{}，最大中奖次数：{}",
                        activityRulesEntity.getUserlevel(),activityRulesEntity.getEnterTimes(),activityRulesEntity.getGoalTimes());
            });
            // 活动奖品策略设置过期时间
            redisUtil.expire(RedisKeys.MAX_ENTER_TIMES + activity.getId(),expire);
            redisUtil.expire(RedisKeys.MAX_HIT_TIMES + activity.getId(),expire);

            // 更新数据库中活动的状态
            apiServiceFeign.updateStatus(activity);

        });
    }
}
