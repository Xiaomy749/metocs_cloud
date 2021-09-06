package com.metocs.security.controller;

import com.metocs.common.controller.BaseController;
import com.metocs.common.entity.Result;
import com.metocs.security.entity.MyClientDetails;
import com.metocs.security.service.MyClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "client")
public class SecurityController extends BaseController {

    @Autowired
    private MyClientDetailsService detailsService;


    @GetMapping(value = "/getList")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result getALllDataBaseName(){
        List<MyClientDetails> main = detailsService.list();
        return success(main);
    }

    @PutMapping(value = "/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result save(@RequestBody MyClientDetails myClientDetails){
        detailsService.save(myClientDetails);
        return success();
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result update(@RequestBody MyClientDetails myClientDetails){
        detailsService.updateById(myClientDetails);
        return success();
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result delete(@PathVariable(value = "id") String id){
        detailsService.removeById(id);
        return success();
    }


}
