package com.yizhisha.taosha.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.adapter.MyOrderDetailsAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.ui.me.contract.OrderDetailsContract;
import com.yizhisha.taosha.ui.me.presenter.OrderDetailsPresenter;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.LogUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

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
    @Bind(R.id.shippingaddress_orderdetailss_tv)
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
    @Bind(R.id.additional_comments_tv)
    TextView mTvAddItionalComment;
    @Bind(R.id.againbuy_tv)
    TextView mTvAgeinBuy;
    @Bind(R.id.cancel_the_order_tv)
    TextView mTvCancelTheOrder;
    @Bind(R.id.immediate_payment_tv)
    TextView mTvImmediatePayment;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    private MyOrderDetailsAdapter mAdapter;
    private Order order;
    private List<Goods> dataList=new ArrayList<>();
    private String orderNo="";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        initAdapter();
        Bundle bundle=getIntent().getExtras();
        orderNo=bundle.getString("ORDERNO");
        load(orderNo,true);

    }
    private void load(String orderNo,boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("orderno",orderNo);
        mPresenter.loadOrderDetails(map,false);
    }
    private void init(){
        if(order==null){
            return;
        }
        String state[]=new String[]{"您还未支付该订单","买家已付款,代发货","商家已发货","交易成功","已评价"};
        String tip[]=new String[]{"请尽快支付","请耐心等待,我们会尽快给您发货","请耐心等待,我们会尽快给您送货","欢迎再次光临","已评价"};
        mTvPayState.setText(state[order.getStatus()]);
        mTvShopTip.setText(tip[order.getStatus()]);
        mTvConsignee.setText(order.getLinkman());
        mTvConsigneePhone.setText(order.getMobile());
        mTvShipAddress.setText(order.getAddress());
        mTvCompay.setText(order.getCompany());
        mTvOrderNo.setText(order.getOrderno());
        mTvOrderTime.setText(order.getAddtime());
        mTvPayWay.setText(order.getPayment()+"");
        mTvPayTime.setText(DateUtil.getDateToString(order.getPaytime()*1000));
        mTvDistributionway.setText("三四十");
        mTvTradelTotal.setText(order.getGoods_price()+"");
        switchState(order.getStatus());
    }
    private void initAdapter(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter=new MyOrderDetailsAdapter(this,dataList);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void loadoSuccess(List<Order> data) {
        dataList.clear();
        order=data.get(0);
        init();
        dataList=order.getGoods();
        mAdapter.setNewData(dataList);
    }
    @Override
    public void showLoading() {
        mLoadingView.load("");
    }
    @Override
    public void hideLoading() {
        mLoadingView.loadSuccess();
    }
    @Override
    public void showEmpty() {
        mLoadingView.loadSuccess(true);
    }
    @Override
    public void loadFail(String msg) {
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(orderNo,true);
            }
        });

        mLoadingView.loadError();
    }
    @OnClick({R.id.cancel_the_order_tv,R.id.contact_the_merchant_tv,R.id.confirm_goods_tv,
            R.id.immediate_evaluation_tv,R.id.againbuy_tv,R.id.immediate_payment_tv,R.id.additional_comments_tv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_the_order_tv:
                break;
            case R.id.contact_the_merchant_tv:
                break;
            case R.id.confirm_goods_tv:
                break;
            case R.id.immediate_evaluation_tv:
                Bundle commentbundle = new Bundle();
                commentbundle.putInt("TYPE",1);
                commentbundle.putInt("ORDERID",order.getId());
                commentbundle.putInt("MZWUIID",order.getMzw_uid());
                startActivity(AddCommentActivity.class,commentbundle);
                break;
            case R.id.againbuy_tv:
                break;
            case R.id.immediate_payment_tv:
                break;
            case R.id.additional_comments_tv:
                Bundle addCommentbundle = new Bundle();
                addCommentbundle.putInt("TYPE",2);
                addCommentbundle.putInt("ORDERID",order.getId());
                addCommentbundle.putInt("MZWUIID",order.getMzw_uid());
                startActivity(AddCommentActivity.class,addCommentbundle);
                break;
        }
    }




    /**根据交易状态，切换布局显示
     * @param paystate
     * 0 未付款
    1 待发货
    2 待收货
    3 已收货
     */
    private void switchState(int paystate){
        switch (paystate){

            case 0:
                mIvPayState.setImageResource(R.drawable.icon_weifukuan);
                mTvCancelTheOrder.setVisibility(View.VISIBLE);
                mTvImmediatePayment.setVisibility(View.VISIBLE);
                mTvConfirmGoods.setVisibility(View.GONE);
                mTvImmediateEvaluation.setVisibility(View.GONE);
                mTvAddItionalComment.setVisibility(View.GONE);
                mTvAgeinBuy.setVisibility(View.GONE);
                break;
            case 1:
                mIvPayState.setImageResource(R.drawable.icon_daifahuo);
                mTvCancelTheOrder.setVisibility(View.VISIBLE);
                mTvImmediatePayment.setVisibility(View.GONE);
                mTvConfirmGoods.setVisibility(View.GONE);
                mTvImmediateEvaluation.setVisibility(View.GONE);
                mTvAddItionalComment.setVisibility(View.GONE);
                mTvAgeinBuy.setVisibility(View.GONE);
                break;
            case 2:
                mIvPayState.setImageResource(R.drawable.icon_daishouhuo);
                mTvCancelTheOrder.setVisibility(View.GONE);
                mTvImmediatePayment.setVisibility(View.GONE);
                mTvConfirmGoods.setVisibility(View.VISIBLE);
                mTvImmediateEvaluation.setVisibility(View.GONE);
                mTvAddItionalComment.setVisibility(View.GONE);
                mTvAgeinBuy.setVisibility(View.GONE);
                break;
            case 3:
                mIvPayState.setImageResource(R.drawable.icon_jiaoyiwancheng);
                mTvCancelTheOrder.setVisibility(View.GONE);
                mTvImmediatePayment.setVisibility(View.GONE);
                mTvConfirmGoods.setVisibility(View.GONE);
                mTvImmediateEvaluation.setVisibility(View.VISIBLE);
                mTvAddItionalComment.setVisibility(View.GONE);
                mTvAgeinBuy.setVisibility(View.VISIBLE);
                break;
            case 4:
                mIvPayState.setImageResource(R.drawable.icon_jiaoyiwancheng);
                mTvCancelTheOrder.setVisibility(View.GONE);
                mTvImmediatePayment.setVisibility(View.GONE);
                mTvConfirmGoods.setVisibility(View.GONE);
                mTvImmediateEvaluation.setVisibility(View.GONE);
                mTvAddItionalComment.setVisibility(View.VISIBLE);
                mTvAgeinBuy.setVisibility(View.VISIBLE);
                break;
        }
    }
}
