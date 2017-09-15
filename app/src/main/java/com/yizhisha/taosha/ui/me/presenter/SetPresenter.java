package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WechatBean;
import com.yizhisha.taosha.bean.json.WechatInfoBean;
import com.yizhisha.taosha.ui.me.contract.SetContract;

import java.util.Map;

/**
 * Created by lan on 2017/8/10.
 */

public class SetPresenter extends SetContract.Presenter{
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
    public void bindWeChat(Map<String, String> map) {
        addSubscrebe(Api.getInstance().bindWeChat(map),
                new RxSubscriber<RequestStatusBean>(mContext, false) {
                    @Override
                    protected void onSuccess(RequestStatusBean info) {
                        if(info.getStatus().equals("y")){
                            mView.bindWeChat(info.getInfo());
                        }else{
                            mView.loadFail(info.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void loadWeChatInfo(String url) {
        addSubscrebe(Api.getInstance().getWeChatInfo(url),
                new RxSubscriber<WechatInfoBean>(mContext, true) {
                    @Override
                    protected void onSuccess(WechatInfoBean bean) {
                            mView.loadWeChatInfo(bean);
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void loadBindWeChat(int uid) {
        addSubscrebe(Api.getInstance().loadBindWeChat(uid),
                new RxSubscriber<RequestStatusBean>(mContext, true) {
                    @Override
                    protected void onSuccess(RequestStatusBean bean) {
                        mView.loadBindWeChat(bean);
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void unBindWeChat(int uid) {
        addSubscrebe(Api.getInstance().unBindWeChat(uid),
                new RxSubscriber<RequestStatusBean>(mContext, true) {
                    @Override
                    protected void onSuccess(RequestStatusBean info) {
                        if(info.getStatus().equals("y")){
                            mView.unBindWeChat(info.getInfo());
                        }else{
                            mView.unBindWeChatFail(info.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }

    @Override
    public void showBindWeChart(int uid) {
        addSubscrebe(Api.getInstance().showBindWeChart(uid),
                new RxSubscriber<RequestStatusBean>(mContext, true) {
                    @Override
                    protected void onSuccess(RequestStatusBean info) {
                            mView.showBindWeChart(info);

                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }
}
