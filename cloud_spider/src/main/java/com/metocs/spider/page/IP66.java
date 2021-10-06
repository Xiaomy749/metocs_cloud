package com.metocs.spider.page;

import com.metocs.spider.entity.Ip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

@Slf4j
@Component
public class IP66 implements PageProcessor {

    private final Site site = Site.me().setRetryTimes(3).setSleepTime(2000);

    @Override
    public Site getSite() {
        return site;
    }

    private List<Ip> ips = new ArrayList<>();


    @Override
    public void process(Page page) {

        Html html = page.getHtml();
        Selectable ul = html.xpath("//ul[@class='textlarge22']//li");
        List<Selectable> nodes = ul.nodes();
        for (Selectable node : nodes) {
            String url = "http://www.66ip.cn"+node.xpath("//li//a/@href").get();
            String s = url.replaceAll("http://www.66ip.cnhttp://www.66ip.cn", "http://www.66ip.cn");
            page.addTargetRequest(s);
        }

        Selectable footer = html.xpath("//div[@class='footer']");

        Selectable xpath = footer.xpath("//table");
        List<Selectable> list = xpath.nodes();
        for (Selectable selectable : list) {
            Ip ip = new Ip();
            Selectable tr = selectable.xpath("//tr");
            List<Selectable> td = tr.nodes();
            for (Selectable selectable1 : td) {
                ip.setIp(selectable1.xpath("//td[1]/text()").get());
                ip.setPort(selectable1.xpath("//td[2]/text()").get());
            }
            ips.add(ip);
        }
        page.putField("ips",ips);
    }

}
