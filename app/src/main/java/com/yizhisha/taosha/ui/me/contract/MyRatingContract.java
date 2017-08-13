package com.yizhisha.taosha.ui.me.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.MyCommentListBean;
import com.yizhisha.taosha.bean.json.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/12 0012.
 */

public interface MyRatingContract {
    interface View extends BaseView {
        void loadCommentSuccess(List<MyCommentListBean> data);

        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void loadComment(int uid, boolean isShowLoad);

        @Override
        public void onStart() {

        }
    }
}
