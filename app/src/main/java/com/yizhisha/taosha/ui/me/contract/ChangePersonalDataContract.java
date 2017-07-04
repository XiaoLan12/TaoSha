package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.RequestStatusBean;

import java.util.Map;

/**
 * Created by lan on 2017/7/4.
 */

public interface ChangePersonalDataContract {
    interface View extends BaseView {
        void changePersonalDataSuccess(RequestStatusBean data);

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void changePersonalData(Map<String,String> map);

        @Override
        public void onStart() {

        }
    }
}
