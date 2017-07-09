package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.HomeYarnTypeEntity;
import com.yizhisha.taosha.bean.json.IndexDeatailYarnBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;

import java.util.List;
import java.util.UUID;

/**
 * Created by ude on 2017/6/29.
 */

public class HomeYarnRecommendAdapter extends BaseQuickAdapter<IndexDeatailYarnBean,BaseViewHolder> {
    public HomeYarnRecommendAdapter(@Nullable List<IndexDeatailYarnBean> data) {
        super(R.layout.item_home_recommend,data);

    }
    @Override
    protected void convert(BaseViewHolder helper, IndexDeatailYarnBean item) {
        helper.setText(R.id.tv_name,item.getTitle());
//        ((ImageView)helper.getView(R.id.img_type)).setImageResource(R.mipmap.ic_launcher);
        Glide.with(mContext).load(AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getLitpic())
                .signature(new StringSignature(UUID.randomUUID().toString()))  // 重点在这行
                .diskCacheStrategy( DiskCacheStrategy.NONE )//禁用磁盘缓存
                .skipMemoryCache( true )//跳过内存缓存
                .into((ImageView)helper.getView(R.id.img_type));
    }
}