package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.HotCommendBean;
import com.yizhisha.taosha.bean.json.IndexImgBean;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.ui.home.contract.ProductsCommendContract;

/**
 * Created by lan on 2017/8/18.
 */

public class ProductsCommendPresenter extends ProductsCommendContract.Presenter{
    @Override
    public void loadProductsCommend(boolean isShowload) {
        if(isShowload){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getPPT(), new RxSubscriber<IndexPPTBean>(mContext, false) {
            @Override
            protected void onSuccess(IndexPPTBean bean) {
                mView.hideLoading();
                if(bean!=null&&bean.getAds().size()>0){
                    mView.loadSuccess(bean.getAds());
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
}
