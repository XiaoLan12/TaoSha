package com.yizhisha.taosha.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/16.
 */

public class ProductDetailImgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context mContext;
    public ProductDetailImgAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.item_fragment_product_img,data);
        this.mContext=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, String goods) {
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.PRODUCT_DETAIL_CONTENT_IMG_URL+goods,
                (ImageView) helper.getView(R.id.img), GlideUtil.LOAD_BITMAP);
    }
}