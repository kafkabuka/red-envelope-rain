package com.bainianzhi.api.service;

import com.bainianzhi.common.entity.UserHitEntity;
import com.bainianzhi.common.entity.dto.HitDto;
import com.bainianzhi.common.entity.dto.UserHitDto;
import com.bainianzhi.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 会员中奖记录
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
public interface UserHitService extends IService<UserHitEntity> {

    PageUtils queryPage(Map<String, Object> params);

    // 中奖列表
    List<HitDto> hit(int activityid);

    // 我的奖品
    List<UserHitDto> getMyPrizes(Integer userId);

    // 获取奖品已抽中数
    Long getHits(Integer activityId);
}

