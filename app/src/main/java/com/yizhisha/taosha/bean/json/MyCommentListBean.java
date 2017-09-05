package com.yizhisha.taosha.bean.json;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12 0012.
 */

public class MyCommentListBean implements MultiItemEntity {
    public static final int TEXT_TYPE = 1;
    public static final int IMGS_TYPE = 3;
    private List<Goods> goods;
    private MyComment comment;
    private int itemType;

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public MyComment getComment() {
        return comment;
    }

    public void setComment(MyComment comment) {
        this.comment = comment;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public class Goods{
        private int gid;
        private String title;
        private String pname;
        private String price;
        private String amount;
        private String litpic;
        private String detail;
        private float totalprice;
        private String remark;
        private String mobile;
        private String is_ship;
        private String orderno;

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public float getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(float totalprice) {
            this.totalprice = totalprice;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIs_ship() {
            return is_ship;
        }

        public void setIs_ship(String is_ship) {
            this.is_ship = is_ship;
        }
    }
    public class MyComment{
            private String comment_id;
            private String is_show;
            private String comment_uid;
            private String comment_mzw_uid;
            private String comment_order_id;
            private String comment_stars;
            private String comment_stars_quality;
            private String comment_stars_logistics;
            private String comment_stars_service;
            private String comment_detail;
            private String comment_photos;
            private List<String> commentPhotos;
            private long comment_addtime;
            private String comment_redetail;
            private long comment_retime;
            private String comment_detail_add;
            private long comment_addtime_add;

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getComment_uid() {
            return comment_uid;
        }

        public void setComment_uid(String comment_uid) {
            this.comment_uid = comment_uid;
        }

        public String getComment_mzw_uid() {
            return comment_mzw_uid;
        }

        public void setComment_mzw_uid(String comment_mzw_uid) {
            this.comment_mzw_uid = comment_mzw_uid;
        }

        public String getComment_order_id() {
            return comment_order_id;
        }

        public void setComment_order_id(String comment_order_id) {
            this.comment_order_id = comment_order_id;
        }

        public String getComment_stars() {
            return comment_stars;
        }

        public void setComment_stars(String comment_stars) {
            this.comment_stars = comment_stars;
        }

        public String getComment_stars_quality() {
            return comment_stars_quality;
        }

        public void setComment_stars_quality(String comment_stars_quality) {
            this.comment_stars_quality = comment_stars_quality;
        }

        public String getComment_stars_logistics() {
            return comment_stars_logistics;
        }

        public void setComment_stars_logistics(String comment_stars_logistics) {
            this.comment_stars_logistics = comment_stars_logistics;
        }

        public String getComment_stars_service() {
            return comment_stars_service;
        }

        public void setComment_stars_service(String comment_stars_service) {
            this.comment_stars_service = comment_stars_service;
        }

        public String getComment_detail() {
            return comment_detail;
        }

        public void setComment_detail(String comment_detail) {
            this.comment_detail = comment_detail;
        }

        public String getComment_photos() {
            return comment_photos;
        }

        public void setComment_photos(String comment_photos) {
            this.comment_photos = comment_photos;
        }

        public List<String> getCommentPhotos() {
            return commentPhotos;
        }

        public void setCommentPhotos(List<String> commentPhotos) {
            this.commentPhotos = commentPhotos;
        }

        public long getComment_addtime() {
            return comment_addtime;
        }

        public void setComment_addtime(long comment_addtime) {
            this.comment_addtime = comment_addtime;
        }

        public String getComment_redetail() {
            return comment_redetail;
        }

        public void setComment_redetail(String comment_redetail) {
            this.comment_redetail = comment_redetail;
        }

        public long getComment_retime() {
            return comment_retime;
        }

        public void setComment_retime(long comment_retime) {
            this.comment_retime = comment_retime;
        }

        public String getComment_detail_add() {
            return comment_detail_add;
        }

        public void setComment_detail_add(String comment_detail_add) {
            this.comment_detail_add = comment_detail_add;
        }

        public long getComment_addtime_add() {
            return comment_addtime_add;
        }

        public void setComment_addtime_add(long comment_addtime_add) {
            this.comment_addtime_add = comment_addtime_add;
        }
    }
}
