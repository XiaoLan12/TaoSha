package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.UserHeadBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by lan on 2017/8/8.
 */

public interface MeContract {
    interface View extends BaseView {
        void getUserInfoSuccess(UserInfoBean info);
        void changeHeadSuccess(UserHeadBean msg);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getUserInfo(int uid);
        public abstract void changeHeadSuccess(RequestBody uid, MultipartBody.Part body);
        @Override
        public void onStart() {

        }
    }
}
