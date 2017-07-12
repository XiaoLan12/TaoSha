package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.SeckillActBean;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/7/12.
 */

public class SeckillActivityAdapter extends BaseQuickAdapter<SeckillActBean,BaseViewHolder> {
    public SeckillActivityAdapter(@Nullable List<SeckillActBean> data) {
        super(R.layout.item_seckillact,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SeckillActBean item) {
        helper.setText(R.id.seckillact_title_tv,item.getTitle());
        helper.setText(R.id.seckillact_market_price_tv1,String.valueOf(item.getMarket_price()));
        helper.setText(R.id.seckillact_seckill_price_tv,String.valueOf(item.getPrice()));

        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getLitpic(),
                (ImageView) helper.getView(R.id.seckillact_photo_iv),GlideUtil.LOAD_BITMAP);
    }
}
