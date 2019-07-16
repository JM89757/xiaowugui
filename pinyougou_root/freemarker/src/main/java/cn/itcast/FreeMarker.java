package cn.itcast;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class FreeMarker {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("D:\\IdeaProjects\\pinyougou_root\\freemarker\\src\\main\\resources\\"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("test.ftl");
        List goodsList = new ArrayList();
        Map goods1 = new HashMap();
        goods1.put("name", "苹果");
        goods1.put("price", 5.8);
        Map goods2 = new HashMap();
        goods2.put("name", "香蕉");
        goods2.put("price", 2.5);
        Map goods3 = new HashMap();
        goods3.put("name", "橘子");
        goods3.put("price", 3.2);
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        Map map = new HashMap();
        map.put("name", "Jay");
        map.put("message", "Welcome to FreeMarker");
        map.put("success", true);
        map.put("failure", false);
        map.put("goodsList", goodsList);
        map.put("today", new Date());
        map.put("point", 1234567890);
        Writer writer = new FileWriter(new File("d:\\test.html"));
        template.process(map, writer);
        writer.close();

    }
}
