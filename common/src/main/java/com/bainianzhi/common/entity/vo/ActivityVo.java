package com.bainianzhi.common.entity.vo;

import com.bainianzhi.common.entity.ActivityEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityVo extends ActivityEntity implements Serializable {
    private String typeName;

    @Override
    public String toString() {
        return "ActivityVo{" +super.toString()+
                "typeName='" + typeName + '\'' +
                '}';
    }
}
