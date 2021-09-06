package com.metocs.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.metocs.common.controller.BaseController;
import com.metocs.common.entity.Result;
import com.metocs.common.uils.HttpUtil;
import com.metocs.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "auth")
public class LoginController extends BaseController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/password")
    public Result getALllDataBaseName(@RequestBody User user){
        String url = "http://admin.metocs.com/api/security/oauth/token?client_id=security&grant_type=password&client_secret=123456"
                +"&username="+user.getUsername()+"&password="+user.getPassword();
        String data = HttpUtil.httpPostRequest(url);
        JSONObject jsonObject = JSONObject.parseObject(data);
        Map<String,String> map = new HashMap<>();
        map.put("access_token",jsonObject.getString("access_token"));
        return success(map);
    }

}
