package com.yizhisha.taosha.bean.json;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by lan on 2017/7/31.
 */

public class CommentBean implements MultiItemEntity {
    public static final int TEXT_TYPE = 1;
    public static final int IMGS_TYPE = 3;
    private int itemType;

    private int comment_id;

    private int comment_uid;

    private float comment_stars;

    private String comment_detail;

    private List<String> commentPhotos;
    private String comment_photos;

    private int comment_addtime;

    private String comment_redetail;

    private int comment_retime;

    private String comment_detail_add;

    private int comment_addtime_add;

    private String detail;

    private String mobile;

    private String avatar;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getComment_uid() {
        return comment_uid;
    }

    public void setComment_uid(int comment_uid) {
        this.comment_uid = comment_uid;
    }

    public float getComment_stars() {
        return comment_stars;
    }

    public void setComment_stars(float comment_stars) {
        this.comment_stars = comment_stars;
    }

    public String getComment_detail() {
        return comment_detail;
    }

    public void setComment_detail(String comment_detail) {
        this.comment_detail = comment_detail;
    }
    public List<String> getcommentPhotos() {
        return commentPhotos;
    }

    public void setcommentPhotos(List<String> comment_photos) {
        this.commentPhotos = comment_photos;
    }

    public void setComment_photos(String comment_photos) {
        this.comment_photos = comment_photos;
    }

    public String getComment_photos() {
        return comment_photos;
    }

    public int getComment_addtime() {
        return comment_addtime;
    }

    public void setComment_addtime(int comment_addtime) {
        this.comment_addtime = comment_addtime;
    }

    public String getComment_redetail() {
        return comment_redetail;
    }

    public void setComment_redetail(String comment_redetail) {
        this.comment_redetail = comment_redetail;
    }

    public int getComment_retime() {
        return comment_retime;
    }

    public void setComment_retime(int comment_retime) {
        this.comment_retime = comment_retime;
    }

    public String getComment_detail_add() {
        return comment_detail_add;
    }

    public void setComment_detail_add(String comment_detail_add) {
        this.comment_detail_add = comment_detail_add;
    }

    public int getComment_addtime_add() {
        return comment_addtime_add;
    }

    public void setComment_addtime_add(int comment_addtime_add) {
        this.comment_addtime_add = comment_addtime_add;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    @Override
    public int getItemType() {
        return itemType;
    }
}
