package com.bainianzhi.api.dao;

import com.bainianzhi.common.entity.ActivityRulesEntity;
import com.bainianzhi.common.entity.vo.ActivityRulesVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 活动策略
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Mapper
public interface ActivityRulesDao extends BaseMapper<ActivityRulesEntity> {

    //活动规则信息
    List<ActivityRulesVo> queryActivityRulesByActivityId(Integer activityId);
}
