package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.SeckillActBean;
import com.yizhisha.taosha.bean.json.SeckillActListBean;
import com.yizhisha.taosha.ui.me.contract.MyCollectConstract;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/12.
 */

public interface SeckillActivityContract {
    interface View extends BaseView {
        void loadSuccess(SeckillActListBean data);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadSeckillActivity(Map<String, String> param, boolean isShowLoad);

        @Override
        public void onStart() {

        }
    }
}
