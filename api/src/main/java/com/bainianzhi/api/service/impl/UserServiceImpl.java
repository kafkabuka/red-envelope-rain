package com.bainianzhi.api.service.impl;

import com.bainianzhi.api.dao.UserDao;
import com.bainianzhi.api.service.SysDictService;
import com.bainianzhi.api.service.UserService;
import com.bainianzhi.common.entity.UserEntity;
import com.bainianzhi.common.entity.dto.UserLevelDto;
import com.bainianzhi.common.utils.PageUtils;
import com.bainianzhi.common.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private SysDictService sysDictService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 带条件分页查询用户列表
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();

        //拼接输入的参数
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.and((w)->{
                w.like("uname",key).or().like("realname",key)
                        .or().like("idcard",key)
                        .or().like("phone",key);
            });
        }

        //拼接用户等级
        String level = (String) params.get("level");
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }

        //拼接用户创建时间的开始时间
        String begin = (String) params.get("begin");
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("createtime",begin);
        }

        //拼接创建时间的结束时间
        String end = (String) params.get("end");
        if(!StringUtils.isEmpty(end)  ){
            wrapper.le("createtime",end);
        }

        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                wrapper
        );

        // 封装用户等级名称
        PageUtils pageUtils = new PageUtils(page);
        List<UserEntity> records = page.getRecords();
        List<UserLevelDto> userLevelDtoList = records.stream().map(userEntity -> {
            UserLevelDto userLevelDto = new UserLevelDto();
            BeanUtils.copyProperties(userEntity,userLevelDto);
            // 获取用户等级名称
            String levelName = sysDictService.getLevelName(userEntity.getLevel());
            userLevelDto.setLevelName(levelName);
            return userLevelDto;
        }).collect(Collectors.toList());
        pageUtils.setList(userLevelDtoList);

        return pageUtils;
    }

    @Override
    public UserEntity login(String uname, String passwd) {
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("uname",uname).eq("passwd",passwd));
    }

}