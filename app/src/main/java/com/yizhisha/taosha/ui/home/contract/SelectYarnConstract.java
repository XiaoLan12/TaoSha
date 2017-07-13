package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.bean.json.SearchDetailBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/3.
 */

public interface SelectYarnConstract {
    interface View extends BaseView {
        void loadSuccess(List<SearchDetailBean> data);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadSearch(Map<String, String> param,boolean isShowLoad);

        @Override
        public void onStart() {

        }
    }
}
