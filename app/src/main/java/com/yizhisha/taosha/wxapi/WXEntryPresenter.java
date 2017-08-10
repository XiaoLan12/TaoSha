package com.yizhisha.taosha.wxapi;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WechatBean;

import java.util.Map;

/**
 * Created by lan on 2017/8/10.
 */

public class WXEntryPresenter extends WXEntryContract.Presenter{
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
                            mView.weChatLogin(info.getInfo());
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
}
