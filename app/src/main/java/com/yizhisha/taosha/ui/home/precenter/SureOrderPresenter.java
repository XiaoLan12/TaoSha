package com.yizhisha.taosha.ui.home.precenter;

import android.util.Log;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.bean.json.SeckillOrderSureBean;
import com.yizhisha.taosha.bean.json.ShopCartOrderSureBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;
import com.yizhisha.taosha.ui.home.contract.SureOrderContract;

import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by lan on 2017/8/14.
 */

public class SureOrderPresenter extends SureOrderContract.Presenter{
    //加载普通商品和板毛确认订单
    @Override
    public void loadOrderSure(Map<String, String> param) {
        addSubscrebe(Api.getInstance().orderSure(param), new RxSubscriber<OrderSureBean>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(OrderSureBean bean) {
              if(bean.getStatus().equals("y")){
                    mView.loadOrderSuccess(bean);
                }else{
                    mView.loadFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
    //加载购物车确认订单
    @Override
    public void loadShopCartOrderSure(Map<String, String> param) {
        addSubscrebe(Api.getInstance().shopCartOrderSure(param), new RxSubscriber<ShopCartOrderSureBean>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(ShopCartOrderSureBean bean) {
              if(bean.getStatus().equals("y")){
                    mView.loadShopCartOrderSuccess(bean);
                }else{
                    mView.loadFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
    //加载秒纱确认订单
    @Override
    public void loadSeckillOrder(Map<String, String> param) {
        addSubscrebe(Api.getInstance().seckillOrderSure(param), new RxSubscriber<SeckillOrderSureBean>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(SeckillOrderSureBean bean) {
             if(bean.getStatus().equals("y")){
                    mView.loadSeckillOrderSuccess(bean);
                }else{
                    mView.loadFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
    //加载拿样确认订单
    @Override
    public void loadNayangOrderOrder(Map<String, String> param) {
        addSubscrebe(Api.getInstance().nayangOrderSure(param), new RxSubscriber<AddressListBean>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(AddressListBean bean) {
             if(bean.getStatus().equals("y")){
                    mView.loadNayangOrderSuccess(bean.getAddress());
                }else{
                    mView.loadFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
    //微信支付
    @Override
    public void weChatPay(Map<String, String> param) {
        addSubscrebe(Api.getInstance().weChatPay(param), new RxSubscriber<PayReqBean>(mContext, true) {
            @Override
            protected void onSuccess(PayReqBean bean) {
                mView.hideLoading();
                if(bean.getStatus().equals("y")){
                    mView.weChatPay(bean);
                }else{
                    mView.createFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.createFail(message);
            }
        });
    }
    //普通商品和板毛创建订单
    @Override
    public void regularOrder(Map<String, String> param) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().regularOrder(param), new RxSubscriber<RequestStatusBean>(mContext, false) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.regularOrderSuccess(bean);
                }else{
                    mView.hideLoading();
                    mView.createFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.createFail(message);
            }
        });
    }
    //购物车创建订单
    @Override
    public void shopcartOrder(Map<String, String> param) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().shopCartCreateOrder(param), new RxSubscriber<RequestStatusBean>(mContext, false) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.regularOrderSuccess(bean);
                }else{
                    mView.hideLoading();
                    mView.createFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.createFail(message);
            }
        });
    }
    //拿样创建订单
    @Override
    public void nayangOrder(Map<String, String> param) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().naYangCreateOrder(param), new RxSubscriber<RequestStatusBean>(mContext, false) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.nayangOrderSuccess(bean.getInfo());
                }else{
                    mView.hideLoading();
                    mView.createFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.createFail(message);
            }
        });
    }
    //秒纱创建订单
    @Override
    public void seckillOrder(Map<String, String> param) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().seckillCreateOrder(param), new RxSubscriber<RequestStatusBean>(mContext, false) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.regularOrderSuccess(bean);
                }else{
                    mView.hideLoading();
                    mView.createFail(bean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.createFail(message);
            }
        });
    }
    //同步微信支付状态
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
                mView.createFail(message);
            }
        });
    }
}
