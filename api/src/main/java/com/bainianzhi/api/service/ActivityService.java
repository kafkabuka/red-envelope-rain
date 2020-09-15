package com.bainianzhi.api.service;

import com.bainianzhi.common.entity.ActivityEntity;
import com.bainianzhi.common.entity.dto.PrizesDto;
import com.bainianzhi.common.utils.PageUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 活动
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
public interface ActivityService extends IService<ActivityEntity> {
    // 查询活动信息
    IPage queryPage(Map<String, Object> params);

    // 查询将来1分钟内要开始的活动
    List<ActivityEntity> getActivities();

    // 查询指定活动关联的所有的奖品信息，还包括奖品数量
    List<PrizesDto> getPrizes(Integer id);

    // 更新活动的状态
    void updateStatus(ActivityEntity activityEntity);

    // 前台-根据状态获取活动分页列表
    PageUtils getActivitiesByStatus(int status, String current, String size);

    // 中奖统计
    PageUtils queryPageByCondition(Map<String, Object> params);
}

