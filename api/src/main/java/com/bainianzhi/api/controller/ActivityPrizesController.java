package com.bainianzhi.api.controller;

import com.bainianzhi.api.service.ActivityPrizesService;
import com.bainianzhi.common.entity.ActivityPrizesEntity;
import com.bainianzhi.common.entity.vo.ActivityPrizesVo;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.R;
import com.bainianzhi.common.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 活动奖品关联
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@RestController
@RequestMapping("rain/activityprizes")
public class ActivityPrizesController {
    @Autowired
    private ActivityPrizesService activityPrizesService;

    /**
     * 根据活动id获取活动与奖品的关联信息
     * @param id 活动id
     * @return
     */
    @RequestMapping("/list/{id}")
    public R getActivityPrizes(@PathVariable("id") Integer id) {
        List<ActivityPrizesEntity> activityPrizesEntities =  activityPrizesService.getActivityPrizes(id);
        return R.ok().setData(activityPrizesEntities);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = activityPrizesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		ActivityPrizesEntity activityPrizes = activityPrizesService.getById(id);

        return R.ok().put("activityPrizes", activityPrizes);
    }

    /**
     * 列表
     * @param activityId
     * @return
     */
    @RequestMapping("/getActivityPrizes")
    public R getActivityPrizes1(@RequestParam Integer activityId){
        List<ActivityPrizesVo> activityPrizes = activityPrizesService.getActivityPrizes1(activityId);
        return R.ok().put("activityPrizes", activityPrizes);
    }

    /**
     * 保存
     * @param activityPrizes
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody ActivityPrizesEntity activityPrizes){
        try {
            activityPrizesService.savePrizes(activityPrizes);
            return R.ok();
        } catch (RRException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 修改
     * @param activityPrizes
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody ActivityPrizesEntity activityPrizes){
        try {
            activityPrizesService.updatePrizes(activityPrizes);
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
		activityPrizesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
