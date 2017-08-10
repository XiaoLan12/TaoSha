package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.AccountBean;
import com.yizhisha.taosha.bean.json.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/8/10.
 */

public interface AccountCenterContract {
    interface View extends BaseView {
        void loadAccountSuccess(AccountBean data);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadAccount(int uid, boolean isShowLoad);

        @Override
        public void onStart() {

        }
    }
}
