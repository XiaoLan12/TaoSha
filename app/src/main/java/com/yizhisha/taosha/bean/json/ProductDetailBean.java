package com.yizhisha.taosha.bean.json;

import org.w3c.dom.Comment;

import java.util.List;

/**
 * Created by Administrator on 2017/7/9.
 */

public class ProductDetailBean{
    private String status;

    private String info;

    private String  favorite;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    private ProductDeatilItemBean goods;

    private Comment comment;


    public ProductDeatilItemBean getGoods() {
        return goods;
    }

    public void setGoods(ProductDeatilItemBean goods) {
        this.goods = goods;
    }

    public Comment getComment() {
        return comment;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public class Comment{
        private String  comment_detail;
        private String  mobile;
        private String  avatar;
        private int  count;

        public String getComment_detail() {
            return comment_detail;
        }

        public void setComment_detail(String comment_detail) {
            this.comment_detail = comment_detail;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    @Override
    public String toString() {
        return "ProductDetailBean{" +
                ", goods=" + goods +
                '}';
    }
}
