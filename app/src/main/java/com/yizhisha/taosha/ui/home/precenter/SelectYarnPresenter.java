package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.ui.home.contract.SelectYarnConstract;

import java.util.Map;

/**
 * Created by lan on 2017/7/13.
 */

public class SelectYarnPresenter extends SelectYarnConstract.Presenter{
    @Override
    public void loadSearch(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().search(param), new RxSubscriber<SearchBean>(mContext, false) {
            @Override
            protected void onSuccess(SearchBean searchBean) {
                mView.hideLoading();
                if(searchBean.getStatus().equals("y")&&searchBean.getGoods().size()>0){
                    mView.loadSuccess(searchBean.getGoods());
                }else{
                    mView.showEmpty();
                }
            }

            @Override
            protected void onFailure(String message) {
                mView.hideLoading();
                mView.loadFail(message);
            }
        });
    }
}
