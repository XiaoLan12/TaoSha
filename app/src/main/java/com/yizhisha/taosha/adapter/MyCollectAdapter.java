package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.CollectListBean;

import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class MyCollectAdapter extends BaseQuickAdapter<CollectListBean.Favorite,BaseViewHolder>{
    public MyCollectAdapter(@Nullable List<CollectListBean.Favorite> data) {
        super(R.layout.item_mycollect,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, CollectListBean.Favorite item) {
        //helper.setText(R.id.totalprice_mycollect_tv,item);
    }
}
