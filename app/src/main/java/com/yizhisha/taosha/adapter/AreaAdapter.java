package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.AreaInfo;

import java.util.List;

/**
 * Created by lan on 2017/7/11.
 */

public class AreaAdapter extends BaseQuickAdapter<AreaInfo,BaseViewHolder> {
    public AreaAdapter(@Nullable List<AreaInfo> data) {
        super(R.layout.item_area_list,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, AreaInfo item) {
        helper.setText(R.id.area_txt_item,item.getAreaName());
    }
}
