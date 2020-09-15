package com.bainianzhi.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Data
@TableName("rain_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 用户名
	 */
	private String uname;
	/**
	 * 头像
	 */
	private String pic;
	/**
	 * 密码
	 */
	private String passwd;
	/**
	 * 姓名
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
	 * 等级
	 */
	private Integer level;
	/**
	 * 注册时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createtime;
	/**
	 * 更新时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
	private Date updatetime;

}
