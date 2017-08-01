package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;

import java.util.Map;

/**
 * Created by lan on 2017/8/1.
 */

public interface ChangeUserInfoContract {
    interface View extends BaseView {
        void changeSuccess(String msg);

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void changeUserInfo(Map<String, String> param);

        @Override
        public void onStart() {

        }
    }
}
