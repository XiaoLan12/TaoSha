package com.yizhisha.taosha.ui.me.presenter;

import android.util.Log;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;
import com.yizhisha.taosha.ui.me.contract.OrderDetailsContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/9 0009.
 */

public class OrderDetailsPresenter extends OrderDetailsContract.Presenter{
    @Override
    public void loadOrderDetails(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
          addSubscrebe(Api.getInstance().loadOrderDetails(param), new RxSubscriber<MyOrderListBean>(mContext,false) {
              @Override
              protected void onSuccess(MyOrderListBean myOrderListBean) {
                  mView.hideLoading();
                  if(myOrderListBean.getStatus().equals("y")&&myOrderListBean.getOrder().size()>0){
                      mView.loadoSuccess(myOrderListBean.getOrder());
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
    public void changePayWay(Map<String, String> param) {
        addSubscrebe(Api.getInstance().changePayWay(param), new RxSubscriber<RequestStatusBean>(mContext, true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.changePayWaySuccess(requestStatusBean.getInfo());
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
    //微信支付
    @Override
    public void weChatPay(Map<String, String> param) {
        addSubscrebe(Api.getInstance().weChatPay(param), new RxSubscriber<PayReqBean>(mContext, true) {
            @Override
            protected void onSuccess(PayReqBean bean) {
                mView.hideLoading();
                if(bean.getStatus()!=null&&!bean.getStatus().equals("y")){
                    mView.cancelFail("获取数据失败");

                }else{
                    mView.weChatPay(bean);
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.loadFail(message);
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
                mView.loadFail(message);
            }
        });
    }
}
