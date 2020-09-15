package com.bainianzhi.api.service.impl;

import com.bainianzhi.api.dao.ViewUserHitDao;
import com.bainianzhi.api.service.ViewUserHitService;
import com.bainianzhi.common.entity.ViewUserHitEntity;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;


@Service("viewUserHitService")
public class ViewUserHitServiceImpl extends ServiceImpl<ViewUserHitDao, ViewUserHitEntity> implements ViewUserHitService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<ViewUserHitEntity> wrapper = new QueryWrapper<>();

        //拼接输入的参数
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.and((w)->{
                w.like("uname",key).or().like("realname",key)
                        .or().like("idcard",key)
                        .or().like("phone",key)
                        .or().like("title",key)
                        .or().like("name",key);
            });
        }

        //拼接中奖时间的开始时间
        String begin = (String) params.get("begin");
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("hittime",begin);
        }

        //拼接中奖时间的结束时间
        String end = (String) params.get("end");
        if(!StringUtils.isEmpty(end)  ){
            wrapper.le("hittime",end);
        }

        IPage<ViewUserHitEntity> page = this.page(
                new Query<ViewUserHitEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}