package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/8/10.
 */

public class WechatBean {
    private  String openid;
    private String access_token;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
