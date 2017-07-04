package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.ChangePersonalDataContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/4.
 */

public class ChangePersonalDataPresenter extends ChangePersonalDataContract.Presenter{
    @Override
    public void changePersonalData(Map<String, String> map) {
        addSubscrebe(Api.getInstance().changePersonalData(map),
                new RxSubscriber<RequestStatusBean>(mContext,"正在修改....",true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.changePersonalDataSuccess(requestStatusBean);
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
