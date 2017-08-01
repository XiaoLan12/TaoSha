package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.SeckillBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/9 0009.
 */

public interface SecKillOrderDetailsContract {
    interface View extends BaseView {
        void loadoSecKillOrderSuccess(List<SeckillBean> data);
        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadSecKillOrderDetails(Map<String, String> param, boolean isShowLoad);

        @Override
        public void onStart() {

        }
    }
}
