package com.metocs.security.impl;

import com.metocs.common.base.BaseServiceImpl;
import com.metocs.security.entity.OauthUrl;
import com.metocs.security.mapper.OauthUrlMapper;
import com.metocs.security.service.OauthUrlService;
import org.springframework.stereotype.Service;

@Service
public class OauthUrlServiceImpl extends BaseServiceImpl<OauthUrlMapper, OauthUrl> implements OauthUrlService {
}
