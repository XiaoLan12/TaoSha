package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;

import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class MyCollectAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public MyCollectAdapter(@Nullable List<String> data) {
        super(R.layout.item_mycollect,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.totalprice_mycollect_tv,item);
    }
}
