package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.IndexADSBean;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by lan on 2017/8/18.
 */

public class ProductsCommendAdapter extends BaseQuickAdapter<IndexADSBean,BaseViewHolder>{
    public ProductsCommendAdapter(@Nullable List<IndexADSBean> data) {
        super(R.layout.item_productcommend,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexADSBean item) {
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_BANK_IMG_URL+item.getImg(),
                (ImageView) helper.getView(R.id.pic_iv),GlideUtil.LOAD_BITMAP);
    }
}
