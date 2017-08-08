package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.CommentPicBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.AddCommentContract;

import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by lan on 2017/8/8.
 */

public class AddCommentPresenter extends AddCommentContract.Presenter{
    @Override
    public void addComment(Map<String, String> map) {
        addSubscrebe(Api.getInstance().addComment(map),
                new RxSubscriber<RequestStatusBean>(mContext, false) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        mView.hideLoading();
                        if(requestStatusBean!=null&&requestStatusBean.getStatus().equals("y")){
                            mView.addCommentSuccess(requestStatusBean.getInfo());
                        }else{
                            mView.loadFail(requestStatusBean.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.hideLoading();
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void addAddComment(Map<String, String> map) {
        addSubscrebe(Api.getInstance().addAddComment(map),
                new RxSubscriber<RequestStatusBean>(mContext, false) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        mView.hideLoading();
                        if(requestStatusBean!=null&&requestStatusBean.getStatus().equals("y")){
                            mView.addAddCommentSuccess(requestStatusBean.getInfo());
                        }else{
                            mView.loadFail(requestStatusBean.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.hideLoading();
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void addCommentPic(MultipartBody.Part body) {

        addSubscrebe(Api.getInstance().addCommentPic(body),
                new RxSubscriber<CommentPicBean>(mContext, false) {
                    @Override
                    protected void onSuccess(CommentPicBean commentPicBean) {
                        mView.hideLoading();
                        if(commentPicBean!=null&&!commentPicBean.getCommentPic().equals("")){
                            mView.addCommentPicSuccess(commentPicBean.getCommentPic());
                        }else{
                            mView.loadFail("发布失败");
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.hideLoading();
                        mView.loadFail(message);
                    }
                });
    }
}
