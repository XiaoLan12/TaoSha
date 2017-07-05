package com.yizhisha.taosha.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.AddressListBean;

import java.util.List;

/**
 * Created by lan on 2017/6/27.
 */

public class MyAddressAdapter extends BaseQuickAdapter<AddressListBean.Address,BaseViewHolder> {
    public MyAddressAdapter(@Nullable List<AddressListBean.Address> data) {
        super(R.layout.item_myaddress,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, AddressListBean.Address item) {
        helper.setText(R.id.name_myaddress_tv,item.getLinkman());
        helper.setText(R.id.phone_myaddress_tv,item.getMobile());
        helper.setText(R.id.address_myaddress_tv,item.getAddress());
        if(item.getIndex().equals("1")){
            helper.setChecked(R.id.seletaddress_myaddress_cb,true);
        }else{
            helper.setChecked(R.id.seletaddress_myaddress_cb,false);
        }
        helper.addOnClickListener(R.id.edit_myaddress_tv);
        helper.addOnClickListener(R.id.delete_myaddress_tv);
    }
}
