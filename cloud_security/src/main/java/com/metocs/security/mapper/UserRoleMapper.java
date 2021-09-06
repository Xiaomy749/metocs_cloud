package com.metocs.security.mapper;


import com.metocs.common.annotation.SourceName;
import com.metocs.common.base.MyBaseMapper;
import com.metocs.security.entity.Role;
import com.metocs.security.entity.UserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserRoleMapper extends MyBaseMapper<UserRole> {

    @Select("SELECT r.* FROM oauth_user_role ou join oauth_role r on r.id = ou.role_id where ou.user_id = #{id}")
    List<Role> selectByUser(Long id);
}
