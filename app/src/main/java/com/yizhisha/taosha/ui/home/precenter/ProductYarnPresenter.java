package com.yizhisha.taosha.ui.home.precenter;

import android.util.Log;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.ui.home.contract.ProductYarnContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/16.
 */

public class ProductYarnPresenter extends ProductYarnContract.Presenter {
    @Override
    public void getProductDetail(Map<String, String> map) {
        addSubscrebe(Api.getInstance().getProductDetail(map), new RxSubscriber<ProductDetailBean>(mContext,true) {
            @Override
            protected void onSuccess(ProductDetailBean productDetailBean) {
                Log.e("TTT", productDetailBean.toString());
                if(productDetailBean.getInfo_s()==0){
                    mView.getProductDetailSuccess(productDetailBean);
                }else{
                    mView.getProductDetailFail(productDetailBean.getInfo_t());
                }

            }

            @Override
            protected void onFailure(String message) {
                mView.getProductDetailFail(message);
            }
        });
    }
}
