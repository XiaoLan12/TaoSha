package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/7/5.
 */

public class MyOrderListBean {
    private List<Order> order ;
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
    public void setOrder(List<Order> order){
        this.order = order;
    }
    public List<Order> getOrder(){
        return this.order;
    }

}
