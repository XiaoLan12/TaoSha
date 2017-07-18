package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/7/9.
 */

public class ProductDetailBean {
    private int info_s;
    private String info_t;

    private ProductDeatilItemBean goods;

    public int getInfo_s() {
        return info_s;
    }

    public void setInfo_s(int info_s) {
        this.info_s = info_s;
    }

    public String getInfo_t() {
        return info_t;
    }

    public void setInfo_t(String info_t) {
        this.info_t = info_t;
    }

    public ProductDeatilItemBean getGoods() {
        return goods;
    }

    public void setGoods(ProductDeatilItemBean goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "ProductDetailBean{" +
                "info_s=" + info_s +
                ", info_t='" + info_t + '\'' +
                ", goods=" + goods +
                '}';
    }
}
