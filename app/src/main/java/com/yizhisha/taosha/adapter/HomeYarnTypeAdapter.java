package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.HomeYarnTypeEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */

public class HomeYarnTypeAdapter extends BaseQuickAdapter<HomeYarnTypeEntity,BaseViewHolder> {
    public HomeYarnTypeAdapter(@Nullable List<HomeYarnTypeEntity> data) {
        super(R.layout.home_yarn_type_item,data);

    }
    @Override
    protected void convert(BaseViewHolder helper, HomeYarnTypeEntity item) {
            helper.setText(R.id.tv_name,item.getName());
    }
}
