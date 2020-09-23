package com.zgcenv.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasePo implements Serializable {
    private String id;
    private String createdBy;
    private Date createdTime;
    private String updatedBy;
    private Date updatedTime;
}
