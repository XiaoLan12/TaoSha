package com.yizhisha.taosha.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/7/10.
 */

public class MyOrderDetailsAdapter extends BaseQuickAdapter<Goods,BaseViewHolder>{
    private Context mContext;
    public MyOrderDetailsAdapter(Context context,@Nullable List<Goods> data) {
        super(R.layout.item_orderdetails,data);
        this.mContext=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, Goods goods) {
        helper.setText(R.id.tradename_myorder_tv,goods.getTitle());
        helper.setText(R.id.tradecolor_myorder_tv,goods.getRemark());
        helper.setText(R.id.tradeprice_myorder_tv,"￥"+goods.getTotalprice());
        ImageView imageView=helper.getView(R.id.tradehead_myorder_iv);
        GlideUtil.getInstance().LoadContextBitmap(mContext,goods.getLitpic(),imageView,GlideUtil.LOAD_BITMAP);
        helper.setText(R.id.tradecolor_myorder_tv,goods.getDetail());
    }
}
