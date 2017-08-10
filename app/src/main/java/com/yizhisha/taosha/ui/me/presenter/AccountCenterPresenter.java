package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.AccountBean;
import com.yizhisha.taosha.ui.me.contract.AccountCenterContract;

import java.util.Map;

/**
 * Created by lan on 2017/8/10.
 */

public class AccountCenterPresenter extends AccountCenterContract.Presenter{
    @Override
    public void loadAccount(int uid, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().loadAccount(uid),
                new RxSubscriber<AccountBean>(mContext, false) {
                    @Override
                    protected void onSuccess(AccountBean accountBean) {
                        mView.hideLoading();
                        if(accountBean!=null&&accountBean.getDetail().size()>0){
                            mView.loadAccountSuccess(accountBean);
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
