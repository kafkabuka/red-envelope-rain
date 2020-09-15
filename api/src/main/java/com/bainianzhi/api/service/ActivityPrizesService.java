package com.bainianzhi.api.service;

import com.bainianzhi.common.entity.ActivityPrizesEntity;
import com.bainianzhi.common.entity.vo.ActivityPrizesVo;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.RRException;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 活动奖品关联
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
public interface ActivityPrizesService extends IService<ActivityPrizesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    // 根据活动id获取活动与奖品的关联信息
    List<ActivityPrizesEntity> getActivityPrizes(Integer id);

    // 获取总奖品数
    BigDecimal getTotal(Integer activityId);
    // 根据活动id获取活动与奖品的关联信息
    List<ActivityPrizesVo> getActivityPrizes1(Integer id);
    //添加奖品
    void savePrizes(ActivityPrizesEntity activityPrizesEntity) throws RRException;
    //更新奖品
    void updatePrizes(ActivityPrizesEntity activityPrizesEntity) throws RRException;
}

