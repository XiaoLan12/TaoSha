package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/7/9.
 */

public class SearchBean {
    private String status;

    private String info;
    private List<SearchDetailBean> goods;

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public List<SearchDetailBean> getGoods() {
        return goods;
    }

    public void setGoods(List<SearchDetailBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", goods=" + goods +
                '}';
    }
}
