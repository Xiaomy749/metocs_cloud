package com.metocs.spider.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.metocs.spider.entity.Ip;
import com.metocs.spider.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@EnableScheduling
public class IPTest {

    @Autowired
    private IpService ipService;

    @Scheduled(cron = "0 0 */1 * * ?")
    public void test(){
        QueryWrapper<Ip> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("alive",2);
        List<Ip> list = ipService.list(queryWrapper);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Ip ip : list) {
            Thread thread = new Thread(()->{
                boolean valid = isValid(ip);
                if (!valid){
                    UpdateWrapper<Ip> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("id",ip.getId());
                    updateWrapper.set("alive",2);
                    ipService.update(updateWrapper);
                }else {
                    if (!"1".equals(ip.getAlive())){
                        UpdateWrapper<Ip> updateWrapper = new UpdateWrapper<>();
                        updateWrapper.eq("id",ip.getId());
                        updateWrapper.set("alive",1);
                        ipService.update(updateWrapper);
                    }
                }
            });
            executorService.execute(thread);
        }
    }

    public static boolean isValid(Ip ipBean) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipBean.getIp(), Integer.parseInt(ipBean.getPort())));
        try {
            URLConnection httpCon = new URL("https://www.baidu.com/").openConnection(proxy);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            int code = ((HttpURLConnection) httpCon).getResponseCode();
            return code == 200;
        } catch (IOException ignored) {

        }
        return false;
    }



}
