package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/9 0009.
 */

public interface OrderDetailsContract {
    interface View extends BaseView {
        void loadoSuccess(List<Order>  data);
        void sureGoodsSuuccess(String msg);

        void cancleOrder(String msg);

        void weChatPay(PayReqBean bean);
        void loadWeChatPayState(WeChatPayStateBean bean);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
        void cancelFail(String msg);
        void loadWeChatPayStateFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadOrderDetails(Map<String, String> param, boolean isShowLoad);
        public abstract void sureGoods(Map<String, String> param);
        public abstract void cancleOrder(Map<String, String> param);
        public abstract void weChatPay(Map<String, String> param);
        public abstract void loadWeChatPayState(Map<String, String> param);
        @Override
        public void onStart() {

        }
    }
}
