package com.metocs.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.metocs.common.controller.BaseController;
import com.metocs.common.entity.Result;
import com.metocs.common.uils.EmptyUtil;
import com.metocs.security.entity.OauthUrl;
import com.metocs.security.service.OauthUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "url")
public class UrlController extends BaseController {

    @Autowired
    private OauthUrlService urlService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result getByUserName(@RequestParam(value = "name",required = false)String name,
                                @RequestParam(value = "parentId",required = false)String parentId,
                                @RequestParam(value = "url",required = false)String url,
                                @RequestParam(value = "type",required = false)Integer type,
                                @RequestParam(value = "level",required = false)Integer level){
        QueryWrapper<OauthUrl> queryWrapper = new QueryWrapper<>();
        if (EmptyUtil.not_empty(name)){
            queryWrapper.eq("name",name);
        }
        if (EmptyUtil.not_empty(parentId)){
            queryWrapper.eq("parent_Id",parentId);
        }else {
            queryWrapper.isNull("parent_Id");
        }
        if (EmptyUtil.not_empty(url)){
            queryWrapper.eq("url",url);
        }
        if (EmptyUtil.not_empty(type)){
            queryWrapper.eq("type",type);
        }
        if (EmptyUtil.not_empty(level)){
            queryWrapper.eq("level",level);
        }
        queryWrapper.orderByAsc("sort");
        List<OauthUrl> list = urlService.list(queryWrapper);
        return success(list);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result save(@RequestBody OauthUrl oauthUrl){
        urlService.save(oauthUrl);
        return success();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result update(@RequestBody OauthUrl oauthUrl){
        urlService.updateById(oauthUrl);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result delete(@PathVariable(value = "id")String id){
        urlService.removeById(id);
        return success();
    }


}
