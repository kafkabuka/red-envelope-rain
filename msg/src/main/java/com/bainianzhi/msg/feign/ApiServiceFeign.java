package com.bainianzhi.msg.feign;

import com.bainianzhi.common.entity.UserActivityEntity;
import com.bainianzhi.common.entity.UserHitEntity;
import com.bainianzhi.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("api")
public interface ApiServiceFeign {
    @PostMapping("rain/useractivity/save")
    R saveUserActivity(@RequestBody UserActivityEntity userActivity);

    @PostMapping("rain/userhit/save")
    R saveUserHit(@RequestBody UserHitEntity userHit);
}
