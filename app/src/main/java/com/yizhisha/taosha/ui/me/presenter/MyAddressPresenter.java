package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.MyAddressContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/4.
 */

public class MyAddressPresenter extends MyAddressContract.Presenter{
    @Override
    public void loadAddress(int uid,boolean isShowLoad) {
        if(isShowLoad) {
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadAddressList(uid), new RxSubscriber<AddressListBean>(mContext,false) {
            @Override
            protected void onSuccess(AddressListBean addressListBean) {
                mView.hideLoading();
                if(addressListBean.getAddress().size()>0){
                    mView.loadAddressSuccess(addressListBean.getAddress());
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
    public void deleteAddress(int id) {
        addSubscrebe(Api.getInstance().deleteAddress(id),
                new RxSubscriber<RequestStatusBean>(mContext,true) {
                    @Override
                    protected void onSuccess(RequestStatusBean requestStatusBean) {
                        if(requestStatusBean.getStatus().equals("y")){
                            mView.deleteAddress();
                        }else{
                            mView.deleteFail(requestStatusBean.getInfo());
                        }
                    }
                    @Override
                    protected void onFailure(String message) {
                            mView.deleteFail(message);
                    }
                });
    }
}
