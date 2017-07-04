package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
import com.yizhisha.taosha.ui.me.contract.ChangeOneInfoContract;

/**
 * Created by lan on 2017/7/4.
 */

public class ChangeOneInfoPresenter extends ChangeOneInfoContract.Presenter{
    @Override
    public void loadPersonalData(int uid) {
        addSubscrebe(Api.getInstance().loadPersonalData(uid), new RxSubscriber<PersonalDataBean>(mContext,true) {
            @Override
            protected void onSuccess(PersonalDataBean personalDataBean) {
                mView.loadPersonalDataSuccess(personalDataBean);
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
