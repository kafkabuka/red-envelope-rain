package com.bainianzhi.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.entity.ViewUserHitEntity;

import java.util.Map;

/**
 * VIEW
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
public interface ViewUserHitService extends IService<ViewUserHitEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

