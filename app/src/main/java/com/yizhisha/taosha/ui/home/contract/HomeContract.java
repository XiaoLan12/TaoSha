package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;
import com.yizhisha.taosha.bean.json.ListGoodsBean;

/**
 * Created by Administrator on 2017/7/8.
 */

public interface HomeContract {
    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);

        void getPPTSuccess(IndexPPTBean model);
        void getPPTFail(String msg);

        void getRecommendSuccess(IndexRecommendYarnBean model);
        void getRecommendFali(String msg);

        void getListGoodsDailySuccess(ListGoodsBean model);
        void getListGoodsNaYangSuccess(ListGoodsBean model);
        void getListGoodsBannaoSuccess(ListGoodsBean model);
        void getListGoodsFail(String msg);
    }

    abstract class Presenter extends BasePresenter<HomeContract.View> {

        public abstract void getPPT();
        public abstract void getRecmomendYarn();

        public abstract void getListGoodsDaily(String type);
        public abstract void getListGoodsNaYang(String type);
        public abstract void getListGoodsBannao(String type);


        @Override
        public void onStart() {

        }
    }
}
