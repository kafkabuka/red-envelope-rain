package com.bainianzhi.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动中奖统计
 */
@Data
public class ActivityHitDto {
    /**
     * 活动主题
     */
    private String title;
    /**
     * 开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    /**
     * 结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date endtime;
    /**
     * 活动类型名称
     */
    private String typeName;
    /**
     * 总奖品数
     */
    private BigDecimal total;
    /**
     * 奖品已抽中数
     */
    private Long hit;
}
