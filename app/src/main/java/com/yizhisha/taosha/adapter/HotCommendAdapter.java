package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.HotCommendBean;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/8/18.
 */

public class HotCommendAdapter extends BaseQuickAdapter<HotCommendBean.Goods,BaseViewHolder>{
    public HotCommendAdapter(@Nullable List<HotCommendBean.Goods> data) {
        super(R.layout.item_hotcommend,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotCommendBean.Goods item) {
        helper.setText(R.id.title_tv,item.getTitle());
        helper.setText(R.id.price_tv,"￥:"+item.getPrice()+"元");
        helper.setText(R.id.price_real_tv,item.getPrice_real()+"/份");
        helper.setText(R.id.ingredient_tv,item.getIngredient());
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getLitpic(),
                (ImageView) helper.getView(R.id.pic_iv),GlideUtil.LOAD_BITMAP);
    }
}
