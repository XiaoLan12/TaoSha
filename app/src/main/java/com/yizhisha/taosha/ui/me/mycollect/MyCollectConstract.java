package com.yizhisha.taosha.ui.me.mycollect;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.UserInfoBean;

/**
 * Created by lan on 2017/7/3.
 */

public interface MyCollectConstract {
    interface View extends BaseView {
        void loadCollectSuccess(UserInfoBean userInfoBean);

        void showLoading();

        void hideLoading();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        protected abstract void loadCollect(int uid, int pid);

        @Override
        public void onStart() {

        }
    }
}
