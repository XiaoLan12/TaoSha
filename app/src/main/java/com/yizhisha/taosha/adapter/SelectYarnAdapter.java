package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.SearchDetailBean;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class SelectYarnAdapter extends BaseQuickAdapter<SearchDetailBean,BaseViewHolder>{
    public SelectYarnAdapter(@Nullable List<SearchDetailBean> data) {
        super(R.layout.item_selectyarn,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SearchDetailBean item) {
        helper.setText(R.id.unitprice_selectyarn_tv,"￥"+item.getPrice()+"/份");
        helper.setText(R.id.shopname_selectyarn_tv,item.getTitle());
        helper.setText(R.id.composition_selectyarn_tv,item.getIngredient());
        helper.setText(R.id.fit_pittype_selectyarn_tv,item.getNeedle_name());
        helper.setText(R.id.totalprice_selectyarn_tv,"￥"+item.getPrice());
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getLitpic(),
                (ImageView) helper.getView(R.id.shophead_selectyarn_iv),GlideUtil.LOAD_BITMAP);

    }
}
