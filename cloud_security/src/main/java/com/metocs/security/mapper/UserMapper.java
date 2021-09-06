package com.metocs.security.mapper;

import com.metocs.common.annotation.DataSource;
import com.metocs.common.annotation.SourceName;
import com.metocs.common.base.MyBaseMapper;
import com.metocs.security.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends MyBaseMapper<User> {

    @DataSource(value = "user")
    @Select("SELECT * FROM main_user WHERE user_name = #{username}")
    User selectByUserName(String username);
}
