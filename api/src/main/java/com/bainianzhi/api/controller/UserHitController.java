package com.bainianzhi.api.controller;

import com.bainianzhi.api.service.UserHitService;
import com.bainianzhi.common.entity.UserHitEntity;
import com.bainianzhi.common.entity.dto.HitDto;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.R;
import com.bainianzhi.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 会员中奖记录
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@RestController
@RequestMapping("rain/userhit")
public class UserHitController {
    @Autowired
    private UserHitService userHitService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 前台中奖列表
     * @param activityid 活动id
     * @retur   */
    @GetMapping("/hit/{activityid}")
    public R getHitList(@PathVariable int activityid) {
        List<HitDto> hits = userHitService.hit(activityid);
        return R.ok().setData(hits);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userHitService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserHitEntity userHit = userHitService.getById(id);

        return R.ok().put("userHit", userHit);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody UserHitEntity userHit){
		userHitService.save(userHit);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserHitEntity userHit){
		userHitService.updateById(userHit);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userHitService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
