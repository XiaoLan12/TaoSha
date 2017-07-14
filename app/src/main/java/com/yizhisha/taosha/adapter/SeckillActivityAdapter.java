package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.SeckillActBean;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.DensityUtil;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/7/12.
 */

public class SeckillActivityAdapter extends BaseQuickAdapter<SeckillActBean,BaseViewHolder> {
    private long endtime;
    public SeckillActivityAdapter(@Nullable List<SeckillActBean> data) {
        super(R.layout.item_seckillact,data);
        this.endtime=System.currentTimeMillis()/1000+10;
    }

    @Override
    protected void convert(BaseViewHolder helper, SeckillActBean item) {
        item.setNowtime(System.currentTimeMillis()/1000);
        Log.e("PPP","石楠"+ DateUtil.getDateToString(System.currentTimeMillis()));
        item.setEndtime(endtime);
        helper.setText(R.id.seckillact_title_tv,item.getTitle());
        helper.setText(R.id.seckillact_market_price_tv1,String.valueOf(item.getMarket_price()));
        helper.setText(R.id.seckillact_seckill_price_tv,String.valueOf(item.getPrice()));

        if(item.getStarttime()>=item.getEndtime()){
            helper.setText(R.id.seckillact_activity_tv,"活动已结束");
        } else if(item.getNowtime()<item.getStarttime()){
            helper.setText(R.id.seckillact_activity_tv,"活动未开始");
        }
//        if(item.getNowtime()>item.getEndtime())
        else {
            long counttime = item.getEndtime()*1000-item.getNowtime()*1000;
            long days = counttime / (1000 * 60 * 60 * 24);
            long hours = (counttime-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (counttime-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            long second = (counttime-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60)-minutes*(1000*60))/1000;
            //并保存在商品time这个属性内
            String finaltime = days + "天" + hours + "时" + minutes + "分" + second + "秒";
            helper.setText(R.id.seckillact_activity_tv,finaltime);
        }
//        String.valueOf(DensityUtil.stampToDate(item.getNowtime()*1000))
//        helper.setText(R.id.seckillact_activity_tv,item.getNowtime()+"");

        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getLitpic(),
                (ImageView) helper.getView(R.id.seckillact_photo_iv),GlideUtil.LOAD_BITMAP);
    }
}
