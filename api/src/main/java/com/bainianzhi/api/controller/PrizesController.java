package com.bainianzhi.api.controller;

import com.bainianzhi.api.service.PrizesService;
import com.bainianzhi.common.entity.PrizesEntity;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 奖品
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@RestController
@RequestMapping("rain/prizes")
public class PrizesController {
    @Autowired
    private PrizesService prizesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = prizesService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取奖品列表
     * @return
     */
    @RequestMapping("/prizes")
    public R prizes(){
        List<PrizesEntity> list = prizesService.list();
        return R.ok().put("prizes", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		PrizesEntity prizes = prizesService.getById(id);

        return R.ok().put("prizes", prizes);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PrizesEntity prizes){
		prizesService.save(prizes);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PrizesEntity prizes){
		prizesService.updateById(prizes);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		prizesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
