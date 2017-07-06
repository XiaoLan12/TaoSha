package com.yizhisha.taosha.bean.json;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by lan on 2017/7/6.
 */

public class OrderBean  implements MultiItemEntity {
    public static final int TYPE_1 = 1;
    public static final int TYPE_2= 2;

    private int itemType;
    private OrderHeadBean orderHeadBean;
    private OrderFootBean orderFootBean;
    private Goods goods;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public OrderHeadBean getOrderHeadBean() {
        return orderHeadBean;
    }

    public void setOrderHeadBean(OrderHeadBean orderHeadBean) {
        this.orderHeadBean = orderHeadBean;
    }

    public OrderFootBean getOrderFootBean() {
        return orderFootBean;
    }

    public void setOrderFootBean(OrderFootBean orderFootBean) {
        this.orderFootBean = orderFootBean;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
