package com.bainianzhi.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserHitDto {
    /**
     * 活动主题
     */
    private String title;
    /**
     * 奖品名称
     */
    private String name;
    /**
     * 中奖时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date hittime;
}
