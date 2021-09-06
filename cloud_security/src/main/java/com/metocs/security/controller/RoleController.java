package com.metocs.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.metocs.common.annotation.DataSource;
import com.metocs.common.controller.BaseController;
import com.metocs.common.entity.Result;
import com.metocs.common.uils.EmptyUtil;
import com.metocs.security.entity.Role;
import com.metocs.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "role")
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result getByUserName(@RequestParam(value = "name",required = false)String name,
                                @RequestParam(value = "nickName",required = false)String nickName){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (EmptyUtil.not_empty(name)){
            queryWrapper.eq("name",name);
        }
        if (EmptyUtil.not_empty(nickName)){
            queryWrapper.eq("nick_name",nickName);
        }
        List<Role> list = roleService.list(queryWrapper);
        return success(list);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result save(@RequestBody Role role){
        roleService.save(role);
        return success();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result update(@RequestBody Role role){
        roleService.updateById(role);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result delete(@PathVariable(value = "id")String id){
        roleService.removeById(id);
        return success();
    }



}
