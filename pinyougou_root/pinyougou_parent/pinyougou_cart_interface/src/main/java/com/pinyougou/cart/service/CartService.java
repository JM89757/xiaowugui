package com.pinyougou.cart.service;

import com.pinyougou.pojo.Cart;

import java.util.List;

public interface CartService {

    public List<Cart> addGoodsToCartList(List<Cart> cartList, Long itemId, Integer num);

    List<Cart> findCartListFromRedis(String username);

    List<Cart> mergeCartList(List<Cart> cartList_cookie, List<Cart> cartList_redis);

    void saveCartListToRedis(String username, List<Cart> cartList);
}
