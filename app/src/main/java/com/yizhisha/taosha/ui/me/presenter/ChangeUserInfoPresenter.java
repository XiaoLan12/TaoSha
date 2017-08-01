package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.ChangeUserInfoContract;

import java.util.Map;

/**
 * Created by lan on 2017/8/1.
 */

public class ChangeUserInfoPresenter extends ChangeUserInfoContract.Presenter{
    @Override
    public void changeUserInfo(Map<String, String> param) {
        addSubscrebe(Api.getInstance().changeUserInfo(param),new RxSubscriber<RequestStatusBean>(mContext,true){
            @Override
            protected void onSuccess(RequestStatusBean info) {
                if(info!=null){
                    if(info.getStatus().equals("y")){
                        mView.changeSuccess(info.getInfo());
                    }else{
                        mView.loadFail(info.getInfo());
                    }
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
