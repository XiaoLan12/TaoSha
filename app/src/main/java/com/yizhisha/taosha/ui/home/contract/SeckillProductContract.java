package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.bean.json.SeckillProductBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/16.
 */

public interface SeckillProductContract {
    interface View extends BaseView {
        void loadSuccess(SeckillProductBean model);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);



    }

    abstract class Presenter extends BasePresenter<View> {


        public abstract void loadSeckillProduct(Map<String, String> map);

        @Override
        public void onStart() {

        }
    }
}