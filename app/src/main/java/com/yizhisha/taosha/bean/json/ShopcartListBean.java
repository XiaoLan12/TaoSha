package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/7/10.
 */

public class ShopcartListBean {
    private String status;

    private String info;
    private List<Shopcart> shopcart ;

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public void setShopcart(List<Shopcart> shopcart){
        this.shopcart = shopcart;
    }
    public List<Shopcart> getShopcart(){
        return this.shopcart;
    }

    @Override
    public String toString() {
        return "ShopcartListBean{" +
                "shopcart=" + shopcart +
                '}';
    }
}
