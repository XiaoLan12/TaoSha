package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/5.
 */

public interface MyOrderContract {
    interface View extends BaseView {
        void loadOrderSuccess(List<Order> data);

        void sureGoodsSuuccess(String msg);

        void cancleOrder(String msg);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
        void cancelFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadOrder(Map<String, String> param, boolean isShowLoad);
        public abstract void cancleOrder(Map<String, String> param);
        public abstract void sureGoods(Map<String, String> param);


        @Override
        public void onStart() {

        }
    }
}
