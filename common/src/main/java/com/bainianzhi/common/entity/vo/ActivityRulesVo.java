package com.bainianzhi.common.entity.vo;

import com.bainianzhi.common.entity.ActivityRulesEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityRulesVo extends ActivityRulesEntity implements Serializable {
    private String userLevelName;
    private String title;
}
