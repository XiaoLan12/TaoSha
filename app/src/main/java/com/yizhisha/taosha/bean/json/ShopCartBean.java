package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/8/11.
 */

public class ShopCartBean {
    private String title;

    private String pname;

    private String price;

    private String gid;

    private List<String> seka ;

    private List<ShopcartList> shopcart ;

    private String sid;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public List<String> getSeka() {
        return seka;
    }

    public void setSeka(List<String> seka) {
        this.seka = seka;
    }

    public List<ShopcartList> getShopcart() {
        return shopcart;
    }

    public void setShopcart(List<ShopcartList> shopcart) {
        this.shopcart = shopcart;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public class ShopcartList{
        private String detail;

        private int amount;

        private boolean isAdd;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public boolean isAdd() {
            return isAdd;
        }

        public void setAdd(boolean add) {
            isAdd = add;
        }

        @Override
        public String toString() {
            return "ShopcartList{" +
                    "detail='" + detail + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShopCartBean{" +
                "title='" + title + '\'' +
                ", pname='" + pname + '\'' +
                ", price='" + price + '\'' +
                ", gid='" + gid + '\'' +
                ", seka=" + seka +
                ", shopcart=" + shopcart +
                ", sid='" + sid + '\'' +
                '}';
    }
}
