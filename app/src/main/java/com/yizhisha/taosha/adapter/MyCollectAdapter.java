package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.widget.SwipeMenuLayout;

import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class MyCollectAdapter extends BaseQuickAdapter<CollectListBean.Favorite,BaseViewHolder>{
    public MyCollectAdapter(@Nullable List<CollectListBean.Favorite> data) {
        super(R.layout.item_mycollect,data);
    }
    @Override
    protected void convert(final BaseViewHolder helper, CollectListBean.Favorite item) {
        helper.setText(R.id.unitprice_mycollect_tv,"￥"+item.getPrice()+"/份");
        helper.setText(R.id.shopname_mycollect_tv,item.getTitle());
        helper.setText(R.id.composition_mycollect_tv,item.getIngredient());
        helper.setText(R.id.fit_pittype_mycollect_tv,item.getNeedle_name());
        helper.setText(R.id.totalprice_mycollect_tv,"￥"+item.getPrice());
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getLitpic(),
                (ImageView) helper.getView(R.id.shophead_mycollect_iv),GlideUtil.LOAD_BITMAP);
        helper.addOnClickListener(R.id.btnDelete);

    }

}
