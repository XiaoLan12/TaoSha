package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.PersonalDataBean;

import java.util.Map;

/**
 * Created by lan on 2017/7/18.
 */

public interface ChangePwdContract {
    interface View extends BaseView {
        void changePwdSuccess(String msg);

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void changePwd(Map<String, String> param);

        @Override
        public void onStart() {

        }
    }
}
