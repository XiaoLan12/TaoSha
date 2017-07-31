package com.yizhisha.taosha.ui.home.contract;

import com.yizhisha.taosha.base.BasePresenter;
import com.yizhisha.taosha.base.BaseView;
import com.yizhisha.taosha.bean.json.CommentBean;
import com.yizhisha.taosha.bean.json.CommentListBean;
import com.yizhisha.taosha.bean.json.ProductDetailBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/7/31.
 */

public interface CommentYarnContract {
    interface View extends BaseView {
        void loadCommentListSuccess(List<CommentBean> model);
        void showLoading();

        void hideLoading();

        void showEmpty();

        void loadFail(String msg);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void loadCommentList(Map<String, String> map,boolean isShowLoad);
        @Override
        public void onStart() {

        }
    }
}
