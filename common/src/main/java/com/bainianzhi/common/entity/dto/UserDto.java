package com.bainianzhi.common.entity.dto;

import com.bainianzhi.common.entity.UserEntity;
import lombok.Data;

@Data
public class UserDto extends UserEntity {
    private Integer activities; // 用户参与活动数
    private Integer prizes; // 用户中奖数
}
