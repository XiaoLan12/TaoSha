package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/8/18.
 */

public class HotCommendBean {
    private int info_s;

    private String info_t;

    private String pname;

    private List<Goods> goods ;

    public int getInfo_s() {
        return info_s;
    }

    public void setInfo_s(int info_s) {
        this.info_s = info_s;
    }

    public String getInfo_t() {
        return info_t;
    }

    public void setInfo_t(String info_t) {
        this.info_t = info_t;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public class Goods {
        private int id;

        private String title;

        private String litpic;

        private String price;

        private String price_real;

        private String ingredient;

        private String needle_name;

        private String yam;

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
        public void setLitpic(String litpic){
            this.litpic = litpic;
        }
        public String getLitpic(){
            return this.litpic;
        }
        public void setPrice(String price){
            this.price = price;
        }
        public String getPrice(){
            return this.price;
        }
        public void setPrice_real(String price_real){
            this.price_real = price_real;
        }
        public String getPrice_real(){
            return this.price_real;
        }
        public void setIngredient(String ingredient){
            this.ingredient = ingredient;
        }
        public String getIngredient(){
            return this.ingredient;
        }
        public void setNeedle_name(String needle_name){
            this.needle_name = needle_name;
        }
        public String getNeedle_name(){
            return this.needle_name;
        }
        public void setYam(String yam){
            this.yam = yam;
        }
        public String getYam(){
            return this.yam;
        }

    }
}
