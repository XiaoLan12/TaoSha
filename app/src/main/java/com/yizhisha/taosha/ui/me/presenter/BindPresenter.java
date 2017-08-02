package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.BindContract;

import java.util.Map;

/**
 * Created by lan on 2017/8/2.
 */

public class BindPresenter extends BindContract.Presenter{
    @Override
    public void bing(Map<String, String> params) {
        addSubscrebe(Api.getInstance().bindPhone(params),
                new RxSubscriber<RequestStatusBean>(mContext,true) {
                    @Override
                    protected void onSuccess(RequestStatusBean o) {
                        if(o.getStatus().equals("y")){
                            mView.bindSuccess(o.getInfo());
                        }else {
                            mView.loadFail(o.getInfo());
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
