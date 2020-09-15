package com.bainianzhi.job.feign;

import com.bainianzhi.common.entity.ActivityEntity;
import com.bainianzhi.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("api")
public interface ApiServiceFeign {

    @PostMapping("rain/activity/future/list")
    public R getActivities();

    @RequestMapping("rain/activity/prizes/{id}")
    public R getPrizes(@PathVariable("id") Integer id);

    @PostMapping("rain/activity/update/status")
    public R updateStatus(@RequestBody ActivityEntity activityEntity);

    @RequestMapping("rain/activityprizes/list/{id}")
    public R getActivityPrizes(@PathVariable("id") Integer id);

    @RequestMapping("rain/activityrules/list/{id}")
    public R getActivityRules(@PathVariable("id") Integer id);

}
