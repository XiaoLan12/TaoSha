package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/7/3.
 */

public class CollectListBean {

    private List<Favorite> favorite;

    public List<Favorite> getData() {
        return favorite;
    }

    public void setData(List<Favorite> favorite) {
        this.favorite = favorite;
    }


    public class Favorite {


        private String id;

        private String uid;

        private String gid;

        private String title;

        private String pname;

        private String price;

        private String litpic;

        private String addtime;

        private String ingredient;

        private String needle_name;

        private String price_real;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

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

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        public String getNeedle_name() {
            return needle_name;
        }

        public void setNeedle_name(String needle_name) {
            this.needle_name = needle_name;
        }

        public String getPrice_real() {
            return price_real;
        }

        public void setPrice_real(String price_real) {
            this.price_real = price_real;
        }

        @Override
        public String toString() {
            return "CollectListBean{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", gid='" + gid + '\'' +
                    ", title='" + title + '\'' +
                    ", pname='" + pname + '\'' +
                    ", price='" + price + '\'' +
                    ", litpic='" + litpic + '\'' +
                    ", addtime='" + addtime + '\'' +
                    ", ingredient='" + ingredient + '\'' +
                    ", needle_name='" + needle_name + '\'' +
                    ", price_real='" + price_real + '\'' +
                    '}';
        }
    }
}
