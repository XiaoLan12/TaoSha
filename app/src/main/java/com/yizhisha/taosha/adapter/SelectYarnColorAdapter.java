package com.yizhisha.taosha.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class SelectYarnColorAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context mContext;
    public SelectYarnColorAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.item_select_yarn_color,data);
        this.mContext=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, String goods) {

    }
}