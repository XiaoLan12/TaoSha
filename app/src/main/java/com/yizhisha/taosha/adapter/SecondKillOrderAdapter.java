package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.SeckillBean;
import com.yizhisha.taosha.bean.json.SeckillGoodsBean;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class SecondKillOrderAdapter extends BaseQuickAdapter<SeckillBean,BaseViewHolder> {
    public SecondKillOrderAdapter(@Nullable List<SeckillBean> data) {
        super(R.layout.item_secondkill,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SeckillBean item) {
        if(item.getStatus()==0){
            helper.setText(R.id.paystate_tv,"未付款");
        }else if(item.getStatus()==1){
            helper.setText(R.id.paystate_tv,"待发货");
        }else if(item.getStatus()==2){
            helper.setText(R.id.paystate_tv,"待收货");
        }else if(item.getStatus()==3){
            helper.setText(R.id.paystate_tv,"交易完成");
        }
        else if(item.getStatus()==4){
            helper.setText(R.id.paystate_tv,"交易完成");
        }

        helper.setText(R.id.tradename_tv,item.getTitle());
        helper.setText(R.id.seckillprice_tv,String.valueOf(item.getSeckilling_price()));
        helper.setText(R.id.marketprice_tv,"￥"+item.getTotalprice());
        helper.setText(R.id.tradeltotal_tv,String.valueOf(item.getTotalprice()));
        SeckillGoodsBean goods=item.getGoods();
        helper.setText(R.id.company_tv,goods.getCompany());
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+goods.getLitpic(),
                (ImageView) helper.getView(R.id.tradehead_iv),GlideUtil.LOAD_BITMAP);
        switchState(item.getStatus(),helper);
        helper.addOnClickListener(R.id.cancel_the_order_tv);
        helper.addOnClickListener(R.id.contact_the_merchant_tv);

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
                helper.setVisible(R.id.againbuy_tv,false);
                break;
            case 4:
                helper.setVisible(R.id.cancel_the_order_tv,false);
                helper.setVisible(R.id.immediate_payment_tv,false);
                helper.setVisible(R.id.confirm_goods_tv,false);
                helper.setVisible(R.id.immediate_evaluation_tv,false);
                helper.setVisible(R.id.additional_comments_tv,true);
                helper.setVisible(R.id.againbuy_tv,false);
                break;
        }
    }
}
