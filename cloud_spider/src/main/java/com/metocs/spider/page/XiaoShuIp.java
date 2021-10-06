package com.metocs.spider.page;

import com.metocs.spider.entity.Ip;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class XiaoShuIp implements PageProcessor {

    private final Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public Site getSite() {
        return site;
    }

    private List<Ip> ips = new ArrayList<>();

    private static final Pattern IP_RULE = Pattern.compile("([:\\d.]*)(@)");

    @Override
    public void process(Page page) {
        Selectable url = page.getUrl();
        Html html = page.getHtml();
        if ("http://www.xsdaili.cn/".equals(url.get())){

            Selectable xpath = html.xpath("//a[@class='list-group-item']");
            List<Selectable> nodes = xpath.nodes();
            for (Selectable node : nodes) {
                String s = "http://www.xsdaili.cn" + node.xpath("//a/@href");
                page.addTargetRequest(s);
            }
        }
        String nowUrl = url.replace("http://www.xsdaili.cn/dayProxy/", "").get();

        if (!nowUrl.startsWith("ip")){
            Selectable table = html.xpath("//div[@class='table table-hover panel-default panel ips ']");
            Selectable ipHref = table.xpath("//div[@class='title']/a/@href");
            page.addTargetRequest(ipHref.get());
        }else {
            Selectable content = html.xpath("//div[@class='col-md-12']/div[@class='cont']/text()");
            String[] split = content.get().split("#");
            for (String s : split) {
                Matcher matcher = IP_RULE.matcher(s);
                if (matcher.find()){
                    String group = matcher.group(1);
                    String[] ipGroup = group.split(":");
                    Ip ip = new Ip();
                    ip.setIp(ipGroup[0]);
                    ip.setPort(ipGroup[1]);
                    ips.add(ip);
                }
            }
        }
        page.putField("ips",ips);
    }

}
