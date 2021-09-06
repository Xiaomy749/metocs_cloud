package com.metocs.security.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "oauth_url")
public class OauthUrl {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;
    private String parentId;
    private String url;
    private String name;
    private Integer sort;
    private Integer level;
    private Integer type;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date  updateTime;

}
