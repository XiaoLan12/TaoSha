package com.yizhisha.taosha.ui.shoppcart.presenter;

import android.util.Log;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.ShopcartListBean;
import com.yizhisha.taosha.ui.shoppcart.contract.ShoppCartContract;
import com.yizhisha.taosha.utils.LogUtil;

import java.util.Map;

/**
 * Created by lan on 2017/7/10.
 */

public class ShoppCartPresenter extends ShoppCartContract.Presenter{
    @Override
    public void loadShoppCart(int uid, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getShoppCart(uid),
                new RxSubscriber<ShopcartListBean>(mContext, false) {
                    @Override
                    protected void onSuccess(ShopcartListBean bean) {
                        mView.hideLoading();
                        if(bean.getStatus()==null&&bean.getShopcart().size()>0){
                            mView.loadSuccess(bean.getShopcart());
                        }else if(bean.getStatus()!=null&&bean.getStatus().equals("n")){
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
    public void deleteShoppCart(Map<String, String> map) {
        addSubscrebe(Api.getInstance().deleteShoppCart(map),new RxSubscriber<RequestStatusBean>(mContext,true){

            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if("y".equals(requestStatusBean.getStatus())){
                    mView.deleteShoppCart(requestStatusBean.getInfo());
                }else{
                    mView.deleteFail(requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.deleteFail(message);
            }
        });
    }

    @Override
    public void deleteOneShoppCart(Map<String, String> map, final int groupPosition, final int childPosition) {
        addSubscrebe(Api.getInstance().deleteShoppCart(map),new RxSubscriber<RequestStatusBean>(mContext,true){

            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if("y".equals(requestStatusBean.getStatus())){
                    mView.deleteOneShoppCart(requestStatusBean.getInfo(),groupPosition,childPosition);
                }else{
                    mView.deleteFail(requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.deleteFail(message);
            }
        });
    }
}
