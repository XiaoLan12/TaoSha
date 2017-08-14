package com.yizhisha.taosha.ui.home.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.ui.home.contract.SureOrderContract;
import com.yizhisha.taosha.ui.home.precenter.SureOrderPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SureOrderActivity extends BaseActivity<SureOrderPresenter>
        implements SureOrderContract.View {

    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.consignee_name_tv)
    TextView consigneeNameTv;
    @Bind(R.id.consignee_phone_tv)
    TextView consigneePhoneTv;
    @Bind(R.id.shippingaddress_tv)
    TextView shippingaddressTv;
    @Bind(R.id.shippingaddress_ll)
    RelativeLayout shippingaddressLl;

    @Bind(R.id.tradehead_iv)
    ImageView tradeheadIv;
    @Bind(R.id.tradename_tv)
    TextView tradenameTv;
    @Bind(R.id.tradecolor_tv)
    TextView tradecolorTv;
    @Bind(R.id.tradeprice_tv)
    TextView tradepriceTv;
    @Bind(R.id.distribution_way_tv)
    TextView distributionWayTv;
    @Bind(R.id.pay_way_tv)
    TextView payWayTv;
    @Bind(R.id.cost_tv)
    TextView costTv;
    @Bind(R.id.ishave_freight_tv)
    TextView ishaveFreightTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sure_order;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {
        Map<String,String> map=new HashMap();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("gid",String.valueOf(AppConstant.UID));
        map.put("type",String.valueOf(AppConstant.UID));
        map.put("detail",String.valueOf(AppConstant.UID));
        map.put("amount",String.valueOf(AppConstant.UID));
        mPresenter.loadOrderSure(map);
    }

    @Override
    public void loadSuccess(OrderSureBean data) {
        OrderSureBean.Address address=data.getAddress().get(0);
        consigneeNameTv.setText(address.getLinkman());
        consigneePhoneTv.setText(address.getMobile());
        shippingaddressTv.setText(address.getArea_pname());
        tradenameTv.setText(data.getTitle());
        tradecolorTv.setText(data.getDetail());
        tradepriceTv.setText("￥"+data.getPrice());

    }

    @Override
    public void loadFail(String msg) {

    }

}
