package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.UserInfoBean;

import java.util.Map;

/**
 * Created by lan on 2017/8/8.
 */

public interface MeContract {
    interface View extends BaseView {
        void getUserInfoSuccess(UserInfoBean info);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getUserInfo(int uid);
        @Override
        public void onStart() {

        }
    }
}
