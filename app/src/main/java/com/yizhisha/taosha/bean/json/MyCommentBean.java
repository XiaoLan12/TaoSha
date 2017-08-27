package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12 0012.
 */

public class MyCommentBean{
    private List<MyCommentListBean> commentList;
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public List<MyCommentListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<MyCommentListBean> commentList) {
        this.commentList = commentList;
    }
}
