package com.yizhisha.taosha.ui.me.presenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.ChangeUserInfoBody;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.UserHeadBean;
import com.yizhisha.taosha.ui.me.contract.ChangeOneInfoContract;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    @Override
    public void changeUserInfo(Map<String, String> params) {
        addSubscrebe(Api.getInstance().changeUserInfo(params),new RxSubscriber<RequestStatusBean>(mContext,true){
            @Override
            protected void onSuccess(RequestStatusBean info) {
                if(info!=null){
                    if(info.getStatus().equals("y")){
                        mView.changeSuccess(info.getInfo());
                    }else{
                        mView.loadFail(info.getInfo());
                    }
                }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }

    @Override
    public void changeHeadSuccess(MultipartBody.Part body) {
        addSubscrebe(Api.getInstance().changeUserHead(body),new RxSubscriber<UserHeadBean>(mContext,true){
            @Override
            protected void onSuccess(UserHeadBean info) {
                if(info!=null) {
                    mView.changeHeadSuccess(info.getAvatar());
                } else{
                    mView.loadFail("头像修改失败");
                    }
            }
            @Override
            protected void onFailure(String message) {
                mView.loadFail(message);
            }
        });
    }
}
