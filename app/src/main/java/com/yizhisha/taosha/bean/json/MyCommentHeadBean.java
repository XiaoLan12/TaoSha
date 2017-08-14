package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/8/14.
 */

public class MyCommentHeadBean {
    private long comment_addtime;
    private String orderno;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public long getComment_addtime() {
        return comment_addtime;
    }

    public void setComment_addtime(long comment_addtime) {
        this.comment_addtime = comment_addtime;
    }
}
