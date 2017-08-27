package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.bean.json.SeckillProductBean;
import com.yizhisha.taosha.ui.home.contract.SeckillProductContract;

import java.util.Map;

/**
 * Created by lan on 2017/8/17.
 */

public class SeckillProductPresenter extends SeckillProductContract.Presenter{
    @Override
    public void loadSeckillProduct(Map<String, String> map) {
        mView.showLoading();
        addSubscrebe(Api.getInstance().getSeckillProduct(map), new RxSubscriber<SeckillProductBean>(mContext,true) {
            @Override
            protected void onSuccess(SeckillProductBean bean) {
                mView.hideLoading();
                if(bean.getStatus().equals("y")){
                    mView.loadSuccess(bean);
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
