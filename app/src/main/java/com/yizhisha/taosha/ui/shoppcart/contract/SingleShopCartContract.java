package com.yizhisha.taosha.ui.shoppcart.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.ShopCartBean;
import com.yizhisha.taosha.bean.json.Shopcart;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/8/11.
 */

public interface SingleShopCartContract {
    interface View extends BaseView {
        void loadSuccess(ShopCartBean data);
        void changeShopCartSuccess(String msg);
        void showLoading();
        void hideLoading();
        void showEmpty();
        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadSingleShoppCart(Map<String, String> map,boolean isShowLoad);
        public abstract void changeShopCart(Map<String, String> map);
        @Override
        public void onStart() {

        }
    }
}
