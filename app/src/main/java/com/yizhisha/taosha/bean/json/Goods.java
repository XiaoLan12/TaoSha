package com.yizhisha.taosha.bean.json;

import android.content.ServiceConnection;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yizhisha.taosha.adapter.MyOrderAdapter;

import java.io.Serializable;

/**
 * Created by lan on 2017/7/5.
 */

public class Goods implements Serializable{

    private int id;
    private int gid;

    private int mzw_uid;

    private String title;

    private String pname;

    private float price;

    private int amount;

    private String litpic;

    private String detail;

    private float totalprice;

    private String remark;

    private String mobile;

    private int is_ship;

    private String orderno;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIs_ship() {
        return is_ship;
    }

    public void setIs_ship(int is_ship) {
        this.is_ship = is_ship;
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

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", title='" + title + '\'' +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", litpic='" + litpic + '\'' +
                ", detail='" + detail + '\'' +
                ", totalprice=" + totalprice +
                ", remark='" + remark + '\'' +
                ", mobile='" + mobile + '\'' +
                ", is_ship=" + is_ship +
                '}';
    }
}
