package com.pinyougou.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.pinyougou.cart.service.CartService;
import com.pinyougou.pojo.Cart;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Reference(timeout = 6000)
    private CartService cartService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping("/showName")
    public Map showName() {
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前登录人：" + loginName);
        Map map = new HashMap<>();
        map.put("loginName", loginName);
        return map;
    }


    @RequestMapping("/findCartList")
    public List<Cart> findCartList() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前的用户是：" + username);
        String cartListString = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
        if (cartListString == null || cartListString.equals("")) {
            cartListString = "[]";
        }
        List<Cart> cartList_cookie = JSON.parseArray(cartListString, Cart.class);
        if (username.equals("anonymousUser")) {
            System.out.println("未登录的用户！！！");
            return cartList_cookie;
        } else {
            List<Cart> cartList_redis = cartService.findCartListFromRedis(username);
            if (cartList_cookie.size() > 0) {
                List<Cart> cartList = cartService.mergeCartList(cartList_cookie, cartList_redis);
                cartService.saveCartListToRedis(username, cartList);
                CookieUtil.deleteCookie(request, response, "cartList");
                System.out.println("合并了购物车！！！");
                return cartList;
            }
            return cartList_redis;
        }
    }

    @RequestMapping("/addGoodsToCartList")
    @CrossOrigin(origins = "http://localhost:9105")
    public Result addGoodsToCartList(Long itemId, Integer num) {
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:9105");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前的用户是：" + username);
        try {
            List<Cart> cartList = findCartList();
            cartList = cartService.addGoodsToCartList(cartList, itemId, num);
            if (username.equals("anonymousUser")) {
                CookieUtil.setCookie(request, response, "cartList", JSON.toJSONString(cartList), 3600 * 24, "UTF-8");
                System.out.println("向cookie中存数据！！！");
            } else {
                cartService.saveCartListToRedis(username, cartList);
            }
            return new Result(true, "添加购物车成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加购物车失败！！！");
        }

    }


}
