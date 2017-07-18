package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.ProductDetailBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/16.
 */

public interface ProductYarnContract {
    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showEmpty();

        void getProductDetailFail(String msg);

        void getProductDetailSuccess(ProductDetailBean model);

    }

    abstract class Presenter extends BasePresenter<View> {


        public abstract void getProductDetail(Map<String, String> map);

        @Override
        public void onStart() {

        }
    }
}