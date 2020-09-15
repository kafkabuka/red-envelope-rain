package com.bainianzhi.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 活动策略
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Data
@TableName("rain_activity_rules")
public class ActivityRulesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 活动id
	 */
	private Integer activityid;
	/**
	 * 会员等级
	 */
	private Integer userlevel;
	/**
	 * 可抽奖次数（0为不限）
	 */
	private Integer enterTimes;
	/**
	 * 最大中奖次数（0为不限）
	 */
	private Integer goalTimes;

}
