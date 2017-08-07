package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.ChangeUserInfoBody;
import com.yizhisha.taosha.bean.json.PersonalDataBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by lan on 2017/7/4.
 */

public interface ChangeOneInfoContract {
    interface View extends BaseView {
        void loadPersonalDataSuccess(PersonalDataBean personalDataBean);
        void changeSuccess(String msg);
        void changeHeadSuccess(String msg);

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadPersonalData(int uid);
        public abstract void changeUserInfo(Map<String, String> params);
        public abstract void changeHeadSuccess(MultipartBody.Part body);

        @Override
        public void onStart() {

        }
    }
}
