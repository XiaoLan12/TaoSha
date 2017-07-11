package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.SeckillListBean;
import com.yizhisha.taosha.ui.me.contract.SecKillOrderContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/11.
 */

public class SecKillOrderPresenter extends SecKillOrderContract.Presenter{
    @Override
    public void loadSeckillOrder(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getSeckillOrder(param),
                new RxSubscriber<SeckillListBean>(mContext, false) {
            @Override
            protected void onSuccess(SeckillListBean seckillListBean) {
                mView.hideLoading();
                if(seckillListBean!=null&&seckillListBean.getSeckilling().size()>0){
                    mView.loadSuccess(seckillListBean.getSeckilling());
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
