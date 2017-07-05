package com.yizhisha.taosha.ui.login.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.login.contract.LoginContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/5.
 */

public class LoginPresenter extends LoginContract.Presenter{
    @Override
    public void login(Map<String, String> map) {
        addSubscrebe(Api.getInstance().login(map),
                new RxSubscriber<RequestStatusBean>(mContext, "正在登录....", true) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        if(requestStatusBean.getStatus().equals("y")){
                            mView.loginSuccess(requestStatusBean.getInfo());
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
    public void register(Map<String, String> map) {
        addSubscrebe(Api.getInstance().register(map),
                new RxSubscriber<RequestStatusBean>(mContext,true) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        if(requestStatusBean.getStatus().equals("y")){
                            mView.registerSuccess(requestStatusBean.getInfo());
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
    public void findPwd(Map<String, String> map) {
        addSubscrebe(Api.getInstance().findPwd(map),
                new RxSubscriber<RequestStatusBean>(mContext,true) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        if(requestStatusBean.getStatus().equals("y")){
                            mView.findPwdSuccess(requestStatusBean.getInfo());
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
        addSubscrebe(Api.getInstance().getCode(map),
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
