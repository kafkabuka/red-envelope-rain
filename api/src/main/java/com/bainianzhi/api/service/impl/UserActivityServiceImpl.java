package com.bainianzhi.api.service.impl;

import com.bainianzhi.api.dao.UserActivityDao;
import com.bainianzhi.api.service.UserActivityService;
import com.bainianzhi.common.entity.UserActivityEntity;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userActivityService")
public class UserActivityServiceImpl extends ServiceImpl<UserActivityDao, UserActivityEntity> implements UserActivityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserActivityEntity> page = this.page(
                new Query<UserActivityEntity>().getPage(params),
                new QueryWrapper<UserActivityEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 获取用户参与活动数
     * @param uesrId 用户id
     * @return
     */
    @Override
    public Integer getUserActivityNums(Integer uesrId) {
        return baseMapper.getUserActivityNums(uesrId);
    }

    /**
     * 获取用户中奖数
     * @param userId 用户id
     * @return
     */
    @Override
    public Integer getUserPrizesNums(Integer userId) {
        return baseMapper.getUserPrizesNums(userId);
    }

}