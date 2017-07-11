package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.SeckillBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/11.
 */

public interface SecKillOrderContract {
    interface View extends BaseView {
        void loadSuccess(List<SeckillBean> data);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadSeckillOrder(Map<String, String> param, boolean isShowLoad);

        @Override
        public void onStart() {

        }
    }
}
