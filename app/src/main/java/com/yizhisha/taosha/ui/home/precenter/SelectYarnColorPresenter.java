package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.home.contract.SelectYarnColorContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/13 0013.
 */

public class SelectYarnColorPresenter extends SelectYarnColorContract.Presenter {
    @Override
    public void changeShopCart(Map<String, String> map) {
        addSubscrebe(Api.getInstance().changeShopCart(map),
                new RxSubscriber<RequestStatusBean>(mContext, true) {
                    @Override
                    protected void onSuccess(RequestStatusBean info) {
                        if(info.getStatus().equals("y")){
                            mView.changeShopCartSuccess(info.getInfo());
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
