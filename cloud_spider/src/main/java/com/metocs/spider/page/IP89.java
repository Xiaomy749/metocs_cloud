package com.metocs.spider.page;

import com.metocs.spider.entity.Ip;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class IP89 implements PageProcessor {

    private final Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public Site getSite() {
        return site;
    }

    private List<Ip> ips = new ArrayList<>();

    private static final Pattern IP_RULE = Pattern.compile("([:\\d.]*)(@)");
    @Override
    public void process(Page page) {
        Html html = page.getHtml();

        Selectable form = html.xpath("//div[@class='layui-col-md8']");
        System.out.println(form);

        Selectable xpath1 = form.xpath("//div[@id='layui-laypage-1']");

        System.out.println(xpath1);

//        for (Selectable url : urls) {
//            Selectable xpath = url.xpath("//a/@href");
//            page.addTargetRequest("https://www.89ip.cn/"+xpath.get());
//
//        }

        Selectable xpath = html.xpath("//div[@class='layui-table']");
        List<Selectable> nodes = xpath.xpath("//tbody/tr").nodes();
        for (Selectable node : nodes) {
            List<Selectable> nodes1 = node.xpath("//td").nodes();
            Ip ip = new Ip();
            ip.setIp(nodes1.get(0).get());
            ip.setPort(nodes1.get(1).get());
            ips.add(ip);
        }
        page.putField("ips",ips);
    }

    public static void main(String[] args) {
        Spider.create(new IP89())
                .addUrl("https://www.89ip.cn/")
                .addPipeline(new ConsolePipeline())
                .thread(10)
                .run();
    }

}
