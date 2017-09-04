package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/8/10.
 */

public class WechatInfoBean {
    private String nickname;
    private String headimgurl;
    private int sex;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
