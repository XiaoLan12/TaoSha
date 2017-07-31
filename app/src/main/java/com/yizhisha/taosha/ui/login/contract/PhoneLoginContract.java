package com.yizhisha.taosha.ui.login.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.RequestStatusBean;

import java.util.Map;

/**
 * Created by lan on 2017/7/31.
 */

public interface PhoneLoginContract {
    interface View extends BaseView {
        void loginSuccess(RequestStatusBean info);

        void getCodeSuccess(String info);

        void loadFail(String msg);
    }
    abstract class Presenter extends BasePresenter<View> {

        public abstract void phoneLogin(Map<String, String> map);
        public abstract void getCode(Map<String, String> map);

        @Override
        public void onStart() {

        }
    }
}
