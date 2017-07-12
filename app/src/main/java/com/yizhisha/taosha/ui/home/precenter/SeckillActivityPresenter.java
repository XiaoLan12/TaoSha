package com.yizhisha.taosha.ui.home.precenter;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.SeckillActListBean;
import com.yizhisha.taosha.ui.home.contract.SeckillActivityContract;
import com.yizhisha.taosha.ui.me.contract.SecKillOrderContract;

import java.util.Map;

/**
 * Created by lan on 2017/7/12.
 */

public class SeckillActivityPresenter extends SeckillActivityContract.Presenter{
    @Override
    public void loadSeckillActivity(Map<String, String> param, boolean isShowLoad) {
        if(isShowLoad){
            mView.showLoading();
        }
        addSubscrebe(Api.getInstance().getSeckillActivity(param),
                new RxSubscriber<SeckillActListBean>(mContext, false) {
                    @Override
                    protected void onSuccess(SeckillActListBean seckillActListBean) {
                        mView.hideLoading();
                        if(seckillActListBean!=null&&seckillActListBean.getSeckilling().size()>0){
                            mView.loadSuccess(seckillActListBean.getSeckilling());
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
