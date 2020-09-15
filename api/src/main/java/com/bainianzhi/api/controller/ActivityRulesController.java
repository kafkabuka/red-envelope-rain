package com.bainianzhi.api.controller;

import com.bainianzhi.api.service.ActivityRulesService;
import com.bainianzhi.common.entity.ActivityRulesEntity;
import com.bainianzhi.common.entity.vo.ActivityRulesVo;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.R;
import com.bainianzhi.common.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 活动策略
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@RestController
@RequestMapping("rain/activityrules")
public class ActivityRulesController {
    @Autowired
    private ActivityRulesService activityRulesService;

    /**
     * 根据活动id获取活动奖品策略配置信息
     * @param id 活动id
     * @return
     */
    @RequestMapping("/list/{id}")
    public R getActivityRules(@PathVariable("id") Integer id) {
        List<ActivityRulesEntity> activityRulesEntities =  activityRulesService.getActivityRules(id);
        return R.ok().setData(activityRulesEntities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = activityRulesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		ActivityRulesEntity activityRules = activityRulesService.getById(id);

        return R.ok().put("activityRules", activityRules);
    }

    /**
     * 列表
     * @param activityId
     * @return
     */
    @RequestMapping("/getActivityRules")
    public R getActivityRules1(@RequestParam Integer activityId){
        List<ActivityRulesVo> activityRules = activityRulesService.getActivityRules1(activityId);
        return R.ok().put("activityRules", activityRules);
    }

    /**
     * 保存
     * @param activityRules
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody ActivityRulesEntity activityRules){
        try {
            activityRulesService.saveRules(activityRules);
            return R.ok();
        } catch (RRException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 修改
     * @param activityRules
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody ActivityRulesEntity activityRules){
        try {
            activityRulesService.updateRules(activityRules);
            return R.ok();
        } catch (RRException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		activityRulesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
