package com.yizhisha.taosha.wxapi;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WechatBean;
import com.yizhisha.taosha.ui.me.contract.AddAddressContract;

import java.util.Map;

/**
 * Created by lan on 2017/8/10.
 */

public interface WXEntryContract {
    interface View extends BaseView {
        void loadWeChatData(WechatBean wechatBean);
        void weChatLogin(String info);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadWeChatData(String url);
        public abstract void weChatLogin(Map<String,String> map);
        @Override
        public void onStart() {

        }
    }
}
