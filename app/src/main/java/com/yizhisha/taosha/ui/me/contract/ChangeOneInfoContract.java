package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.PersonalDataBean;

/**
 * Created by lan on 2017/7/4.
 */

public interface ChangeOneInfoContract {
    interface View extends BaseView {
        void loadPersonalDataSuccess(PersonalDataBean personalDataBean);

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadPersonalData(int uid);

        @Override
        public void onStart() {

        }
    }
}
