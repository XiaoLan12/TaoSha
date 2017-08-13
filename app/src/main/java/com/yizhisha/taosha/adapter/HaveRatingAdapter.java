package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.CommentBean;
import com.yizhisha.taosha.bean.json.MyCommentListBean;

import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class HaveRatingAdapter extends BaseMultiItemQuickAdapter<MyCommentListBean,BaseViewHolder> {
    public HaveRatingAdapter(@Nullable List<MyCommentListBean> data) {
        super(data);
        addItemType(CommentBean.TEXT_TYPE, R.layout.item_text_commnet);
        addItemType(CommentBean.IMGS_TYPE, R.layout.item_img_commnet);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCommentListBean item) {

    }
}
