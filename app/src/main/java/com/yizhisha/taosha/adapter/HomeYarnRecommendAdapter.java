package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.HomeYarnTypeEntity;

import java.util.List;

/**
 * Created by ude on 2017/6/29.
 */

public class HomeYarnRecommendAdapter extends BaseQuickAdapter<HomeYarnTypeEntity,BaseViewHolder> {
    public HomeYarnRecommendAdapter(@Nullable List<HomeYarnTypeEntity> data) {
        super(R.layout.item_home_recommend,data);

    }
    @Override
    protected void convert(BaseViewHolder helper, HomeYarnTypeEntity item) {
        helper.setText(R.id.tv_name,item.getName());
        ( (ImageView)helper.getView(R.id.img_type)).setImageResource(item.getImg());
    }
}