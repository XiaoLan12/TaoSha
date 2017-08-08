package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.UserInfoBean;
import com.yizhisha.taosha.ui.me.contract.MeContract;

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
                        mView.getUserInfoSuccess(userInfoBean);
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }
}
