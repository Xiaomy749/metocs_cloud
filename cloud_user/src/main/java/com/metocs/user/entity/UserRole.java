package com.metocs.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "oauth_user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 4320418500816860139L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    private String roleId;

    private String userId;

    private Date createTime;

    private Date updateTime;
}
