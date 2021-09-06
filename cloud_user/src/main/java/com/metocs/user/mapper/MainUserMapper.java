package com.metocs.user.mapper;

import com.metocs.common.base.MyBaseMapper;
import com.metocs.user.entity.MainUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MainUserMapper extends MyBaseMapper<MainUser> {

    @Select("SELECT * FROM main_user where user_name = #{userName}")
    MainUser getByUserName(@Param("userName") String userName);

}
