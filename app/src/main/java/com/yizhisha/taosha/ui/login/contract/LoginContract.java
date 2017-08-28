package com.yizhisha.taosha.ui.login.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WechatBean;
import com.yizhisha.taosha.ui.me.contract.MyOrderContract;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/5.
 */

public interface LoginContract {
    interface View extends BaseView {
        void loginSuccess(RequestStatusBean info);

        void weChatLoginSuccess(RequestStatusBean bean);

        void registerSuccess(String info);

        void findPwdSuccess(String info);

        void getCodeSuccess(String info);

        void loadWeChatData(WechatBean wechatBean);

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void login(Map<String, String> map);
        public abstract void register(Map<String, String> map);
        public abstract void findPwd(Map<String, String> map);
        public abstract void getCode(Map<String, String> map);
        public abstract void loadWeChatData(String url);
        public abstract void weChatLogin(Map<String,String> map);

        @Override
        public void onStart() {

        }
    }
}
