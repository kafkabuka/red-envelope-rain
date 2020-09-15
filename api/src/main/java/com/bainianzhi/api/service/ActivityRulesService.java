package com.bainianzhi.api.service;

import com.bainianzhi.common.entity.ActivityRulesEntity;
import com.bainianzhi.common.entity.vo.ActivityRulesVo;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.RRException;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 活动策略
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
public interface ActivityRulesService extends IService<ActivityRulesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    // 根据活动id获取活动奖品策略配置信息
    List<ActivityRulesEntity> getActivityRules(Integer id);


    // 查询活动下的抽奖策略
    List<ActivityRulesVo> getActivityRules1(Integer id);

    // 添加活动策略
    void saveRules(ActivityRulesEntity activityRulesEntity) throws RRException;

    // 更新活动策略
    void updateRules(ActivityRulesEntity activityRulesEntity) throws RRException;
}

