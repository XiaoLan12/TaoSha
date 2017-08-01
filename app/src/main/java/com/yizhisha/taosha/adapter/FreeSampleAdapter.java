package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class FreeSampleAdapter extends BaseQuickAdapter<FreeSampleBean.Active,BaseViewHolder> {
    public FreeSampleAdapter(@Nullable List<FreeSampleBean.Active> data) {
        super(R.layout.item_freesample,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FreeSampleBean.Active item) {

        helper.setText(R.id.paystate_tv,item.getIs_ship()==0?"已发货":"未发货");
        helper.setText(R.id.company_tv,item.getCompany());
        helper.setText(R.id.tradename_tv,item.getTitle());
        GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getLitpic(),
                (ImageView) helper.getView(R.id.tradehead_iv),GlideUtil.LOAD_BITMAP);
        helper.addOnClickListener(R.id.cancel_the_order_tv);

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
