package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.MyCommentBean;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.ui.me.contract.MyRatingContract;

/**
 * Created by Administrator on 2017/8/12 0012.
 */

public class MyRatingPresenter extends MyRatingContract.Presenter{
    @Override
    public void loadComment(int uid, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadMyComment(uid),
                new RxSubscriber<MyCommentBean>(mContext, false) {
                    @Override
                    protected void onSuccess(MyCommentBean info) {
                        mView.hideLoading();
                        if(info.getStatus().equals("y")&&info.getCommentList().size()>0){
                            mView.loadCommentSuccess(info.getCommentList());
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
