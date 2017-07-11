package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/5.
 */

public interface FreeSampleContract {
    interface View extends BaseView {
        void loadFreeSampleSuccess(List<FreeSampleBean.Active> data);

        void cancleFreeSample(String msg);
        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
        void cancleFreeSampleFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadFreeSample(Map<String, String> param, boolean isShowLoad);
        public abstract void cancleFreeSample(Map<String, String> param);

        @Override
        public void onStart() {

        }
    }
}
