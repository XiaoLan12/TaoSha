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
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadOrderList(param),
                new RxSubscriber<MyOrderListBean>(mContext, false) {
                    @Override
                    protected void onSuccess(MyOrderListBean myOrderListBean) {
                        mView.hideLoading();
                        if(myOrderListBean!=null&&myOrderListBean.getOrder().size()>0){
                            mView.loadOrderSuccess(myOrderListBean.getOrder());
                        }else{
                            mView.showEmpty();
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                            mView.hideLoading();
                            mView.loadFail(message);
                    }
                });
    }
}
