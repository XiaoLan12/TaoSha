package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.SeckillListBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;
import com.yizhisha.taosha.ui.me.contract.OrderDetailsContract;
import com.yizhisha.taosha.ui.me.contract.SecKillOrderDetailsContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/9 0009.
 */

public class SecKillOrderDetailsPresenter extends SecKillOrderDetailsContract.Presenter{

    @Override
    public void loadSecKillOrderDetails(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getSeckillOrderDetail(param), new RxSubscriber<SeckillListBean>(mContext,false) {
            @Override
            protected void onSuccess(SeckillListBean seckillListBean) {
                mView.hideLoading();
                if(seckillListBean.getStatus().equals("y")&&seckillListBean.getSeckilling().size()>0){
                    mView.loadoSecKillOrderSuccess(seckillListBean.getSeckilling());
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
        addSubscrebe(Api.getInstance().cancelSkillOrder(param), new RxSubscriber<RequestStatusBean>(mContext, true) {
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
    public void weChatPay(Map<String, String> param) {
        addSubscrebe(Api.getInstance().weChatPay(param), new RxSubscriber<PayReqBean>(mContext, true) {
            @Override
            protected void onSuccess(PayReqBean bean) {
                mView.hideLoading();
                if(bean.getStatus()!=null&&!bean.getStatus().equals("y")){
                    mView.cancelFail("数据加载失败");

                }else{
                    mView.weChatPay(bean);
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.cancelFail(message);
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
                mView.cancelFail(message);
            }
        });
    }
}
