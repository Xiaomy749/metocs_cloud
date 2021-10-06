package com.metocs.spider.impl;

import com.metocs.common.base.BaseServiceImpl;
import com.metocs.spider.entity.Ip;
import com.metocs.spider.mapper.IpMapper;
import com.metocs.spider.service.IpService;
import org.springframework.stereotype.Service;

@Service
public class IpServiceImpl extends BaseServiceImpl<IpMapper,Ip> implements IpService {
}
