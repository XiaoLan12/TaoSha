package com.yizhisha.taosha.ui.shoppcart.presenter;

import android.util.Log;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.ShopcartListBean;
import com.yizhisha.taosha.ui.shoppcart.contract.ShoppCartContract;

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
                    protected void onSuccess(ShopcartListBean shopcartListBean) {
                        mView.hideLoading();
                        if(shopcartListBean!=null&&shopcartListBean.getShopcart().size()>0){
                            mView.loadSuccess(shopcartListBean.getShopcart());
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
    public void deleteShoppCart(int id) {

    }
}
