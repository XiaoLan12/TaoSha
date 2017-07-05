package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.FreeSampleBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class FreeSampleAdapter extends BaseQuickAdapter<FreeSampleBean.Active,BaseViewHolder> {
    public FreeSampleAdapter(@Nullable List<FreeSampleBean.Active> data) {
        super(R.layout.item_myorder,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FreeSampleBean.Active item) {
        helper.setText(R.id.paystate_myorder_tv,item.getIs_ship()==0?"未发货":"已发货");
        helper.setText(R.id.company_myorder_tv,item.getCompany());
        helper.setText(R.id.tradename_myorder_tv,item.getTitle());
        helper.setText(R.id.tradecolor_myorder_tv,item.getPname());

       if(item.getIs_ship()==0){
            switchState(2,helper);
        }
        else if(item.getIs_ship()==1){
            switchState(3,helper);
        }
    }
    private void switchState(int paystate,BaseViewHolder helper){
        switch (paystate){
            case 1:
                helper.setVisible(R.id.cancel_the_order_tv,true);
                helper.setVisible(R.id.immediate_payment_tv,true);
                helper.setVisible(R.id.confirm_goods_tv,false);
                helper.setVisible(R.id.immediate_evaluation_tv,false);
                helper.setVisible(R.id.againbuy_tv,false);
                helper.setVisible(R.id.immediate_payment_tv,false);
                break;
            case 2:
                helper.setVisible(R.id.cancel_the_order_tv,true);
                helper.setVisible(R.id.immediate_payment_tv,false);
                helper.setVisible(R.id.confirm_goods_tv,false);
                helper.setVisible(R.id.immediate_evaluation_tv,false);
                helper.setVisible(R.id.againbuy_tv,false);
                helper.setVisible(R.id.immediate_payment_tv,false);
                break;
            case 3:
                helper.setVisible(R.id.cancel_the_order_tv,false);
                helper.setVisible(R.id.immediate_payment_tv,false);
                helper.setVisible(R.id.confirm_goods_tv,true);
                helper.setVisible(R.id.immediate_evaluation_tv,false);
                helper.setVisible(R.id.againbuy_tv,false);
                helper.setVisible(R.id.immediate_payment_tv,false);
                break;
            case 4:
                helper.setVisible(R.id.cancel_the_order_tv,false);
                helper.setVisible(R.id.immediate_payment_tv,false);
                helper.setVisible(R.id.confirm_goods_tv,false);
                helper.setVisible(R.id.immediate_evaluation_tv,true);
                helper.setVisible(R.id.againbuy_tv,true);
                helper.setVisible(R.id.immediate_payment_tv,false);
                break;
        }
    }
}
