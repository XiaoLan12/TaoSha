package com.yizhisha.taosha.ui.login.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WechatBean;
import com.yizhisha.taosha.bean.json.WechatInfoBean;
import com.yizhisha.taosha.ui.login.contract.LoginContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/5.
 */

public class LoginPresenter extends LoginContract.Presenter{
    @Override
    public void login(Map<String, String> map) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().login(map),
                new RxSubscriber<RequestStatusBean>(mContext,false) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        if(requestStatusBean.getStatus().equals("y")){
                            mView.loginSuccess(requestStatusBean);
                        }else{
                            mView.hideLoading();
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

    @Override
    public void loadWeChatData(String url) {
        addSubscrebe(Api.getInstance().getWeChatLoginData(url),
                new RxSubscriber<WechatBean>(mContext, false) {
                    @Override
                    protected void onSuccess(WechatBean wechatBean) {
                        if(wechatBean!=null){
                            mView.loadWeChatData(wechatBean);
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void weChatLogin(Map<String, String> map) {
        addSubscrebe(Api.getInstance().weChatLogin(map),
                new RxSubscriber<RequestStatusBean>(mContext, false) {
                    @Override
                    protected void onSuccess(RequestStatusBean info) {
                        if(info.getStatus().equals("y")){
                            mView.weChatLoginSuccess(info);
                        }else{
                            mView.weChatLogin(info.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void bindWeChat(Map<String, String> map) {
        addSubscrebe(Api.getInstance().bindWeChat(map),
                new RxSubscriber<RequestStatusBean>(mContext, false) {
                    @Override
                    protected void onSuccess(RequestStatusBean info) {
                        mView.hideLoading();
                        if(info.getStatus().equals("y")){
                            mView.bindWeChatSuccess(info.getInfo());
                        }else{
                            mView.loadFail(info.getInfo());
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
    public void loadWeChatInfo(String url) {
        addSubscrebe(Api.getInstance().getWeChatInfo(url),
                new RxSubscriber<WechatInfoBean>(mContext, false) {
                    @Override
                    protected void onSuccess(WechatInfoBean bean) {
                        mView.loadWeChatInfo(bean);
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.hideLoading();
                        mView.loadFail(message);
                    }
                });
    }
}
