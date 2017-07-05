package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.bean.json.MyOrderListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/5.
 */

public interface MyOrderContract {
    interface View extends BaseView {
        void loadOrderSuccess(List<MyOrderListBean.Order> data);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadOrder(Map<String, String> param, boolean isShowLoad);

        @Override
        public void onStart() {

        }
    }
}
