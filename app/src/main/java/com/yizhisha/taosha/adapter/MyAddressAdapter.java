package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;

import java.util.List;

/**
 * Created by lan on 2017/6/27.
 */

public class MyAddressAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public MyAddressAdapter(@Nullable List<String> data) {
        super(R.layout.item_myaddress,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.edit_myaddress_tv);
    }
}
