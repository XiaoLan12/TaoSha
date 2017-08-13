package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.SearchDetailBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/13 0013.
 */

public interface SelectYarnColorContract {
    interface View extends BaseView {
        void changeShopCartSuccess(String msg);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<SelectYarnColorContract.View> {

        public abstract void changeShopCart(Map<String, String> map);

        @Override
        public void onStart() {

        }
    }
}
