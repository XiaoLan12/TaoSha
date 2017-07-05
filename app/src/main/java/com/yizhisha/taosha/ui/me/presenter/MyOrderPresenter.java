package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.ui.me.contract.MyOrderContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/5.
 */

public class MyOrderPresenter extends MyOrderContract.Presenter{
    @Override
    public void loadOrder(Map<String, String> param, boolean isShowLoad) {
        addSubscrebe(Api.getInstance().loadOrderList(param),
                new RxSubscriber<MyOrderListBean>(mContext, false) {
                    @Override
                    protected void onSuccess(MyOrderListBean myOrderListBean) {

                    }
                    @Override
                    protected void onFailure(String message) {

                    }
                });
    }
}
