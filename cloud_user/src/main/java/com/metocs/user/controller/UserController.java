package com.metocs.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.metocs.common.controller.BaseController;
import com.metocs.common.entity.Result;
import com.metocs.common.uils.EmptyUtil;
import com.metocs.user.entity.MainUser;
import com.metocs.user.service.MainUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/basic")
public class UserController extends BaseController {

    @Autowired
    private MainUserService userService;


    @GetMapping("/getByUserName")
    public Result getByUserName(@RequestParam(value = "userName")String userName){
        MainUser user = userService.getByUserName(userName);
        return success(user);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result save(@RequestBody MainUser user){
        try{
            user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassWord()));
            userService.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return error(500,"用户名 手机号码已存在！");
        }
        return success();
    }

    @GetMapping("/info")
    @PreAuthorize ("hasAnyRole('ROLE_ADMIN')")
    public Result detail(@RequestParam(value = "id")String id){
        MainUser one = userService.getById(id);
        return success(one);
    }

    @GetMapping("/detail")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result info(@RequestParam(value = "id")String id){
        MainUser one = userService.getById(id);
        return success(one);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(value = "name",required = false)String nickName,
                       @RequestParam(value = "phone",required = false)String phone,
                        @RequestParam(value = "pageNum")Integer pageNum,
                        @RequestParam(value = "pageSize")Integer pageSize){
        QueryWrapper<MainUser> queryWrapper = new QueryWrapper<>();
        if (EmptyUtil.not_empty(nickName)){
            queryWrapper.like("nick_name",nickName);
        }
        if (EmptyUtil.not_empty(phone)){
            queryWrapper.like("phone",phone);
        }
        Page<MainUser> page = userService.page(new Page<>(pageNum,pageSize),queryWrapper);
        return success(page);
    }

    @PostMapping("/update")
    public Result update(@RequestBody MainUser user){
        try{
            boolean one = userService.updateById(user);
            if (one){
                return success();
            }
            return error(500,"修改失败！");
        }catch (Exception e){
            return error(500,"用户名 手机号码已存在！");
        }
    }

}
