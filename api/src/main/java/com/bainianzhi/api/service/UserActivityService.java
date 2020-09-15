package com.bainianzhi.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.entity.UserActivityEntity;

import java.util.Map;

/**
 * 会员活动关联
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
public interface UserActivityService extends IService<UserActivityEntity> {

    PageUtils queryPage(Map<String, Object> params);

    // 用户参与活动数
    Integer getUserActivityNums(Integer userId);

    // 用户中奖数
    Integer getUserPrizesNums(Integer userId);
}

