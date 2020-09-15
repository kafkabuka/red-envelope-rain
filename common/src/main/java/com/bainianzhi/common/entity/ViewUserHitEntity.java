package com.bainianzhi.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * VIEW
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Data
@TableName("view_rain_user_hit")
public class ViewUserHitEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 活动主题
	 */
	private String title;
	/**
	 * 活动类型
	 */
	private String type;
	/**
	 * 用户名
	 */
	private String uname;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 身份证号
	 */
	private String idcard;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 用户等级
	 */
	private String level;
	/**
	 * 奖品名称
	 */
	private String name;
	/**
	 * 市场价
	 */
	private BigDecimal price;
	/**
	 * 活动
	 */
	private Integer activityid;
	/**
	 * 用户
	 */
	private Integer userid;
	/**
	 * 奖品
	 */
	private Integer prizesid;
	/**
	 * 中奖时间
	 */
	private Date hittime;

}
