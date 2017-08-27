package com.yizhisha.taosha.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lan on 2017/8/17.
 */

public class SeckillProductBean implements Serializable{
    private Seckilling seckilling;

    private Goods goods;

    private long nowtime;
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
    public class Goods {
        private String mzw_uid;

        private String title;

        private String pname;

        private String code;

        private String litpic;

        private String needle_name;

        private String ingredient;

        private String yam;

        private String brand;

        private String weight;

        private String company;

        private List<String> seka;

        private String color;

        private String description;

        private List<String> content_;

        private List<String> album ;

        public void setMzw_uid(String mzw_uid){
            this.mzw_uid = mzw_uid;
        }
        public String getMzw_uid(){
            return this.mzw_uid;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setPname(String pname){
            this.pname = pname;
        }
        public String getPname(){
            return this.pname;
        }
        public void setCode(String code){
            this.code = code;
        }
        public String getCode(){
            return this.code;
        }
        public void setLitpic(String litpic){
            this.litpic = litpic;
        }
        public String getLitpic(){
            return this.litpic;
        }
        public void setNeedle_name(String needle_name){
            this.needle_name = needle_name;
        }
        public String getNeedle_name(){
            return this.needle_name;
        }
        public void setIngredient(String ingredient){
            this.ingredient = ingredient;
        }
        public String getIngredient(){
            return this.ingredient;
        }
        public void setYam(String yam){
            this.yam = yam;
        }
        public String getYam(){
            return this.yam;
        }
        public void setBrand(String brand){
            this.brand = brand;
        }
        public String getBrand(){
            return this.brand;
        }
        public void setWeight(String weight){
            this.weight = weight;
        }
        public String getWeight(){
            return this.weight;
        }
        public void setCompany(String company){
            this.company = company;
        }
        public String getCompany(){
            return this.company;
        }
        public void setSeka(List<String> seka){
            this.seka = seka;
        }
        public List<String> getSeka(){
            return this.seka;
        }
        public void setColor(String color){
            this.color = color;
        }
        public String getColor(){
            return this.color;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public void setContent(List<String> content_){
            this.content_ = content_;
        }
        public List<String> getContent(){
            return this.content_;
        }
        public void setAlbum(List<String> album){
            this.album = album;
        }
        public List<String> getAlbum(){
            return this.album;
        }

    }
    public class Seckilling {
        private int id;

        private String title;

        private String goods_id;

        private String price;

        private String market_price;

        private String discount;

        private long starttime;

        private long endtime;

        private String inventory;

        private String sales;

        private String is_show;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setGoods_id(String goods_id){
            this.goods_id = goods_id;
        }
        public String getGoods_id(){
            return this.goods_id;
        }
        public void setPrice(String price){
            this.price = price;
        }
        public String getPrice(){
            return this.price;
        }
        public void setMarket_price(String market_price){
            this.market_price = market_price;
        }
        public String getMarket_price(){
            return this.market_price;
        }
        public void setDiscount(String discount){
            this.discount = discount;
        }
        public String getDiscount(){
            return this.discount;
        }
        public void setStarttime(long starttime){
            this.starttime = starttime;
        }
        public long getStarttime(){
            return this.starttime;
        }
        public void setEndtime(long endtime){
            this.endtime = endtime;
        }
        public long getEndtime(){
            return this.endtime;
        }
        public void setInventory(String inventory){
            this.inventory = inventory;
        }
        public String getInventory(){
            return this.inventory;
        }
        public void setSales(String sales){
            this.sales = sales;
        }
        public String getSales(){
            return this.sales;
        }
        public void setIs_show(String is_show){
            this.is_show = is_show;
        }
        public String getIs_show(){
            return this.is_show;
        }

    }


    public Seckilling getSeckilling() {
        return seckilling;
    }

    public void setSeckilling(Seckilling seckilling) {
        this.seckilling = seckilling;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public long getNowtime() {
        return nowtime;
    }

    public void setNowtime(long nowtime) {
        this.nowtime = nowtime;
    }
}
