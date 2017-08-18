package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/8/15.
 */

public class ShopCartOrderSureBean {
    private List<Address> address ;

    private List<Shopcart> shopcart ;
    private int price;

    private int amount;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<Shopcart> getShopcart() {
        return shopcart;
    }

    public void setShopcart(List<Shopcart> shopcart) {
        this.shopcart = shopcart;
    }

    public class Address {
    private String id;

    private String uid;

    private String linkman;

    private String mobile;

    private String area_pid;

    private String area_sid;

    private String area_pname;

    private String area_sname;

    private String address;

    private String zipcode;

    private String index;

    private String area_app;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    public void setLinkman(String linkman){
        this.linkman = linkman;
    }
    public String getLinkman(){
        return this.linkman;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return this.mobile;
    }
    public void setArea_pid(String area_pid){
        this.area_pid = area_pid;
    }
    public String getArea_pid(){
        return this.area_pid;
    }
    public void setArea_sid(String area_sid){
        this.area_sid = area_sid;
    }
    public String getArea_sid(){
        return this.area_sid;
    }
    public void setArea_pname(String area_pname){
        this.area_pname = area_pname;
    }
    public String getArea_pname(){
        return this.area_pname;
    }
    public void setArea_sname(String area_sname){
        this.area_sname = area_sname;
    }
    public String getArea_sname(){
        return this.area_sname;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setZipcode(String zipcode){
        this.zipcode = zipcode;
    }
    public String getZipcode(){
        return this.zipcode;
    }
    public void setIndex(String index){
        this.index = index;
    }
    public String getIndex(){
        return this.index;
    }
    public void setArea_app(String area_app){
        this.area_app = area_app;
    }
    public String getArea_app(){
        return this.area_app;
    }

}
    public class Shopcart {
        private String id;

        private String title;

        private String gid;

        private String price;

        private String litpic;

        private String amount;

        private String detail;

        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setGid(String gid){
            this.gid = gid;
        }
        public String getGid(){
            return this.gid;
        }
        public void setPrice(String price){
            this.price = price;
        }
        public String getPrice(){
            return this.price;
        }
        public void setLitpic(String litpic){
            this.litpic = litpic;
        }
        public String getLitpic(){
            return this.litpic;
        }
        public void setAmount(String amount){
            this.amount = amount;
        }
        public String getAmount(){
            return this.amount;
        }
        public void setDetail(String detail){
            this.detail = detail;
        }
        public String getDetail(){
            return this.detail;
        }

    }
}