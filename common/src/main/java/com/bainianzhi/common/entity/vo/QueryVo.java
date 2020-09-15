package com.bainianzhi.common.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QueryVo implements Serializable {
    private String title;   //主题
    private Date startTime; //开始时间
    private Date endTime;   //结束时间
    private Integer type;   //类型
    private Long from;   //当前对象
    private Long to;     //限制
}
