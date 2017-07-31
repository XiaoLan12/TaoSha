package com.yizhisha.taosha.ui.login.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.login.contract.PhoneLoginContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/31.
 */

public class PhoneLoginPresenter extends PhoneLoginContract.Presenter{
    @Override
    public void phoneLogin(Map<String, String> map) {
        addSubscrebe(Api.getInstance().phoneLogin(map),new RxSubscriber<RequestStatusBean>(mContext, "正在登录....", true){
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.loginSuccess(requestStatusBean);
                }else{
                    mView.loadFail(requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void getCode(Map<String, String> map) {
        addSubscrebe(Api.getInstance().getPhoneLoginCode(map),
                new RxSubscriber<RequestStatusBean>(mContext,false) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        if(requestStatusBean.getStatus().equals("y")){
                            mView.getCodeSuccess(requestStatusBean.getInfo());
                        }else{
                            mView.loadFail(requestStatusBean.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }
}
