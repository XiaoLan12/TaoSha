package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;
import com.yizhisha.taosha.bean.json.ListGoodsBean;
import com.yizhisha.taosha.ui.home.contract.HomeContract;

/**
 * Created by Administrator on 2017/7/8.
 */

public class HomePresenter extends HomeContract.Presenter {
    @Override
    public void getPPT() {
        addSubscrebe(Api.getInstance().getPPT(),new RxSubscriber<IndexPPTBean>(mContext,false){
            @Override
            protected void onSuccess(IndexPPTBean indexPPTBean) {
                mView.getPPTSuccess(indexPPTBean);
            }
            @Override
            protected void onFailure(String message) {
                mView.getPPTFail(message);
            }
        });

    }

    @Override
    public void getRecmomendYarn() {
        addSubscrebe(Api.getInstance().getRecommendYarn(), new RxSubscriber<IndexRecommendYarnBean>(mContext,false) {
            @Override
            protected void onSuccess(IndexRecommendYarnBean model) {
                mView.getRecommendSuccess(model);
            }

            @Override
            protected void onFailure(String message) {
                mView.getPPTFail(message);
            }
        });
    }

    @Override
    public void getListGoodsDaily(String type) {
        addSubscrebe(Api.getInstance().getGoodList(type), new RxSubscriber<ListGoodsBean>(mContext,false) {
            @Override
            protected void onSuccess(ListGoodsBean model) {
                if(model.getStatus().equals("y")) {
                    mView.getListGoodsDailySuccess(model);
                }
            }

            @Override
            protected void onFailure(String message) {
//                mView.getPPTFail(message);
            }
        });

    }

    @Override
    public void getListGoodsNaYang(String type) {
        addSubscrebe(Api.getInstance().getGoodList(type), new RxSubscriber<ListGoodsBean>(mContext,false) {
            @Override
            protected void onSuccess(ListGoodsBean model) {
                if(model.getStatus().equals("y")) {
                    mView.getListGoodsNaYangSuccess(model);
                }
            }

            @Override
            protected void onFailure(String message) {
//                mView.getPPTFail(message);
            }
        });
    }

    @Override
    public void getListGoodsBannao(String type) {
        addSubscrebe(Api.getInstance().getGoodList(type), new RxSubscriber<ListGoodsBean>(mContext,false) {
            @Override
            protected void onSuccess(ListGoodsBean model) {
                if(model.getStatus().equals("y")){
                    mView.getListGoodsBannaoSuccess(model);
                }

            }

            @Override
            protected void onFailure(String message) {
//                mView.getPPTFail(message);
            }
        });
    }
}
