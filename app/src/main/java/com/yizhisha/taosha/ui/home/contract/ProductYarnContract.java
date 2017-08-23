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
        void collectProductSuccess(String msg);
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void collectProduct(Map<String, String> map);

        @Override
        public void onStart() {

        }
    }
}