package com.bainianzhi.common.entity.dto;

import com.bainianzhi.common.entity.PrizesEntity;
import lombok.Data;

@Data
public class PrizesDto extends PrizesEntity {
    public Integer amount; //奖品数量
}
