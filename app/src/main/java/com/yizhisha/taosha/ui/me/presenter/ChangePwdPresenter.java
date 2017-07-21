package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.ChangePwdContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/18.
 */

public class ChangePwdPresenter extends ChangePwdContract.Presenter{
    @Override
    public void changePwd(Map<String, String> param) {
        addSubscrebe(Api.getInstance().changePwd(param),
                new RxSubscriber<RequestStatusBean>(mContext, true) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        if("y".equals(requestStatusBean.getStatus())){
                            mView.changePwdSuccess(requestStatusBean.getInfo());
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
