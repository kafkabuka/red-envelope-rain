package com.bainianzhi.api.controller;

import com.bainianzhi.api.service.UserActivityService;
import com.bainianzhi.api.service.UserHitService;
import com.bainianzhi.api.service.UserService;
import com.bainianzhi.common.config.RedisKeys;
import com.bainianzhi.common.entity.UserEntity;
import com.bainianzhi.common.entity.dto.UserDto;
import com.bainianzhi.common.entity.dto.UserHitDto;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.R;
import com.bainianzhi.common.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 会员
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@RestController
@RequestMapping("rain/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private UserHitService userHitService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 我的奖品
     * @return
     */
    @GetMapping("/myprizes")
    public R getMyPrizes() {
        // 判断用户是否登录超时
        UserEntity user = (UserEntity) redisUtil.get(RedisKeys.SESSIONID);
        if(user == null) {
            // 登录超时，返回提示消息
            return R.error(-1,"登录超时");
        }
        // 未登录超时，查询获取用户所中的奖品
        List<UserHitDto> prizes = userHitService.getMyPrizes(user.getId());
        return R.ok().setData(prizes);
    }

    /**
     * 用户个人中心，包括基本信息，参与活动数，中奖数
     * @return
     */
    @GetMapping("/info")
    public R getUserInfo() {
        // 从redis中获取用户基本信息
        UserEntity user = (UserEntity) redisUtil.get(RedisKeys.SESSIONID);
        // 判断是否登录超时
        if(user == null) {
            // 登录超时
            return R.error(-1,"登录超时");
        } else {
            // 登录未超时
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
            // 获取用户参与活动数
            userDto.setActivities(userActivityService.getUserActivityNums(user.getId()));
            // 获取用户中奖数
            userDto.setPrizes(userActivityService.getUserPrizesNums(user.getId()));
            return R.ok().setData(userDto);
        }
    }

    /**
     * 用户退出
     * @return
     */
    @GetMapping("/logout")
    public R logout() {
        redisUtil.del(RedisKeys.SESSIONID);
        return R.ok("退出成功！");
    }

    /**
     * 用户登录
     * @param uname 用户名
     * @param passwd 密码
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestParam String uname, @RequestParam String passwd) {
        // 获取用户登录错误次数
        Integer errortimes = (Integer) redisUtil.get(RedisKeys.USER_LOGIN_ERROR_TIMES + uname);
        // 错误次数判断，超过5次给出提示消息
        if(errortimes != null && errortimes >= 5) {
            return R.error(-1,"密码错误5次，请五分钟后再试！");
        }
        // 根据用户名和密码查询用户
        UserEntity user = userService.login(uname,passwd);
        if(user != null) { // 查询出数据，登录成功
            // 过滤敏感信息
            user.setIdcard(null);
            user.setPasswd(null);
            // 用户信息保存到redis中
            redisUtil.set(RedisKeys.SESSIONID,user,3 * 60 * 60);
            return R.ok("登录成功！").setData(user);
        } else { // 为查询出数据，登录失败
            // 失败错误次数计数，达到5次锁定5分钟
            redisUtil.incr(RedisKeys.USER_LOGIN_ERROR_TIMES + uname,1);
            redisUtil.expire(RedisKeys.USER_LOGIN_ERROR_TIMES + uname,5 * 60);
            return R.error(-1,"用户名或密码错误");
        }
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
