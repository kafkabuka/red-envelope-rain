package com.bainianzhi.api.dao;

import com.bainianzhi.common.entity.UserHitEntity;
import com.bainianzhi.common.entity.dto.HitDto;
import com.bainianzhi.common.entity.dto.UserHitDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员中奖记录
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Mapper
public interface UserHitDao extends BaseMapper<UserHitEntity> {

    // 获取用户中奖记录
    List<UserHitDto> getMyPrizes(@Param("userId") Integer userId);

    // 中奖列表
    List<HitDto> hit(@Param("activityid") int activityid);

    // 获取奖品已抽中数
    Long getHit(Integer activityId);
}
