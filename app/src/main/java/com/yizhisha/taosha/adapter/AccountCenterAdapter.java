package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.AccountBean;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.RescourseUtil;

import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class AccountCenterAdapter extends BaseQuickAdapter<AccountBean.AccountList,BaseViewHolder> {
    public AccountCenterAdapter(@Nullable List<AccountBean.AccountList> data) {
        super(R.layout.item_accountcenter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountBean.AccountList item) {
        helper.setText(R.id.title_accountcenter_tv,item.getDetail());

        if(item.getType()==1){
            helper.setText(R.id.deduct_accountcenter_tv,"+"+item.getMoney());
            helper.setTextColor(R.id.deduct_accountcenter_tv,RescourseUtil.getColor(R.color.common_h1));
        }else{
            helper.setText(R.id.deduct_accountcenter_tv,"-"+item.getMoney());
            helper.setTextColor(R.id.deduct_accountcenter_tv, RescourseUtil.getColor(R.color.red1));
        }
        helper.setText(R.id.total_accountcenter_tv,"剩余:"+item.getLeft_money());
        helper.setText(R.id.time_accountcenter_tv, DateUtil.getDateToString1(item.getAddtime()*1000));
    }
}
