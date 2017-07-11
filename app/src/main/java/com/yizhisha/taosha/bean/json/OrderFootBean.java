package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/7/6.
 */

public class OrderFootBean{
    private float totalprice;
    private int status;
    private int amount;
    public float getTotalprice() {
        return totalprice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
