package com.yizhisha.taosha.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.ui.me.contract.OrderDetailsContract;
import com.yizhisha.taosha.ui.me.presenter.OrderDetailsPresenter;
import com.yizhisha.taosha.utils.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class OrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> implements OrderDetailsContract.View{
    @Bind(R.id.paystate_orderdetails_tv)
    TextView mTvPayState;
    @Bind(R.id.paystate_orderdetails_iv)
    ImageView mIvPayState;
    @Bind(R.id.shophint_orderdetails_tv)
    TextView mTvShopTip;
    @Bind(R.id.consignee_name_orderdetails_tv)
    TextView mTvConsignee;
    @Bind(R.id.consignee_phone_orderdetails_tv)
    TextView mTvConsigneePhone;
    @Bind(R.id.consignee_phone_orderdetails_tv)
    TextView mTvShipAddress;
    @Bind(R.id.company_orderdetails_tv)
    TextView mTvCompay;
    @Bind(R.id.orderno_orderdetail_tv)
    TextView mTvOrderNo;
    @Bind(R.id.ordertime_orderdetail_tv)
    TextView mTvOrderTime;
    @Bind(R.id.payway_orderdetail_tv)
    TextView mTvPayWay;
    @Bind(R.id.paytime_orderdetail)
    TextView mTvPayTime;
    @Bind(R.id.distributionway_orderdetail_tv)
    TextView mTvDistributionway;
    @Bind(R.id.tradeltotal_myorder_tv)
    TextView mTvTradelTotal;
    @Bind(R.id.tradelpaymentpay_myorder_tv)
    TextView mTvTradelPaymentPay;
    @Bind(R.id.contact_the_merchant_tv)
    TextView mTvContactTheMerchant;
    @Bind(R.id.confirm_goods_tv)
    TextView mTvConfirmGoods;
    @Bind(R.id.immediate_evaluation_tv)
    TextView mTvImmediateEvaluation;
    @Bind(R.id.againbuy_tv)
    TextView mTvAgeonBuy;
    @Bind(R.id.cancel_the_order_tv)
    TextView mTvCancelTheOrder;
    @Bind(R.id.immediate_payment_tv)
    TextView mTvImmediatePayment;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        String orderNo=bundle.getString("ORDERNO");
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("orderno",orderNo);
        mPresenter.loadOrderDetails(map,false);
    }
    @Override
    public void loadoSuccess(List<Order> data) {
    }
    @Override
    public void showLoading() {

    }
    @Override
    public void hideLoading() {

    }
    @Override
    public void showEmpty() {

    }
    @Override
    public void loadFail(String msg) {

    }
}
