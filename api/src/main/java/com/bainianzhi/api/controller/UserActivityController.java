package com.bainianzhi.api.controller;

import com.bainianzhi.common.entity.UserActivityEntity;
import com.bainianzhi.api.service.UserActivityService;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 会员活动关联
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@RestController
@RequestMapping("rain/useractivity")
public class UserActivityController {
    @Autowired
    private UserActivityService userActivityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userActivityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserActivityEntity userActivity = userActivityService.getById(id);

        return R.ok().put("userActivity", userActivity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody UserActivityEntity userActivity){
		userActivityService.save(userActivity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserActivityEntity userActivity){
		userActivityService.updateById(userActivity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userActivityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
