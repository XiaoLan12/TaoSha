package com.yizhisha.taosha.ui.me.mycollect;

import com.yizhisha.taosha.api.Api;
import com.yizhisha.taosha.base.rx.RxApiCallback;
import com.yizhisha.taosha.base.rx.RxSubscriber;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.utils.LogUtil;

/**
 * Created by lan on 2017/7/3.
 */

public class MyCollectPresenter extends MyCollectConstract.Presenter{
    @Override
    protected void loadCollect(int uid, int pid) {
        addSubscrebe(Api.getInstance().loadCollectList(uid, pid), new RxSubscriber(mContext,false) {
            @Override
            protected void onSuccess(Object o) {

            }
            @Override
            protected void onFailure(String message) {

            }
        });
    }
}
