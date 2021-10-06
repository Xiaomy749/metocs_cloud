package com.metocs.spider.pipe;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.metocs.common.uils.EmptyUtil;
import com.metocs.spider.entity.Ip;
import com.metocs.spider.service.IpService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class IpPipeline implements Pipeline {

    @Autowired
    private IpService ipService;

    private static final String IP = "[\\d.]*";

    private static Pattern IP_RULE =  Pattern.compile(IP);

    @SneakyThrows
    @Override
    public void process(ResultItems resultItems, Task task) {
        Object ips = resultItems.get("ips");
        if (ips==null){
            return;
        }
        List<Ip> list = JSONObject.parseObject(JSON.toJSONString(ips),new TypeReference<>() {
        });

        List<Ip> saveIps = new ArrayList<>();
        for (Ip ip : list) {
            if (ip == null){
                continue;
            }
            if (EmptyUtil.is_empty(ip.getIp())||EmptyUtil.is_empty(ip.getPort())){
                continue;
            }
            String ipAddress = ip.getIp();
            String port = ip.getPort();

            Matcher ipMatcher = IP_RULE.matcher(ipAddress);
            if (!ipMatcher.matches()){
                continue;
            }
            Matcher portMatcher = IP_RULE.matcher(port);
            if (!portMatcher.matches()){
                continue;
            }
            try{
                this.ipService.save(ip);
            }catch (DuplicateKeyException ignored){

            }
        }


    }

}
