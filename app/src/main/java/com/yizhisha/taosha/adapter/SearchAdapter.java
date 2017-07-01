package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.HomeYarnTypeEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */

public class SearchAdapter  extends BaseQuickAdapter<HomeYarnTypeEntity,BaseViewHolder> {
    public SearchAdapter(@Nullable List<HomeYarnTypeEntity> data) {
        super(R.layout.item_search,data);

    }
    @Override
    protected void convert(BaseViewHolder helper, HomeYarnTypeEntity item) {

    }
}