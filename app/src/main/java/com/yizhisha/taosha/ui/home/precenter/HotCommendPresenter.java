package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.HotCommendBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.ui.home.contract.HotCommendContract;

/**
 * Created by lan on 2017/8/18.
 */

public class HotCommendPresenter extends HotCommendContract.Presenter{
    @Override
    public void loadHotCommend(String type,boolean isShowload) {
        if(isShowload){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadHotCommend(type), new RxSubscriber<HotCommendBean>(mContext, false) {
            @Override
            protected void onSuccess(HotCommendBean bean) {
                mView.hideLoading();
                if(bean.getInfo_s()==1||bean.getInfo_s()==0){
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
