package com.bainianzhi.api.service.impl;

import com.bainianzhi.api.dao.ActivityPrizesDao;
import com.bainianzhi.api.service.ActivityPrizesService;
import com.bainianzhi.common.entity.ActivityPrizesEntity;
import com.bainianzhi.common.entity.vo.ActivityPrizesVo;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.Query;
import com.bainianzhi.common.utils.RRException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service("activityPrizesService")
public class ActivityPrizesServiceImpl extends ServiceImpl<ActivityPrizesDao, ActivityPrizesEntity> implements ActivityPrizesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ActivityPrizesEntity> page = this.page(
                new Query<ActivityPrizesEntity>().getPage(params),
                new QueryWrapper<ActivityPrizesEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据活动id获取活动与奖品的关联信息
     * @param id 活动id
     * @return
     */
    @Override
    public List<ActivityPrizesEntity> getActivityPrizes(Integer id) {
        return baseMapper.selectList(new QueryWrapper<ActivityPrizesEntity>().eq("activityid",id));
    }

    // 获取总奖品数
    @Override
    public BigDecimal getTotal(Integer activityId) {
        return baseMapper.getTotal(activityId);
    }

    @Override
    public List<ActivityPrizesVo> getActivityPrizes1(Integer id){
        return baseMapper.queryActivityPrizesByActivityId(id);
    }

    @Override
    public void savePrizes(ActivityPrizesEntity activityPrizesEntity) throws RRException {
        QueryWrapper<ActivityPrizesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activityid",activityPrizesEntity.getActivityid());
        queryWrapper.eq("prizesid",activityPrizesEntity.getPrizesid());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count>0){
            throw new RRException("该活动已存在此奖品");
        }
        baseMapper.insert(activityPrizesEntity);
    }

    @Override
    public void updatePrizes(ActivityPrizesEntity activityPrizesEntity) throws RRException {
        baseMapper.updateById(activityPrizesEntity);
    }

}