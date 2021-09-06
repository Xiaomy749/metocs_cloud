package com.metocs.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "oauth_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 8380597683815734833L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private String nickName;

    private Date createTime;

    private Date updateTime;

}