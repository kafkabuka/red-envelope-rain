package com.bainianzhi.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.entity.PrizesEntity;

import java.util.Map;

/**
 * 奖品
 *
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
public interface PrizesService extends IService<PrizesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

