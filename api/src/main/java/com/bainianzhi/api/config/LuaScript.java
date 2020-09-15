package com.bainianzhi.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Redis lua脚本配置类
 */
@Service
public class LuaScript{
    @Autowired
    private RedisTemplate redisTemplate;
 
    private DefaultRedisScript<Long> script;
 
    @PostConstruct
    public void init(){
        script = new DefaultRedisScript<Long>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/tokenCheck.lua")));
    }
 
    public Long tokenCheck(String activitykey,String curtime){

        List<String> keys = new ArrayList();
        keys.add(activitykey);
        keys.add(curtime);

        Long result = (Long) redisTemplate.execute(script,keys,0,0);

        return result;
    }
}