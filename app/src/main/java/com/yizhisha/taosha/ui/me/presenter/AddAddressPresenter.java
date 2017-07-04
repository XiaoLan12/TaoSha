package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.AddAddressContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/4.
 */

public class AddAddressPresenter extends AddAddressContract.Presenter{
    @Override
    public void addAddress(Map<String, String> map) {
        addSubscrebe(Api.getInstance().addAddress(map), new RxSubscriber<RequestStatusBean>(mContext,"请稍后....",true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.addAddressSuccess(requestStatusBean);
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
