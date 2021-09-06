package com.metocs.main.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "database_name")
public class DatabaseName {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 别名
     */
    private String name;
    /**
     * 数据库名
     */
    private String databaseName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;



}
