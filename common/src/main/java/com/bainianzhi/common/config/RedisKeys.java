package com.bainianzhi.common.config;

public class RedisKeys {
    //活动信息
    public final static String INFO="activity_info_";
    //令牌前缀
    public final static String TOKEN="activity_token_";
    //令牌桶key
    public final static String TOKENS="activity_tokens_";
    //最大中奖数
    public final static String MAX_HIT_TIMES="activity_max_hit_times_";
    //最大抽奖数
    public final static String MAX_ENTER_TIMES="activity_max_enter_times_";

    //redis session
    public final static String SESSIONID="user_sessionid_";
    //用户中奖数
    public final static String USER_HIT="user_hit_";
    //用户登录错误次数
    public final static String USER_LOGIN_ERROR_TIMES="user_login_error_times_";
    //用户是否参与该活动
    public final static String USER_ACTIVITY = "user_activity_";
}
