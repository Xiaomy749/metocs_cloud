package com.metocs.security.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("main_user")
public class User implements Serializable {
    private static final long serialVersionUID = -7627075157689492698L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户类型
     */
    private String type;

    /**
     * 用户令牌
     */
    private String accessToken;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 最近登录时间
     */
    private Date loginTime;
    /**
     * 修改人
     */
    private String updateUser;

    @TableField(exist = false)
    private List<String> roleList;

}
