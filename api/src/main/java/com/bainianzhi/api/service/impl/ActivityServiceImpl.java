package com.bainianzhi.api.service.impl;

import com.bainianzhi.api.dao.ActivityDao;
import com.bainianzhi.api.service.ActivityPrizesService;
import com.bainianzhi.api.service.ActivityService;
import com.bainianzhi.api.service.SysDictService;
import com.bainianzhi.api.service.UserHitService;
import com.bainianzhi.common.entity.ActivityEntity;
import com.bainianzhi.common.entity.dto.ActivityHitDto;
import com.bainianzhi.common.entity.dto.PrizesDto;
import com.bainianzhi.common.entity.vo.ActivityVo;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("activityService")
public class ActivityServiceImpl extends ServiceImpl<ActivityDao, ActivityEntity> implements ActivityService {

    @Autowired
    private UserHitService userHitService;

    @Autowired
    private ActivityPrizesService activityPrizesService;

    @Autowired
    private SysDictService sysDictService;


    @Override
    public IPage queryPage(Map<String, Object> params) {
        long current = 1L;
        long size = 10L;
        if (params.get("page") != null){
            current = Long.parseLong(params.get("page").toString());
        }
        if(params.get("size") != null){
            size = Long.parseLong(params.get("size").toString());
        }
        Page<ActivityVo> page = new Page<ActivityVo>(current, size);
        baseMapper.findActivityByQueryVo1(page, params);
        return page;
    }

    /**
     * 查询将来1分钟内要开始的活动
     * @return
     */
    @Override
    public List<ActivityEntity> getActivities() {
        //1、获取当前时间
        Date now = new Date();
        //2、构造查询条件
        QueryWrapper<ActivityEntity> wrapper = new QueryWrapper<>();
        wrapper.gt("starttime",now);
        wrapper.le("starttime", DateUtils.addMinutes(now,1));
        //3、查询
        return baseMapper.selectList(wrapper);
    }

    /**
     * 查询指定活动关联的所有的奖品信息，还包括奖品数量
     * @param id 活动id
     * @return
     */
    @Override
    public List<PrizesDto> getPrizes(Integer id) {
        return baseMapper.getPrizes(id);
    }

    /**
     * 更新活动的状态
     * @param
     */
    @Override
    public void updateStatus(ActivityEntity activityEntity) {
        baseMapper.updateById(activityEntity);
    }

    /**
     * 前台-根据状态获取活动分页列表
     * @param status 活动状态 -1 全部，0 未开始，1 进行中，2 已结束
     * @param current 当前页
     * @param size 每页记录数
     * @return
     */
    @Override
    public PageUtils getActivitiesByStatus(int status, String current, String size) {
        // 0、获取当前时间
        Date now = new Date();
        // 1、构造分页参数
        Map<String, Object> params = new HashMap<>();
        params.put("page",current);
        params.put("limit",size);
        // 2、构造查询条件
        QueryWrapper<ActivityEntity> wrapper = new QueryWrapper<>();
        switch (status) {
            case -1:
                // 查询全部活动
                break;
            case 0:
                // 查询未开始活动
                wrapper.gt("starttime",now);
                break;
            case 1:
                // 查询进行中活动
                wrapper.le("starttime",now).ge("endtime",now);
                break;
            case 2:
                // 查询已结束活动
                wrapper.lt("endtime",now);
                break;
        }
        // 按活动开始时间倒叙排序
        wrapper.orderByDesc("starttime");

        IPage<ActivityEntity> page = this.page(
                new Query<ActivityEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    /**
     * 中奖统计
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<ActivityEntity> wrapper = new QueryWrapper<>();

        //拼接活动主题
        String title = (String) params.get("title");
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }

        //拼接活动开始时间的开始时间
        String begin = (String) params.get("begin");
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("starttime",begin);
        }

        //拼接活动开始时间的结束时间
        String end = (String) params.get("end");
        if(!StringUtils.isEmpty(end)  ){
            wrapper.le("starttime",end);
        }

        IPage<ActivityEntity> page = this.page(
                new Query<ActivityEntity>().getPage(params),
                wrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<ActivityEntity> records = page.getRecords();
        // 封装数据
        List<ActivityHitDto> activityHitDtoList = records.stream().map(activity -> {
            ActivityHitDto activityHitDto = new ActivityHitDto();
            BeanUtils.copyProperties(activity,activityHitDto);
            // 获取奖品已抽中数
            Long hit = userHitService.getHits(activity.getId());
            activityHitDto.setHit(hit);
            // 获取总奖品数
            BigDecimal total = activityPrizesService.getTotal(activity.getId());
            activityHitDto.setTotal(total);
            // 获取活动类型名称
            String typeName = sysDictService.getTypeName(activity.getType());
            activityHitDto.setTypeName(typeName);
            return activityHitDto;
        }).collect(Collectors.toList());
        pageUtils.setList(activityHitDtoList);
        return pageUtils;

    }

}