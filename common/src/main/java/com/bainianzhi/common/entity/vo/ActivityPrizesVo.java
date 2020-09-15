package com.bainianzhi.common.entity.vo;

import com.bainianzhi.common.entity.ActivityPrizesEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityPrizesVo extends ActivityPrizesEntity implements Serializable {
    private String title;//活动主题
    private String name;//奖品名称

}
