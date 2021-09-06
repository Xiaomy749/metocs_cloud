package com.metocs.user.service;

import com.metocs.common.base.BaseService;
import com.metocs.user.entity.MainUser;

public interface MainUserService extends BaseService<MainUser> {

    MainUser getByUserName(String userName);

}
