package com.metocs.security.impl;

import com.metocs.common.base.BaseServiceImpl;

import com.metocs.security.entity.Role;
import com.metocs.security.mapper.RoleMapper;
import com.metocs.security.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {
}
