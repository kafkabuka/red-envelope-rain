package com.bainianzhi.api.service.impl;

import com.bainianzhi.api.dao.UserHitDao;
import com.bainianzhi.api.service.UserHitService;
import com.bainianzhi.common.entity.UserHitEntity;
import com.bainianzhi.common.entity.dto.HitDto;
import com.bainianzhi.common.entity.dto.UserHitDto;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userHitService")
public class UserHitServiceImpl extends ServiceImpl<UserHitDao, UserHitEntity> implements UserHitService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserHitEntity> page = this.page(
                new Query<UserHitEntity>().getPage(params),
                new QueryWrapper<UserHitEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 我的奖品
     * @param userId 用户id
     * @return
     */
    @Override
    public List<UserHitDto> getMyPrizes(Integer userId) {
        return baseMapper.getMyPrizes(userId);
    }

    // 获取奖品已抽中数
    @Override
    public Long getHits(Integer activityId) {
        return baseMapper.getHit(activityId);
    }

    /**
     * 中奖列表
     * @param activityid 活动id
     * @return
     */
    @Override
    public List<HitDto> hit(int activityid) {
        return baseMapper.hit(activityid);
    }

}