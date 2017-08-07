package com.yizhisha.taosha.bean.json;

import java.io.Serializable;

/**
 * Created by lan on 2017/7/6.
 */

public class OrderFootBean implements Serializable{
    private float totalprice;
    private int status;
    private int amount;
    private int id;

    private int mzw_uid;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMzw_uid() {
        return mzw_uid;
    }

    public void setMzw_uid(int mzw_uid) {
        this.mzw_uid = mzw_uid;
    }
}
