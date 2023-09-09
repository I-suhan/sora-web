package com.suhan.core.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;
}
