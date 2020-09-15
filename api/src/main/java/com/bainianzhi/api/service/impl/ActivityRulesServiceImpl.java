package com.bainianzhi.api.service.impl;

import com.bainianzhi.api.dao.ActivityRulesDao;
import com.bainianzhi.api.service.ActivityRulesService;
import com.bainianzhi.common.entity.ActivityRulesEntity;
import com.bainianzhi.common.entity.vo.ActivityRulesVo;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.Query;
import com.bainianzhi.common.utils.RRException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("activityRulesService")
public class ActivityRulesServiceImpl extends ServiceImpl<ActivityRulesDao, ActivityRulesEntity> implements ActivityRulesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ActivityRulesEntity> page = this.page(
                new Query<ActivityRulesEntity>().getPage(params),
                new QueryWrapper<ActivityRulesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ActivityRulesEntity> getActivityRules(Integer id) {
        return baseMapper.selectList(new QueryWrapper<ActivityRulesEntity>().eq("activityid",id));
    }

    @Override
    public List<ActivityRulesVo> getActivityRules1(Integer id) {
        return baseMapper.queryActivityRulesByActivityId(id);
    }

    @Override
    public void saveRules(ActivityRulesEntity activityRulesEntity) throws RRException {
        QueryWrapper<ActivityRulesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activityid",activityRulesEntity.getActivityid());
        queryWrapper.eq("userlevel",activityRulesEntity.getUserlevel());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count>0){
            throw new RRException("该活动已存在此策略");
        }
        baseMapper.insert(activityRulesEntity);
    }

    @Override
    public void updateRules(ActivityRulesEntity activityRulesEntity) throws RRException {
        QueryWrapper<ActivityRulesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activityid",activityRulesEntity.getActivityid());
        queryWrapper.eq("userlevel",activityRulesEntity.getUserlevel());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count>0){
            throw new RRException("该活动已存在此策略");
        }
        baseMapper.updateById(activityRulesEntity);
    }

}