package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/7/10.
 */

public class ShopcartListBean {
    private List<Shopcart> shopcart ;

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
