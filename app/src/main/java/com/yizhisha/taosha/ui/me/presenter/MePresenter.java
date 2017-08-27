package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.UserHeadBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;
import com.yizhisha.taosha.ui.me.contract.MeContract;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by lan on 2017/8/8.
 */

public class MePresenter extends MeContract.Presenter{
    @Override
    public void getUserInfo(int uid) {
        addSubscrebe(Api.getInstance().loadUserInfo(uid),
                new RxSubscriber<UserInfoBean>(mContext, "载入中...", true) {
                    @Override
                    protected void onSuccess(UserInfoBean userInfoBean) {
                        if(userInfoBean.getStatus().equals("y")) {
                            mView.getUserInfoSuccess(userInfoBean);
                        }else{
                            mView.loadFail(userInfoBean.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void changeHeadSuccess(RequestBody uid, MultipartBody.Part body) {
        addSubscrebe(Api.getInstance().changeUserHead(uid,body),new RxSubscriber<UserHeadBean>(mContext,true){
            @Override
            protected void onSuccess(UserHeadBean info) {
                if(info.getStatus().equals("y")) {
                    mView.changeHeadSuccess(info);
                } else{
                    mView.loadFail(info.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
