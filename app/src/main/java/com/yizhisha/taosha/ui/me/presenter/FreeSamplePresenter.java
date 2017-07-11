package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.FreeSampleContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/5.
 */

public class FreeSamplePresenter extends FreeSampleContract.Presenter{
    @Override
    public void loadFreeSample(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadFreeSample(param),
                new RxSubscriber<FreeSampleBean>(mContext,false) {
                    @Override
                    protected void onSuccess(FreeSampleBean freeSampleBean) {
                        mView.hideLoading();
                        if(freeSampleBean!=null&&freeSampleBean.getActive().size()>0){
                            mView.loadFreeSampleSuccess(freeSampleBean.getActive());
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
    public void cancleFreeSample(Map<String, String> param) {
        addSubscrebe(Api.getInstance().cancelFreeSample(param), new RxSubscriber<RequestStatusBean>(mContext, false) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.cancleFreeSample(requestStatusBean.getInfo());
                }else{
                    mView.cancleFreeSampleFail(requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.cancleFreeSampleFail(message);
            }
        });
    }
}
