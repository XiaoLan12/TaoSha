package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxApiCallback;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.ui.me.contract.MyCollectConstract;
import com.yizhisha.taosha.utils.LogUtil;

import java.util.Map;

import retrofit2.http.QueryMap;

/**
 * Created by lan on 2017/7/3.
 */

public class MyCollectPresenter extends MyCollectConstract.Presenter{
    @Override
    public void loadCollect(Map<String, String> param,boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadCollectList(param), new RxSubscriber<CollectListBean>(mContext,false) {
            @Override
            protected void onSuccess(CollectListBean data) {
                mView.hideLoading();
                if(data!=null&&data.getData().size()>0){
                    mView.loadCollectSuccess(data.getData());
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
