package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.ShopCartOrderSureBean;
import com.yizhisha.taosha.ui.home.contract.ProductYarnContract;
import com.yizhisha.taosha.ui.home.contract.YarnContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/16.
 */

public class ProductYarnPresenter extends ProductYarnContract.Presenter{

    @Override
    public void collectProduct(Map<String, String> map) {
        addSubscrebe(Api.getInstance().collectProduct(map), new RxSubscriber<RequestStatusBean>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(RequestStatusBean bean) {
                if(bean.getStatus().equals("y")){
                    mView.collectProductSuccess(bean.getInfo());
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
}
