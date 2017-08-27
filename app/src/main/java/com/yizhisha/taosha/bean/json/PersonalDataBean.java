package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/7/4.
 */

public class PersonalDataBean {
    private String uid;

    private String username;

    private String linkman;

    private String sex;

    private String mobile;

    private String email;

    private String company;
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setLinkman(String linkman){
        this.linkman = linkman;
    }
    public String getLinkman(){
        return this.linkman;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public String getSex(){
        return this.sex;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return this.mobile;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setCompany(String company){
        this.company = company;
    }
    public String getCompany(){
        return this.company;
    }

    @Override
    public String toString() {
        return "PersonalDataBean{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", linkman='" + linkman + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
