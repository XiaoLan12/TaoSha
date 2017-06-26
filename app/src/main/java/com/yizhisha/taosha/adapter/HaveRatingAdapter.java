package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;

import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class HaveRatingAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public HaveRatingAdapter(@Nullable List<String> data) {
        super(R.layout.item_haverating,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
