package com.bainianzhi.common.entity.dto;

import com.bainianzhi.common.entity.UserEntity;
import lombok.Data;

@Data
public class UserLevelDto extends UserEntity {
    private String levelName; // 用户等级名称
}
