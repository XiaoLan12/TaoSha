package com.yizhisha.taosha.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lan on 2017/7/6.
 */

public class OrderFootBean implements Serializable{
    private float totalprice;
    private String orderno;
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


    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    @Override
    public String toString() {
        return "OrderFootBean{" +
                "totalprice=" + totalprice +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}
