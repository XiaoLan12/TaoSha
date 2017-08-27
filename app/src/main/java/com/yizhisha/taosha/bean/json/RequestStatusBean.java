package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/7/4.
 */

public class RequestStatusBean {
    private String status;

    private String info;

    private int uid;

    private String orderno;

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return this.info;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
