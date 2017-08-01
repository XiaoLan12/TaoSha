package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.SeckillListBean;
import com.yizhisha.taosha.ui.me.contract.OrderDetailsContract;
import com.yizhisha.taosha.ui.me.contract.SecKillOrderDetailsContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/9 0009.
 */

public class SecKillOrderDetailsPresenter extends SecKillOrderDetailsContract.Presenter{

    @Override
    public void loadSecKillOrderDetails(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getSeckillOrderDetail(param), new RxSubscriber<SeckillListBean>(mContext,false) {
            @Override
            protected void onSuccess(SeckillListBean seckillListBean) {
                mView.hideLoading();
                if(seckillListBean!=null&&seckillListBean.getSeckilling().size()>0){
                    mView.loadoSecKillOrderSuccess(seckillListBean.getSeckilling());
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
