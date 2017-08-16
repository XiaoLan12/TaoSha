package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.CollectListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/4.
 */

public interface MyAddressContract {
    interface View extends BaseView {
        void loadAddressSuccess(List<AddressListBean.Address> data);

        void changeAddressSuccess(String msg);

        void deleteAddress();

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);

        void deleteFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadAddress(int uid,boolean isShowLoad);
        public abstract void changeAddress(Map<String,String> map);
        public abstract void deleteAddress(int id);

        @Override
        public void onStart() {

        }
    }
}
