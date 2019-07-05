package com.pinyougou.pojo;

import java.io.Serializable;
import java.util.List;

public class GoodsEntityGroup implements Serializable {

    private TbGoods tbGoods;

    private TbGoodsDesc tbGoodsDesc;

    private List<TbItem> tbItemList;

    public GoodsEntityGroup() {
    }

    public GoodsEntityGroup(TbGoods tbGoods, TbGoodsDesc tbGoodsDesc, List<TbItem> tbItemList) {
        this.tbGoods = tbGoods;
        this.tbGoodsDesc = tbGoodsDesc;
        this.tbItemList = tbItemList;
    }

    public TbGoods getTbGoods() {
        return tbGoods;
    }

    public void setTbGoods(TbGoods tbGoods) {
        this.tbGoods = tbGoods;
    }

    public TbGoodsDesc getTbGoodsDesc() {
        return tbGoodsDesc;
    }

    public void setTbGoodsDesc(TbGoodsDesc tbGoodsDesc) {
        this.tbGoodsDesc = tbGoodsDesc;
    }

    public List<TbItem> getTbItemList() {
        return tbItemList;
    }

    public void setTbItemList(List<TbItem> tbItemList) {
        this.tbItemList = tbItemList;
    }
}
