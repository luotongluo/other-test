package com.lt.dailytest.rss;

import java.util.ArrayList;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.rometools.rome.feed.rss.TextInput;
import com.rometools.rome.feed.rss.Cloud;
import com.rometools.rome.feed.rss.Image;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author tong.luo
 * @description FeedConsumer
 * @date 2021/3/26 14:59
 */
public class FeedConsumer {
    private static final String RSS_URL = "https://blog.csdn.net/qq_40408317/rss/list?spm=1001.2014.3001.5494";
    private static final String RSS_VER = "rss_2.0";

    /**
     * 读取rss文件
     *
     * @throws MalformedURLException
     */
    public static void testReadRss() throws MalformedURLException {
        //URL feedUrl = new URL(String.format(RSS_URL, symbol));
        //null 代表header
        URL feedUrl = new URL(String.format(RSS_URL, null));
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        try {
            feed = input.build(new XmlReader(feedUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (feed == null) {
            //log.warn("syndFeed is null, symbol:{}", symbol);
            return;
        }
        List<SyndEntry> list = feed.getEntries();
        for (SyndEntry entry : list) {
            System.out.println(entry.getTitle() + "\n" + entry.getUpdatedDate() + "\n" + entry.getLink());
        }
    }

    /**
     * https://www.songliguo.com/java-rss-online-subscription.html
     * @throws Exception
     */
    public static void createRssFile() throws Exception {

        Channel channel = new Channel();
        channel.setTitle("");
        //网站描述
        channel.setDescription("setDescription");
        //网站主页链接
        channel.setLink("https://blog.csdn.net/qq_40408317");
        channel.setUri("");
        channel.setImage(new Image());
        channel.setItems(new ArrayList<>());
        channel.setTextInput(new TextInput());
        channel.setLanguage("zh-cn");
        channel.setRating("");
        //版权声明
        channel.setCopyright("版权声明");
        //RSS发布时间
        channel.setPubDate(new Date());
        channel.setLastBuildDate(new Date());
        channel.setDocs("");
        channel.setManagingEditor("");
        channel.setWebMaster("");
        channel.setSkipHours(new ArrayList<>());
        channel.setSkipDays(new ArrayList<>());
        channel.setCloud(new Cloud());
        channel.setCategories(new ArrayList<>());
        channel.setGenerator("");
        //time to live的简写，在刷新前当前RSS在缓存中可以保存多长时间（分钟）
        channel.setTtl(5);
        channel.setModules(new ArrayList<>());
        channel.setFeedType("");
        //RSS文件编码
        channel.setEncoding("utf-8");
        channel.setModules(new ArrayList<>());
        channel.setForeignMarkup(new ArrayList<>());
        channel.setStyleSheet("");
        System.out.println(JSON.toJSONString(channel));
    }

    public static void main(String[] args) {

        try {
            testReadRss();
            createRssFile();
//            String url = "http://localhost:8080/rss";
//
//            try (XmlReader reader = new XmlReader(new URL(url))) {
//                SyndFeed feed = new SyndFeedInput().build(reader);
//                System.out.println(feed.getTitle());
//                System.out.println("***********************************");
//                for (SyndEntry entry : feed.getEntries()) {
//                    System.out.println(entry);
//                    System.out.println("***********************************");
//                }
//                System.out.println("Done");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
