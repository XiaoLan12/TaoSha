package com.yizhisha.taosha.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.SelectYarnBean;

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
        if(goods.isAdd()){
            helper.setImageResource(R.id.img_delete,R.drawable.icon_addimage);
        }else{
            helper.setImageResource(R.id.img_delete,R.drawable.icon_shanchushuxing);
        }
        helper.setText(R.id.tv_num,goods.getNum()+"");
        final EditText et_color=(EditText) helper.getView(R.id.et_color);
        if(et_color.getTag() instanceof TextWatcher) {
            et_color.removeTextChangedListener((TextWatcher)et_color.getTag());
        }
        et_color.setText(goods.getColor());
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {

                goods.setColor(et_color.getText().toString());
            }
        };
        et_color.addTextChangedListener(watcher);
        et_color.setTag(watcher);

        helper.addOnClickListener(R.id.tv_add);
        helper.addOnClickListener(R.id.tv_reduce);
         /*   helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener(){
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

                if(num1>0){
                    num1--;
                    goods.setNum(num1);
                    ((TextView) helper.getView(R.id.tv_num)).setText(num1+"");
                }

            }
        });*/

    }
}