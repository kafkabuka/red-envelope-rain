package com.bainianzhi.api.config;

import com.alibaba.fastjson.JSON;
import com.bainianzhi.common.utils.R;
import com.bainianzhi.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户登录拦截器
 */
public class RedisSessionInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 无论访问地址是否正确，都进行登录验证
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") != null) {
            try {
                // 验证当前请求的session是否是已登录的session
                String loginUserId = (String) redisUtil.get("loginUser:" + session.getAttribute("loginUserId"));
                if (loginUserId != null && loginUserId.equals(session.getId())) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response401(response);
        return false;
    }

    private void response401(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        try {
            response.getWriter().print(JSON.toJSONString(new R().error(-1,"用户未登录")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
