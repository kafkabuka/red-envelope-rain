package com.bainianzhi.api.controller;

import com.bainianzhi.common.entity.SysDictEntity;
import com.bainianzhi.api.service.SysDictService;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 数据字典
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@RestController
@RequestMapping("rain/sysdict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 用户等级列表
     * @return
     */
    @GetMapping("/level/list")
    public R getUserLevelList() {
        List<SysDictEntity> levels = sysDictService.getUserLevelList();
        return R.ok().put("levels",levels);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysDictService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 查询列表
     * @param params
     * @return
     */
    @GetMapping("/findQuery")
    public R findQuery(@RequestParam Map<String, Object> params){
        List<SysDictEntity> type = sysDictService.querySysDict(params.get("dict_type").toString());
        return R.ok().put("dict", type);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		SysDictEntity sysDict = sysDictService.getById(id);

        return R.ok().put("sysDict", sysDict);
    }

    /**
     * 保存用户等级
     */
    @RequestMapping("/save")
    public R saveLevel(@RequestBody SysDictEntity sysDict){
        sysDictService.saveLevel(sysDict);

        return R.ok();
    }

//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    public R save(@RequestBody SysDictEntity sysDict){
//		sysDictService.save(sysDict);
//
//        return R.ok();
//    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysDictEntity sysDict){
		sysDictService.updateById(sysDict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		sysDictService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
