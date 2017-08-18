package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.HotCommendBean;
import com.yizhisha.taosha.bean.json.SeckillProductBean;

import java.util.Map;

/**
 * Created by lan on 2017/8/18.
 */

public interface HotCommendContract {
    interface View extends BaseView {
        void loadSuccess(HotCommendBean bean);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);

    }

    abstract class Presenter extends BasePresenter<View> {


        public abstract void loadHotCommend(String type,boolean isShowload);

        @Override
        public void onStart() {

        }
    }
}
