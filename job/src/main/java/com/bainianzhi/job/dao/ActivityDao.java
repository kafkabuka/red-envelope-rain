package com.bainianzhi.job.dao;

import com.bainianzhi.common.entity.ActivityEntity;
import com.bainianzhi.common.entity.dto.PrizesDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 活动
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Mapper
public interface ActivityDao extends BaseMapper<ActivityEntity> {

    // 查询指定活动关联的所有的奖品信息，还包括奖品数量
    List<PrizesDto> getPrizes(Integer id);
}
