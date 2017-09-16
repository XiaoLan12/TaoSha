package com.yizhisha.taosha.bean.json;

/**
 * Created by Administrator on 2017/9/16.
 */

public class ListGoodsBean {
    private String status;
    private String info;
    private String goods;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "ListGoodsBean{" +
                "status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", goods='" + goods + '\'' +
                '}';
    }
}
