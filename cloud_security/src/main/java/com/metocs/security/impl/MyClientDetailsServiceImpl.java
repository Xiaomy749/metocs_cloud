package com.metocs.security.impl;

import com.metocs.common.base.BaseServiceImpl;
import com.metocs.security.entity.MyClientDetails;
import com.metocs.security.mapper.MyClientDetailsMapper;
import com.metocs.security.service.MyClientDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyClientDetailsServiceImpl extends BaseServiceImpl<MyClientDetailsMapper, MyClientDetails> implements MyClientDetailsService {
}
