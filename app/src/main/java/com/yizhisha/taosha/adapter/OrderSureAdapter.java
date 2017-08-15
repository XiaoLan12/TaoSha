package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.OrderSureGoodBean;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/8/15.
 */

public class OrderSureAdapter extends BaseQuickAdapter<OrderSureGoodBean,BaseViewHolder>{
    public OrderSureAdapter(@Nullable List<OrderSureGoodBean> data) {
        super(R.layout.item_ordersure,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, OrderSureGoodBean item) {
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getLitpic(),
                (ImageView) helper.getView(R.id.tradehead_iv),GlideUtil.LOAD_BITMAP);
        helper.setText(R.id.tradename_tv,item.getTitle());
        helper.setText(R.id.tradecolor_tv,item.getDetail());
        helper.setText(R.id.tradeprice_tv,"￥"+item.getPrice());
    }
}
