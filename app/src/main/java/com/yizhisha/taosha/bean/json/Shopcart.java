package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/7/10.
 */

public class Shopcart {
    private int mzw_uid;

    private String company;

    private List<ShopcartGoods> goods ;

    public int getMzw_uid() {
        return mzw_uid;
    }

    public void setMzw_uid(int mzw_uid) {
        this.mzw_uid = mzw_uid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<ShopcartGoods> getGoods() {
        return goods;
    }

    public void setGoods(List<ShopcartGoods> goods) {
        this.goods = goods;
    }
}
