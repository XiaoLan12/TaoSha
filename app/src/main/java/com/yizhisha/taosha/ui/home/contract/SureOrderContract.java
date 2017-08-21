package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.SearchDetailBean;
import com.yizhisha.taosha.bean.json.ShopCartOrderSureBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;

/**
 * Created by lan on 2017/8/14.
 */

public interface SureOrderContract {
    interface View extends BaseView {
        void loadOrderSuccess(OrderSureBean data);
        void loadShopCartOrderSuccess(ShopCartOrderSureBean data);
        void weChatPay(PayReqBean bean);
        void regularOrderSuccess(String msg);
        void loadWeChatPayState(WeChatPayStateBean bean);
        void showLoading();
        void hideLoading();
        void loadFail(String msg);
        void loadWeChatPayStateFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadOrderSure(Map<String, String> param);
        public abstract void loadShopCartOrderSure(Map<String, String> param);
        public abstract void weChatPay(Map<String, String> param);
        public abstract void regularOrder(Map<String, String> param);
        public abstract void loadWeChatPayState(Map<String, String> param);
        @Override
        public void onStart() {

        }
    }
}
