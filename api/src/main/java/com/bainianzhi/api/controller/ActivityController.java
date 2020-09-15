package com.bainianzhi.api.controller;

import com.bainianzhi.api.service.ActivityService;
import com.bainianzhi.common.entity.ActivityEntity;
import com.bainianzhi.common.entity.dto.PrizesDto;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 活动
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@RestController
@RequestMapping("rain/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    /**
     * 中奖统计
     */
    @RequestMapping("/hit/count")
    public R hitCount(@RequestParam Map<String, Object> params){
        PageUtils page = activityService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }

    /**
     * 前台-根据状态获取活动分页列表
     * @param status 活动状态 -1 全部，0 未开始，1 进行中，2 已结束
     * @param current 当前页
     * @param size 每页记录数
     * @return
     */
    @GetMapping("/list/{status}/{current}/{size}")
    public R getActivitiesByStatus(@PathVariable int status, @PathVariable String current,@PathVariable String size) {
        PageUtils page = activityService.getActivitiesByStatus(status,current,size);
        return R.ok("成功").setData(page);
    }

    /**
     * 更新活动的状态
     * @return
     */
    @PostMapping("/update/status")
    public R updateStatus(@RequestBody ActivityEntity activityEntity) {
        activityService.updateStatus(activityEntity);
        return R.ok();
    }

    /**
     * 查询指定活动关联的所有的奖品信息，还包括奖品数量
     * @param id 活动id
     * @return
     */
    @RequestMapping("/prizes/{id}")
    public R getPrizes(@PathVariable("id") Integer id) {
        List<PrizesDto> prizesDtoList = activityService.getPrizes(id);
        return R.ok().setData(prizesDtoList);
    }

    /**
     * 查询将来1分钟内要开始的活动
     * @return
     */
    @PostMapping("/future/list")
    public R getActivities() {
        List<ActivityEntity> activities = activityService.getActivities();
        return R.ok().setData(activities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok().put("page", activityService.queryPage(params));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		ActivityEntity activity = activityService.getById(id);

        return R.ok().put("activity", activity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ActivityEntity activity){
		activityService.save(activity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ActivityEntity activity){
		activityService.updateById(activity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		activityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
