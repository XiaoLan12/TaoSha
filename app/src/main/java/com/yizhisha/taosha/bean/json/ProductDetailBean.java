package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/7/9.
 */

public class ProductDetailBean {
        private ProductDeatilItemBean goods;

    public ProductDeatilItemBean getGoods() {
        return goods;
    }

    public void setGoods(ProductDeatilItemBean goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "ProductDetailBean{" +
                "goods=" + goods +
                '}';
    }
}
