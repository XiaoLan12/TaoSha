package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WechatBean;
import com.yizhisha.taosha.bean.json.WechatInfoBean;
import com.yizhisha.taosha.ui.login.contract.LoginContract;

import java.util.Map;

/**
 * Created by lan on 2017/8/10.
 */

public interface SetContract {
    interface View extends BaseView {
        void loadWeChatData(WechatBean wechatBean);
        void bindWeChat(String info);
        void unBindWeChat(String msg);
        void loadWeChatInfo(WechatInfoBean bean);
        void showBindWeChart(RequestStatusBean bean);
        void loadFail(String msg);
        void unBindWeChatFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadWeChatData(String url);
        public abstract void bindWeChat(Map<String,String> map);
        public abstract void loadWeChatInfo(String url);
        public abstract void unBindWeChat(int uid);
        public abstract void showBindWeChart(int uid);

        @Override
        public void onStart() {

        }
    }
}
