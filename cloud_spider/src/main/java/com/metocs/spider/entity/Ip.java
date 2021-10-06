package com.metocs.spider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "ip_address")
public class Ip {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String ip;
    private String port;
    private String alive;
    private Date createTime;
    private Date updateTime;

}
