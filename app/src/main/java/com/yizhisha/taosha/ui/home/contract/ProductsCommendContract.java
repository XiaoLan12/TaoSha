package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.HotCommendBean;
import com.yizhisha.taosha.bean.json.IndexADSBean;

import java.util.List;

/**
 * Created by lan on 2017/8/18.
 */

public interface ProductsCommendContract {
    interface View extends BaseView {
        void loadSuccess(List<IndexADSBean> bean);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);

    }
    abstract class Presenter extends BasePresenter<View> {


        public abstract void loadProductsCommend(boolean isShowload);

        @Override
        public void onStart() {

        }
    }
}
