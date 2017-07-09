package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/9 0009.
 */

public interface OrderDetailsContract {
    interface View extends BaseView {
        void loadoSuccess(List<Order>  data);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadOrderDetails(Map<String, String> param, boolean isShowLoad);

        @Override
        public void onStart() {

        }
    }
}
