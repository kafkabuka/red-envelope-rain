package com.bainianzhi.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 数据字典
 * 
 * @author ldm
 * @email ldm@gmail.com
 * @date 2020-09-06 10:57:51
 */
@Data
@TableName("sys_dict")
public class SysDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 类型
	 */
	private String dictType;
	/**
	 * 键
	 */
	private String dictKey;
	/**
	 * 值
	 */
	private String dictValue;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
