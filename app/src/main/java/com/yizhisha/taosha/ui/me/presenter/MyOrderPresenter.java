package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
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
                        if(myOrderListBean.getStatus().equals("y")&&myOrderListBean.getOrder().size()>0){
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

    @Override
    public void cancleOrder(Map<String, String> param) {
        addSubscrebe(Api.getInstance().cancelOrder(param), new RxSubscriber<RequestStatusBean>(mContext, true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.cancleOrder(requestStatusBean.getInfo());
                }else{
                    mView.cancelFail(requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.cancelFail(message);
            }
        });
    }

    @Override
    public void sureGoods(Map<String, String> param) {
        addSubscrebe(Api.getInstance().sureGoods(param), new RxSubscriber<RequestStatusBean>(mContext, true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.sureGoodsSuuccess(requestStatusBean.getInfo());
                }else{
                    mView.cancelFail(requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.cancelFail(message);
            }
        });
    }
}
