package com.metocs.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.metocs.common.controller.BaseController;
import com.metocs.common.entity.Result;
import com.metocs.common.uils.EmptyUtil;
import com.metocs.main.entity.DatabaseName;
import com.metocs.main.service.DatabaseNameService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "dataBase")
public class MainController extends BaseController {


    @Autowired
    private DatabaseNameService databaseNameService;


    @GetMapping(value = "/getList")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result getALllDataBaseName(@RequestParam(value = "name",required = false)String name,
                                      @RequestParam(value = "pageSize")Integer pageSize,
                                      @RequestParam(value = "pageNum")Integer pageNum){
        Page<DatabaseName> page = new Page<>(pageNum,pageSize);
        QueryWrapper<DatabaseName> queryWrapper = new QueryWrapper<>();
        if (EmptyUtil.not_empty(name)){
            queryWrapper.like("name",name);
        }
        Page<DatabaseName> iPage = databaseNameService.page(page, queryWrapper);
        return success(iPage);
    }

    @PostMapping(value = "/save")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result save(@RequestBody DatabaseName databaseName){
        databaseNameService.save(databaseName);
        return success();
    }


    @PostMapping(value = "/update")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result update(@RequestBody DatabaseName databaseName){
        databaseNameService.updateById(databaseName);
        return success();
    }

    @DeleteMapping(value = "/delete/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result delete(@PathVariable(value = "id") Long id){
        databaseNameService.removeById(id);
        return success();
    }
}
