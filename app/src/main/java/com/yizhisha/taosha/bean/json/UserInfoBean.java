package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/7/3.
 */

public class UserInfoBean {
    private String uid;

    private String username;

    private String avatar;

    private String linkman;

    private String company;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", linkman='" + linkman + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
