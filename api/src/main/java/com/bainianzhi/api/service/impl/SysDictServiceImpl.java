package com.bainianzhi.api.service.impl;

import com.bainianzhi.api.dao.SysDictDao;
import com.bainianzhi.common.entity.SysDictEntity;
import com.bainianzhi.api.service.SysDictService;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //1、获取key
        String key = (String) params.get("key");
        QueryWrapper<SysDictEntity> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(key)){
            queryWrapper.like("dict_value",key);
        }
        queryWrapper.eq("dict_type","rain_user_level");

        IPage<SysDictEntity> page = this.page(
                new Query<SysDictEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<SysDictEntity> getUserLevelList() {
        return baseMapper.selectList(new QueryWrapper<SysDictEntity>().eq("dict_type","rain_user_level"));
    }

    /**
     * 保存用户等级
     * @param sysDict
     */
    @Override
    public void saveLevel(SysDictEntity sysDict) {
        //构造查询条件
        QueryWrapper<SysDictEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("dict_key");
        wrapper.eq("dict_type","rain_user_level");
        //查询出dict_key最大的数据,并获取它的dict_key值再加1
        SysDictEntity sysDictEntity = baseMapper.selectList(wrapper).get(0);
        Integer dictKey = Integer.valueOf(sysDictEntity.getDictKey());
        sysDict.setDictKey(Integer.toString(dictKey+1));
        sysDict.setDictType("rain_user_level");
        //执行保存
        baseMapper.insert(sysDict);
    }

    /**
     * 获取用户等级名称
     * @param level
     * @return
     */
    @Override
    public String getLevelName(Integer level) {
        QueryWrapper<SysDictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type","rain_user_level");
        wrapper.eq("dict_key",String.valueOf(level));
        SysDictEntity sysDictEntity = baseMapper.selectOne(wrapper);
        return sysDictEntity.getDictValue();
    }

    // 获取活动类型名称
    @Override
    public String getTypeName(Integer type) {
        QueryWrapper<SysDictEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type","rain_activity_type");
        wrapper.eq("dict_key",String.valueOf(type));
        SysDictEntity sysDictEntity = baseMapper.selectOne(wrapper);
        return sysDictEntity.getDictValue();
    }


    public List<SysDictEntity> querySysDict(String dictType){
        QueryWrapper<SysDictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_type",dictType);
        return baseMapper.selectList(queryWrapper);
    }
}