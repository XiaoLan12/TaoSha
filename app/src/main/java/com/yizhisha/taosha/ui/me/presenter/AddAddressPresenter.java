package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.AddAddressContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/4.
 */

public class AddAddressPresenter extends AddAddressContract.Presenter{
    @Override
    public void addAddress(Map<String, String> map) {
        addSubscrebe(Api.getInstance().addAddress(map), new RxSubscriber<RequestStatusBean>(mContext,"正在保存...",true) {
            @Override
            protected void onSuccess(RequestStatusBean requestStatusBean) {
                if(requestStatusBean.getStatus().equals("y")){
                    mView.addAddressSuccess(requestStatusBean);
                }else{
                    mView.loadFail(requestStatusBean.getInfo());
                }
            }
            @Override
            protected void onFailure(String message) {
                    mView.loadFail(message);
            }
        });
    }

    @Override
    public void loadOneAddress(Map<String, String> map) {
        addSubscrebe(Api.getInstance().loadOneAddress(map), new RxSubscriber<AddressListBean.Address>(mContext,"载入中...",true) {
            @Override
            protected void onSuccess(AddressListBean.Address address) {
                if(address!=null){
                    mView.loadOneAddressSuccess(address);
                }else{
                    mView.loadFail("修改失败，请重新尝试");
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
