package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.FootprintListBean;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.RequestStatusBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/8/15.
 */

public interface MyFootprintContract {
    interface View extends BaseView {
        void loadSuccess(List<FootprintListBean> data);
        void clearFootPrint(String msg);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
        void clearFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadFootPrintr(Map<String, String> param, boolean isShowLoad);
        public abstract void clearFootPrint(Map<String, String> param);

        @Override
        public void onStart() {

        }
    }
}
