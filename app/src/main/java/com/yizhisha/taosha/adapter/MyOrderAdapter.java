package com.yizhisha.taosha.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.bean.json.OrderHeadBean;
import com.yizhisha.taosha.utils.GlideUtil;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class MyOrderAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    public static final int ITEM_HEADER = 1;
    public static final int ITEM_CONTENT= 2;
    public static final int ITEM_FOOTER= 3;
    public MyOrderAdapter() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<Object>() {
            @Override
            protected int getItemType(Object order) {
                if(order instanceof OrderHeadBean) {
                    return ITEM_HEADER;
                }else if(order instanceof Goods){
                    return ITEM_CONTENT;
                }else if(order instanceof OrderFootBean){
                    return ITEM_FOOTER;
                }
                return ITEM_CONTENT;
            }
        });
        getMultiTypeDelegate().registerItemType(ITEM_HEADER, R.layout.item_head_myorder).
        registerItemType(ITEM_CONTENT, R.layout.item_middle_myorder).
        registerItemType(ITEM_FOOTER, R.layout.item_bottom_myorder);
    }


    @Override
    protected void convert(final BaseViewHolder helper, Object item) {
        switch (helper.getItemViewType()){
            case ITEM_HEADER:
                final OrderHeadBean order= (OrderHeadBean) item;
                helper.setText(R.id.company_myorder_tv,order.getCompany());
                if(order.getStatus()==0){
                    helper.setText(R.id.paystate_myorder_tv,"未付款");
                }else if(order.getStatus()==1){
                    helper.setText(R.id.paystate_myorder_tv,"待发货");
                }else if(order.getStatus()==2){
                    helper.setText(R.id.paystate_myorder_tv,"待收货");
                }else if(order.getStatus()==3){
                    helper.setText(R.id.paystate_myorder_tv,"已完成");
                }

                break;
            case ITEM_CONTENT:
                Goods goods= (Goods) item;
                helper.setText(R.id.tradename_myorder_tv,goods.getTitle());
                helper.setText(R.id.tradecolor_myorder_tv,goods.getRemark());
                helper.setText(R.id.tradeprice_myorder_tv,"￥"+goods.getTotalprice());
                helper.setText(R.id.tradecolor_myorder_tv,goods.getDetail());
                GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+goods.getLitpic(),
                        (ImageView) helper.getView(R.id.tradehead_myorder_iv),GlideUtil.LOAD_BITMAP);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                           mOnItemClickListener.onItemClick(v,ITEM_CONTENT,helper.getLayoutPosition());
                    }
                });
                break;
            case ITEM_FOOTER:
                final OrderFootBean orderFootBean= (OrderFootBean) item;
                helper.setText(R.id.tradeltotal_myorder_tv,orderFootBean.getTotalprice()+"");
                helper.setText(R.id.tradelnumber_total_tv,"共"+orderFootBean.getAmount()+"件商品");
                switchState(orderFootBean.getStatus(),helper);
                helper.addOnClickListener(R.id.immediate_evaluation_tv);
                break;
        }

    }
    private void switchState(int paystate,BaseViewHolder helper){
        switch (paystate){
            case 0:
                helper.setVisible(R.id.cancel_the_order_tv,true);
                helper.setVisible(R.id.immediate_payment_tv,true);
                helper.setVisible(R.id.confirm_goods_tv,false);
                helper.setVisible(R.id.immediate_evaluation_tv,false);
                helper.setVisible(R.id.againbuy_tv,false);
                break;
            case 1:
                helper.setVisible(R.id.cancel_the_order_tv,true);
                helper.setVisible(R.id.immediate_payment_tv,false);
                helper.setVisible(R.id.confirm_goods_tv,false);
                helper.setVisible(R.id.immediate_evaluation_tv,false);
                helper.setVisible(R.id.againbuy_tv,false);
                break;
            case 2:
                helper.setVisible(R.id.cancel_the_order_tv,false);
                helper.setVisible(R.id.immediate_payment_tv,false);
                helper.setVisible(R.id.confirm_goods_tv,true);
                helper.setVisible(R.id.immediate_evaluation_tv,false);
                helper.setVisible(R.id.againbuy_tv,false);
                break;
            case 3:
                helper.setVisible(R.id.cancel_the_order_tv,false);
                helper.setVisible(R.id.immediate_payment_tv,false);
                helper.setVisible(R.id.confirm_goods_tv,false);
                helper.setVisible(R.id.immediate_evaluation_tv,true);
                helper.setVisible(R.id.againbuy_tv,true);
                break;
        }
    }
    private OnItemTypeClickListener mOnItemClickListener;
    public interface OnItemTypeClickListener{
        void onItemClick(View view,int type, int position);
    }
    public void setOnItemTypeClickListener(OnItemTypeClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
