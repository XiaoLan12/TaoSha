package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.bean.json.ShopCartOrderSureBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;
import com.yizhisha.taosha.ui.home.contract.SureOrderContract;

import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by lan on 2017/8/14.
 */

public class SureOrderPresenter extends SureOrderContract.Presenter{
    @Override
    public void loadOrderSure(Map<String, String> param) {

        addSubscrebe(Api.getInstance().orderSure(param), new RxSubscriber<OrderSureBean>(mContext, false) {
            @Override
            protected void onSuccess(OrderSureBean bean) {
                if(bean!=null){
                    mView.loadOrderSuccess(bean);
                }else{
                    mView.loadFail("数据加载失败");
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void loadShopCartOrderSure(Map<String, String> param) {
        addSubscrebe(Api.getInstance().shopCartOrderSure(param), new RxSubscriber<ShopCartOrderSureBean>(mContext, false) {
            @Override
            protected void onSuccess(ShopCartOrderSureBean bean) {
                if(bean!=null){
                    mView.loadShopCartOrderSuccess(bean);
                }else{
                    mView.loadFail("数据加载失败");
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void weChatPay(Map<String, String> param) {
        addSubscrebe(Api.getInstance().weChatPay(param), new RxSubscriber<PayReqBean>(mContext, true) {
            @Override
            protected void onSuccess(PayReqBean bean) {
                mView.hideLoading();
                if(bean!=null){
                    mView.weChatPay(bean);
                }else{
                    mView.loadFail("数据加载失败");
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
    public void regularOrder(Map<String, String> param) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().regularOrder(param), new RxSubscriber<RequestStatusBean>(mContext, false) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.regularOrderSuccess(bean.getOrderno());
                }else{
                    mView.hideLoading();
                    mView.loadFail("数据加载失败");
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
    public void loadWeChatPayState(Map<String, String> param) {
        addSubscrebe(Api.getInstance().weChatPayState(param), new RxSubscriber<WeChatPayStateBean>(mContext, true) {
            @Override
            protected void onSuccess(WeChatPayStateBean bean) {
                if(bean.getResult_code().equals("SUCCESS")){
                    mView.loadWeChatPayState(bean);
                }else{
                    mView.loadWeChatPayStateFail(bean.getReturn_msg());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
