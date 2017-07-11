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
                        Log.d("TTT","都");
                        if(shopcartListBean!=null&&shopcartListBean.getShopcart().size()>0){
                            Log.d("TTT","都1");
                            mView.loadSuccess(shopcartListBean.getShopcart());
                        }else{
                            Log.d("TTT","都2");
                            mView.showEmpty();
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                        Log.d("TTT","都3");
                        mView.hideLoading();
                        mView.loadFail(message);
                    }
                });
    }
    @Override
    public void deleteShoppCart(int id) {

    }
}
