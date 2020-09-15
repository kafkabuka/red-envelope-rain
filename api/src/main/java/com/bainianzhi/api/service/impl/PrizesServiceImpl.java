package com.bainianzhi.api.service.impl;

import com.bainianzhi.api.dao.PrizesDao;
import com.bainianzhi.common.entity.PrizesEntity;
import com.bainianzhi.api.service.PrizesService;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;


@Service("prizesService")
public class PrizesServiceImpl extends ServiceImpl<PrizesDao, PrizesEntity> implements PrizesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PrizesEntity> wrapper = new QueryWrapper<>();

        //拼接输入的参数
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.and((w)->{
                w.like("name",key).or().like("info",key);
            });
        }

        IPage<PrizesEntity> page = this.page(
                new Query<PrizesEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}