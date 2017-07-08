package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/8.
 */

public interface HomeContract {
    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);

        void getPPTSuccess(IndexPPTBean model);
        void getPPTFail(String msg);

        void getRecommendSuccess(IndexRecommendYarnBean model);
        void getRecommendFali(String msg);
    }

    abstract class Presenter extends BasePresenter<HomeContract.View> {

        public abstract void getPPT(Map<String,String> map);
        public abstract void getRecmomendYarn(Map<String,String> map);


        @Override
        public void onStart() {

        }
    }
}
