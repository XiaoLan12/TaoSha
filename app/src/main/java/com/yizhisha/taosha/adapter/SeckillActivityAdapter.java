package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.SeckillActBean;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.DensityUtil;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.LogUtil;
import com.yizhisha.taosha.widget.TimeView;

import java.text.ParseException;
import java.util.List;

/**
 * Created by lan on 2017/7/12.
 */

public class SeckillActivityAdapter extends BaseQuickAdapter<SeckillActBean,BaseViewHolder> {
    private long endtime;
    public SeckillActivityAdapter(@Nullable List<SeckillActBean> data) {
        super(R.layout.item_seckillact,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SeckillActBean item) {
      /*  item.setStarttime(System.currentTimeMillis()+100);
        item.setEndtime(System.currentTimeMillis()+10000);*/
        helper.setText(R.id.seckillact_title_tv,item.getTitle());
        helper.setText(R.id.seckillact_market_price_tv1,String.valueOf(item.getMarket_price()));
        helper.setText(R.id.seckillact_seckill_price_tv,String.valueOf(item.getPrice()));
        TimeView mTvTimer=helper.getView(R.id.seckillact_activity_tv);
        try {
            //mTvTimer.setData(item.getStarttime(),item.getEndtime(),item.getNowtime()*1000);
            mTvTimer.setData(item.getStarttime()*1000,item.getEndtime()*1000,item.getNowtime()*1000);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getLitpic(),
                (ImageView) helper.getView(R.id.seckillact_photo_iv),GlideUtil.LOAD_BITMAP);
    }
}
