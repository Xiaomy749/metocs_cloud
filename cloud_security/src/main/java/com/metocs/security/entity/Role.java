package com.metocs.security.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "oauth_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 8380597683815734833L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private String nickName;

    private Date createTime;

    private Date updateTime;

}