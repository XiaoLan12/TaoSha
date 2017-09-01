package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/8/10.
 */

public class AccountBean {
    private User  user;
    private List<AccountList> detail ;

    public User getUser() {
        return user;
    }

    public List<AccountList> getDetail() {
        return detail;
    }

    public void setDetail(List<AccountList> detail) {
        this.detail = detail;
    }
    public class User {
        private String money;

        private String username;

        private String mobile;

        private String avatar;

        public void setMoney(String money){
            this.money = money;
        }
        public String getMoney(){
            return this.money;
        }
        public void setUsername(String username){
            this.username = username;
        }
        public String getUsername(){
            return this.username;
        }
        public void setMobile(String mobile){
            this.mobile = mobile;
        }
        public String getMobile(){
            return this.mobile;
        }
        public void setAvatar(String avatar){
            this.avatar = avatar;
        }
        public String getAvatar(){
            return this.avatar;
        }

    }
    public class AccountList{
        private int id;

        private int uid;

        private int type;

        private int act;

        private String money;

        private String detail;

        private int left_money;

        private long addtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getLeft_money() {
            return left_money;
        }

        public void setLeft_money(int left_money) {
            this.left_money = left_money;
        }

        public long getAddtime() {
            return addtime;
        }

        public void setAddtime(long addtime) {
            this.addtime = addtime;
        }
    }
}
