package com.yizhisha.taosha.ui.shoppcart.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.ShopCartBean;
import com.yizhisha.taosha.ui.shoppcart.contract.SingleShopCartContract;

import java.util.Map;

/**
 * Created by lan on 2017/8/11.
 */

public class SingleShopCartPresenter extends SingleShopCartContract.Presenter{
    @Override
    public void loadSingleShoppCart(Map<String, String> map, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadSingleShpCart(map),
                new RxSubscriber<ShopCartBean>(mContext, false) {
                    @Override
                    protected void onSuccess(ShopCartBean shopCartBean) {
                        mView.hideLoading();
                        if(shopCartBean.getStatus().equals("y")){
                            mView.loadSuccess(shopCartBean);
                        }else{
                            mView.showEmpty();
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                            mView.loadFail(message);
                    }
                });
    }

    @Override
    public void changeShopCart(Map<String, String> map) {
        addSubscrebe(Api.getInstance().changeShopCart(map),
                new RxSubscriber<RequestStatusBean>(mContext, true) {
                    @Override
                    protected void onSuccess(RequestStatusBean info) {
                     if(info.getStatus().equals("y")){
                         mView.changeShopCartSuccess(info.getInfo());
                     }else{
                         mView.loadFail(info.getInfo());
                     }
                    }
                    @Override
                    protected void onFailure(String message) {
                        mView.loadFail(message);
                    }
                });
    }
}
