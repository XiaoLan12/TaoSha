package com.yizhisha.taosha.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class SelectYarnColorAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context mContext;
    public SelectYarnColorAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.item_select_yarn_color,data);
        this.mContext=context;
    }
    @Override
    protected void convert(final BaseViewHolder helper, String goods) {

            helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    try {
                        TextView tv_num=    (TextView)helper.getView(R.id.tv_num);
                        Log.e("RRR",tv_num.getText().toString().trim()+"--");
                        int num1=Integer.getInteger(tv_num.getText().toString().trim());
                        num1++;
                        ((TextView) helper.getView(R.id.tv_num)).setText(num1+"--");
                    }catch (Exception e){

                    }

                }
            });
        helper.getView(R.id.tv_reduce).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                TextView tv_num=    (TextView)helper.getView(R.id.tv_num);
                String num=tv_num.getText().toString().trim();
                int num1=Integer.getInteger(num);
                if(num1!=1){
                    num1--;
                    ((TextView) helper.getView(R.id.tv_num)).setText(num1+"");
                }
                }catch (Exception e){

                }

            }
        });

    }
}