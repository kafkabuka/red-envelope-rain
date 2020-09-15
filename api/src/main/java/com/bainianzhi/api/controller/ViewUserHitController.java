package com.bainianzhi.api.controller;

import com.bainianzhi.api.service.ViewUserHitService;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;



/**
 * 后台中奖列表
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@RestController
@RequestMapping("rain/viewuserhit")
public class ViewUserHitController {
    @Autowired
    private ViewUserHitService viewUserHitService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = viewUserHitService.queryPage(params);

        return R.ok().put("page", page);
    }
}
