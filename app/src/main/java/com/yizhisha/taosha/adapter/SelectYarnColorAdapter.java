package com.yizhisha.taosha.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.SelectYarnBean;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class SelectYarnColorAdapter extends BaseQuickAdapter<SelectYarnBean,BaseViewHolder> {
    private Context mContext;
    public SelectYarnColorAdapter(Context context, @Nullable List<SelectYarnBean> data) {
        super(R.layout.item_select_yarn_color,data);
        this.mContext=context;
    }
    @Override
    protected void convert(final BaseViewHolder helper, final SelectYarnBean goods) {
        helper.addOnClickListener(R.id.img_delete);
        helper.setText(R.id.tv_num,goods.getNum()+"");
        helper.setText(R.id.et_color,goods.getColor());
       final EditText et_color=(EditText) helper.getView(R.id.et_color);
        et_color.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            goods.setColor(et_color.getText().toString());
            }
        });
            helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                        TextView tv_num=    (TextView)helper.getView(R.id.tv_num);
                        int num1= goods.getNum();
                        num1++;
                         goods.setNum(num1);
                        ((TextView) helper.getView(R.id.tv_num)).setText(num1+"");

                }
            });
        helper.getView(R.id.tv_reduce).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView tv_num=    (TextView)helper.getView(R.id.tv_num);
                int num1 = goods.getNum();

                if(num1!=1){
                    num1--;
                    goods.setNum(num1);
                    ((TextView) helper.getView(R.id.tv_num)).setText(num1+"");
                }

            }
        });

    }
}