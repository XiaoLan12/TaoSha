package com.yizhisha.taosha.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.HomeYarnTypeEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */

public class HomeYarnTypeAdapter extends BaseQuickAdapter<HomeYarnTypeEntity,BaseViewHolder> {
    public HomeYarnTypeAdapter(@Nullable List<HomeYarnTypeEntity> data) {
        super(R.layout.item_home_yarn_type,data);


    }
    @Override
    protected void convert(BaseViewHolder helper, HomeYarnTypeEntity item) {
            helper.setText(R.id.tv_name,item.getName());

//        ( (ImageView)helper.getView(R.id.img_type)).setImageResource(item.getImg());
      TextView textView=  (TextView)helper.getView(R.id.tv_name);

        Drawable rightDrawable = mContext.getResources().getDrawable(item.getImg());
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        textView.setCompoundDrawables(null,rightDrawable, null, null);

    }
}
