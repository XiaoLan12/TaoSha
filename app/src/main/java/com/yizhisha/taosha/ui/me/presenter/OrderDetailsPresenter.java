package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.ui.me.contract.OrderDetailsContract;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/9 0009.
 */

public class OrderDetailsPresenter extends OrderDetailsContract.Presenter{
    @Override
    public void loadOrderDetails(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
          addSubscrebe(Api.getInstance().loadOrderDetails(param), new RxSubscriber<MyOrderListBean>(mContext,false) {
              @Override
              protected void onSuccess(MyOrderListBean myOrderListBean) {
                      mView.hideLoading();
                  if(myOrderListBean!=null&&myOrderListBean.getOrder().size()>0){
                      mView.loadoSuccess(myOrderListBean.getOrder());
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
