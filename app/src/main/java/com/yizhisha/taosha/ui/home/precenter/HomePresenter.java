package com.yizhisha.taosha.ui.home.precenter;

import android.util.Log;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;
import com.yizhisha.taosha.ui.home.contract.HomeContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/8.
 */

public class HomePresenter extends HomeContract.Presenter {
    @Override
    public void getPPT() {
        addSubscrebe(Api.getInstance().getPPT(),new RxSubscriber<IndexPPTBean>(mContext,false){
            @Override
            protected void onSuccess(IndexPPTBean indexPPTBean) {
                mView.getPPTSuccess(indexPPTBean);
            }
            @Override
            protected void onFailure(String message) {
                mView.getPPTFail(message);
            }
        });

    }

    @Override
    public void getRecmomendYarn() {
        addSubscrebe(Api.getInstance().getRecommendYarn(), new RxSubscriber<IndexRecommendYarnBean>(mContext,false) {
            @Override
            protected void onSuccess(IndexRecommendYarnBean model) {
                mView.getRecommendSuccess(model);
            }

            @Override
            protected void onFailure(String message) {
                mView.getPPTFail(message);
            }
        });
    }
}
