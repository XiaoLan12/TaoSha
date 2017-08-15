package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.FootprintListBean;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/8/15.
 */

public class MyFootprintAdapter extends BaseQuickAdapter<FootprintListBean,BaseViewHolder>{
    public MyFootprintAdapter(@Nullable List<FootprintListBean> data) {
        super(R.layout.item_myfootprint,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FootprintListBean item) {
        FootprintListBean.FootprintGoods goods=item.getGoods();
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+goods.getLitpic(),
                (ImageView) helper.getView(R.id.tradehead_iv),GlideUtil.LOAD_BITMAP);
        helper.setText(R.id.tradename_tv,goods.getTitle());
        helper.setText(R.id.tradeprice_tv,"￥: "+goods.getPrice());
        helper.addOnClickListener(R.id.delete_tv);
    }
}
