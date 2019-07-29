package com.pinyougou.pay.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.wxpay.sdk.WXPayUtil;
import com.pinyougou.pay.service.WeixinPayService;
import org.springframework.beans.factory.annotation.Value;
import util.HttpClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeixinPayServiceImpl implements WeixinPayService {

    @Value("${appid}")
    private String appid;

    @Value("${partner}")
    private String partner;

    @Value("${partnerkey}")
    private String partnerkey;

    @Override
    public Map createNative(String out_trade_no, String total_fee) {
        Map map = new HashMap();
        map.put("appid", appid);
        map.put("mch_id", partner);
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        map.put("body", "品优购");
        map.put("out_trade_no", out_trade_no);
        map.put("total_fee", total_fee);
        map.put("spbill_create_ip", "127.0.0.1");
        map.put("notify_url", "http://www.itcast.cn");
        map.put("trade_type", "NATIVE");

        try {
            String signedXml = WXPayUtil.generateSignedXml(map, partnerkey);
            System.out.println(signedXml);
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            httpClient.setHttps(true);
            httpClient.setXmlParam(signedXml);
            httpClient.post();

            String content = httpClient.getContent();
            Map<String, String> xmlToMap = WXPayUtil.xmlToMap(content);
            System.out.println("微信返回的结果是：" + xmlToMap);
            Map result = new HashMap();
            result.put("code_url", xmlToMap.get("code_url"));
            result.put("out_trade_no", out_trade_no);
            result.put("total_fee", total_fee);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    @Override
    public Map queryPayStatus(String out_trade_no) {
        Map map = new HashMap();
        map.put("appid", appid);
        map.put("mch_id", partner);
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        map.put("out_trade_no", out_trade_no);
        try {
            String signedXml = WXPayUtil.generateSignedXml(map, partnerkey);
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            httpClient.setXmlParam(signedXml);
            httpClient.setHttps(true);
            httpClient.post();

            String content = httpClient.getContent();
            Map<String, String> xmlToMap = WXPayUtil.xmlToMap(content);
            System.out.println("微信返回的结果是：" + content);
            return xmlToMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
