package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/8/14.
 */

public class FootprintListBean {
    private String id;

    private String type;

    private int typeid;

    private String addtime;

    private FootprintGoods goods;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setTypeid(int typeid){
        this.typeid = typeid;
    }
    public int getTypeid(){
        return this.typeid;
    }
    public void setAddtime(String addtime){
        this.addtime = addtime;
    }
    public String getAddtime(){
        return this.addtime;
    }
    public void setGoods(FootprintGoods goods){
        this.goods = goods;
    }
    public FootprintGoods getGoods(){
        return this.goods;
    }
    public class FootprintGoods {
        private String title;

        private String litpic;

        private float price;

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
        public void setPrice(float price){
            this.price = price;
        }
        public float getPrice(){
            return this.price;
        }

    }
}
