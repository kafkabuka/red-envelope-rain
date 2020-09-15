package com.bainianzhi.api.dao;

import com.bainianzhi.common.entity.UserActivityEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员活动关联
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Mapper
public interface UserActivityDao extends BaseMapper<UserActivityEntity> {

    // 获取用户参与活动数
    Integer getUserActivityNums(Integer uesrId);

    // 获取用户中奖数
    Integer getUserPrizesNums(Integer userId);

}
