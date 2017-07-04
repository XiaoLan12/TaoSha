package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.QueryMap;

/**
 * Created by lan on 2017/7/3.
 */

public interface MyCollectConstract {
    interface View extends BaseView {
        void loadCollectSuccess(List<CollectListBean.Favorite> data);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadCollect(Map<String, String> param,boolean isShowLoad);

        @Override
        public void onStart() {

        }
    }
}
