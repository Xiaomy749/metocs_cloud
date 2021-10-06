package com.metocs.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.metocs.common.controller.BaseController;
import com.metocs.common.entity.Result;
import com.metocs.common.uils.EmptyUtil;
import com.metocs.user.entity.MainUser;
import com.metocs.user.service.MainUserService;
import com.sun.tools.javac.Main;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.SimpleInstanceManager;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
    //@PreAuthorize ("hasAnyRole('ROLE_ADMIN')")
    public Result detail(@RequestParam(value = "id")String id){
        MainUser one = userService.getById(id);
        return success(one);
    }

    @GetMapping("/detail")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Result info(@RequestParam(value = "id")String id){
        MainUser one = userService.getById(id);
        return success(one);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(value = "name",required = false)String nickName,
                       @RequestParam(value = "phone",required = false)String phone,
                       @RequestParam(value = "type",required = false)Integer type,
                        @RequestParam(value = "pageNum")Integer pageNum,
                        @RequestParam(value = "pageSize")Integer pageSize){
        QueryWrapper<MainUser> queryWrapper = new QueryWrapper<>();
        if (EmptyUtil.not_empty(nickName)){
            queryWrapper.like("nick_name",nickName);
        }
        if (EmptyUtil.not_empty(phone)){
            queryWrapper.like("phone",phone);
        }
        if (EmptyUtil.not_empty(type)){
            queryWrapper.like("type",type);
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/make")
    public Result make() throws InterruptedException {
        List<MainUser> list = new LinkedList<>();
        List<MainUser> synchronizedList = Collections.synchronizedList(list);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i <10000; i++) {
            int finalI = i;
            Thread thread = new Thread(()->{
                MainUser mainUser = new MainUser();
                String replace = UUID.randomUUID().toString().replace("-", "");
                mainUser.setNickName("测试用户"+ finalI +"号");
                mainUser.setUserName(replace);
                mainUser.setPhone(getTel());
                mainUser.setEmail(getEmail(6,12));
                mainUser.setPassWord(passwordEncoder.encode(replace));
                mainUser.setBirthday(randomBirthday());
                mainUser.setId(null);
                synchronizedList.add(mainUser);
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){
             Thread.sleep(1000);
        }
        try{
            userService.saveBatch(synchronizedList);
        }catch (Exception ignore){

        }

        return success();
    }

    private static  SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String randomBirthday() {
        int startYear=2016;									//指定随机日期开始年份
        int endYear=2018;									//指定随机日期开始年份(含)
        long start = Timestamp.valueOf(startYear+1+"-1-1 0:0:0").getTime();
        long end = Timestamp.valueOf(endYear+"-1-1 0:0:0").getTime();
        long ms=(long) ((end-start)*Math.random()+start);	//获得了符合条件的13位毫秒数

        return FORMAT.format(new Date(ms));

    }

    private static final String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String[] email_suffix="@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");

    public static String getEmail(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int)(Math.random()*email_suffix.length)]);
        return sb.toString();
    }

}
