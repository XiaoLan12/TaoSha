package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.bean.json.ShopCartOrderSureBean;
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
}
