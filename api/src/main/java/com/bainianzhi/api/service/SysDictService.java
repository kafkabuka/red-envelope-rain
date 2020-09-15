package com.bainianzhi.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
public interface SysDictService extends IService<SysDictEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //获取用户所有等级
    List<SysDictEntity> getUserLevelList();

    //保存用户等级
    void saveLevel(SysDictEntity sysDict);

    // 获取用户等级名称
    String getLevelName(Integer level);

    // 获取活动类型名称
    String getTypeName(Integer type);

    /**
     * 查询类型
     * @param dictType
     * @return
     */
    List<SysDictEntity> querySysDict(String dictType);
}

