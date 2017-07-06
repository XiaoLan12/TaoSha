package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.OrderBean;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.bean.json.OrderHeadBean;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class MyOrderAdapter extends BaseMultiItemQuickAdapter<OrderBean,BaseViewHolder> {
    private int ITEM_HEADER = 1,ITEM_CONTENT = 2,ITEM_FOOTER = 3;
    public MyOrderAdapter() {
        super(null);
        /*setMultiTypeDelegate(new MultiTypeDelegate<OrderBean>() {
            @Override
            protected int getItemType(OrderBean order) {
                if(order instanceof OrderHeadBean) {
                    return ITEM_HEADER;
                }else if(order instanceof Goods){
                    return ITEM_CONTENT;
                }else if(order instanceof OrderFootBean){
                    return ITEM_FOOTER;
                }
                return ITEM_CONTENT;
            }
        });*/
       // getMultiTypeDelegate(new MultiTypeDelegate<>())
      /*  addItemType(OrderBean.TYPE_1, R.layout.item_head_myorder);
        addItemType(OrderBean.TYPE_2, R.layout.item_middle_myorder);*/
        //addItemType(Order.TYPE_3, R.layout.item_bottom_myorder);
    }


    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        switch (helper.getItemViewType()){
            case OrderBean.TYPE_1:
                Log.d("TTT","进来啦aa");
                //final Order order= (Order) item;
               /* helper.setText(R.id.company_myorder_tv,order.getCompany());
                List<Goods> goods=order.getGoods();
                for(int i=0;i<goods.size();i++){
                    helper.setText(R.id.tradename_myorder_tv,goods.get(i).getTitle());
                    helper.setText(R.id.tradecolor_myorder_tv,goods.get(i).getRemark());
                    helper.setText(R.id.tradeprice_myorder_tv,"￥"+goods.get(i).getTotalprice());
                }*/
                break;
           /* case OrderBean.TYPE_2:
               // Goods goods= (Goods) item;
                Log.d("TTT","进来啦bb");
               *//* *//*
                break;*/
            /*case Order.TYPE_3:
                Log.d("TTT","进来啦CC");
                //helper.setText(R.id.tradeltotal_myorder_tv,order1.getPayment());
                break;*/
        }
      /*  if(item.equals("待付款")){
            switchState(1,helper);

        }else if(item.equals("待发货")){
            switchState(2,helper);
        }
        else if(item.equals("待收货")){
            switchState(3,helper);
        }
        else if(item.equals("已完成")){
            switchState(4,helper);
        }*/
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
