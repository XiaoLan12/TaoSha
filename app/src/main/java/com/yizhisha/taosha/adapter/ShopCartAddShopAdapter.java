package com.yizhisha.taosha.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.SelectYarnBean;
import com.yizhisha.taosha.bean.json.ShopCartBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class ShopCartAddShopAdapter extends BaseQuickAdapter<ShopCartBean.ShopcartList,BaseViewHolder> {
    public ShopCartAddShopAdapter(@Nullable List<ShopCartBean.ShopcartList> data) {
        super(R.layout.item_select_yarn_color,data);
    }
    @Override
    protected void convert(final BaseViewHolder helper, final ShopCartBean.ShopcartList item) {
        if(item.isAdd()){
            helper.setImageResource(R.id.img_delete,R.drawable.icon_addimage);
        }else{
            helper.setImageResource(R.id.img_delete,R.drawable.icon_shanchushuxing);
        }
        helper.addOnClickListener(R.id.img_delete);
        helper.setText(R.id.tv_num,item.getAmount()+"");
       final EditText et_color=(EditText) helper.getView(R.id.et_color);
        if(et_color.getTag() instanceof TextWatcher) {
            et_color.removeTextChangedListener((TextWatcher)et_color.getTag());
        }
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                item.setDetail(et_color.getText().toString());
            }
        };
        et_color.addTextChangedListener(watcher);
        et_color.setTag(watcher);
        et_color.setText(item.getDetail());
            helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                        TextView tv_num=    (TextView)helper.getView(R.id.tv_num);
                        int num1= item.getAmount();
                        num1++;
                        item.setAmount(num1);
                        ((TextView) helper.getView(R.id.tv_num)).setText(num1+"");

                }
            });
        helper.getView(R.id.tv_reduce).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView tv_num=    (TextView)helper.getView(R.id.tv_num);
                int num1 = item.getAmount();

                if(num1!=1){
                    num1--;
                    item.setAmount(num1);
                    ((TextView) helper.getView(R.id.tv_num)).setText(num1+"");
                }

            }
        });

    }
}