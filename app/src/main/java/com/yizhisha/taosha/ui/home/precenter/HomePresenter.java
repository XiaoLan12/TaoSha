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
    public void getPPT(Map<String, String> map) {
        addSubscrebe(Api.getInstance().getPPT(map),new RxSubscriber<IndexPPTBean>(mContext,false){
            @Override
            protected void onSuccess(IndexPPTBean indexPPTBean) {
                Log.e("TTT", indexPPTBean.toString());
                mView.getPPTSuccess(indexPPTBean);
            }
            @Override
            protected void onFailure(String message) {
                mView.getPPTFail(message);
            }
        });

    }

    @Override
    public void getRecmomendYarn(Map<String, String> map) {
        addSubscrebe(Api.getInstance().getRecommendYarn(map), new RxSubscriber<IndexRecommendYarnBean>(mContext,false) {
            @Override
            protected void onSuccess(IndexRecommendYarnBean model) {
                Log.e("TTT",model.toString());
                mView.getRecommendSuccess(model);
            }

            @Override
            protected void onFailure(String message) {
                mView.getPPTFail(message);
            }
        });
    }
}
