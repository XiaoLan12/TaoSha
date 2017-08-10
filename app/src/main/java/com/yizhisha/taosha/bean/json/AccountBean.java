package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/8/10.
 */

public class AccountBean {
    private float money;
    private List<AccountList> detail ;

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public List<AccountList> getDetail() {
        return detail;
    }

    public void setDetail(List<AccountList> detail) {
        this.detail = detail;
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
