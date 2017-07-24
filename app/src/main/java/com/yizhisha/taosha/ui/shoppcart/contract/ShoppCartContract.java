package com.yizhisha.taosha.ui.shoppcart.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.Shopcart;
import com.yizhisha.taosha.ui.me.contract.MyAddressContract;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/10.
 */

public interface ShoppCartContract {
    interface View extends BaseView {
        void loadSuccess(List<Shopcart> data);

        void deleteShoppCart(String msg);
        void deleteOneShoppCart(String msg,int groupPosition, int childPosition);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);

        void deleteFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadShoppCart(int uid,boolean isShowLoad);
        public abstract void deleteShoppCart(Map<String, String> map);
        public abstract void deleteOneShoppCart(Map<String, String> map,int groupPosition, int childPosition);

        @Override
        public void onStart() {

        }
    }
}
