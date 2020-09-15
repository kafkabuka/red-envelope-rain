package com.bainianzhi.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Data
@TableName("rain_activity")
public class ActivityEntity implements Serializable {
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
	 * 活动宣传图
	 */
	private String pic;
	/**
	 * 活动简介
	 */
	private String info;
	/**
	 * 开始时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
	private Date starttime;
	/**
	 * 结束时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
	private Date endtime;
	/**
	 * 类型（1=概率类，2=随机类）
	 */
	private Integer type;
	/**
	 * 状态（0=未开始，1=历史活动）
	 */
	private Integer status;

}
