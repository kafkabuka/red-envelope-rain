package com.bainianzhi.api.dao;

import com.bainianzhi.common.entity.ActivityPrizesEntity;
import com.bainianzhi.common.entity.vo.ActivityPrizesVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * 活动奖品关联
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Mapper
public interface ActivityPrizesDao extends BaseMapper<ActivityPrizesEntity> {

    // 获取总奖品数
    BigDecimal getTotal(Integer activityId);

    //活动奖品信息
    List<ActivityPrizesVo> queryActivityPrizesByActivityId(Integer activityId);
}
