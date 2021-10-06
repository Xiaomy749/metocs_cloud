package com.metocs.spider.task;

import com.metocs.spider.page.IP66;
import com.metocs.spider.page.XiaoShuIp;
import com.metocs.spider.pipe.IpPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class IpTask implements ApplicationRunner {

    @Autowired
    private  IpPipeline ipPipeline;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Spider.create(new IP66())
                .addUrl("http://www.66ip.cn/index.html")
                .addPipeline(this.ipPipeline)
                .thread(5)
                .run();


        Spider.create(new XiaoShuIp())
                .addUrl("http://www.xsdaili.cn/")
                .addPipeline(this.ipPipeline)
                .thread(5)
                .run();

    }
}
