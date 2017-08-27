package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.CommentListBean;
import com.yizhisha.taosha.ui.home.contract.CommentYarnContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/31.
 */

public class CommentYarnPresenter extends CommentYarnContract.Presenter{
    @Override
    public void loadCommentList(Map<String, String> map,boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getCommentList(map),new RxSubscriber<CommentListBean>(mContext,false){
            @Override
            protected void onSuccess(CommentListBean data) {
                mView.hideLoading();
                if(data.getStatus().equals("y")&data.getComment().size()>0){
                    mView.loadCommentListSuccess(data.getComment());
                }else{
                    mView.showEmpty();
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
