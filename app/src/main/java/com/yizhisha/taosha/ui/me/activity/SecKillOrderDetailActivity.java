package com.yizhisha.taosha.ui.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderDetailsAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.SeckillBean;
import com.yizhisha.taosha.bean.json.SeckillGoodsBean;
import com.yizhisha.taosha.bean.json.SeckillListBean;
import com.yizhisha.taosha.ui.me.contract.SecKillOrderDetailsContract;
import com.yizhisha.taosha.ui.me.presenter.SecKillOrderDetailsPresenter;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SecKillOrderDetailActivity extends BaseActivity<SecKillOrderDetailsPresenter>
        implements SecKillOrderDetailsContract.View {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
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
    @Bind(R.id.againbuy_tv)
    TextView mTvAgeinBuy;
    @Bind(R.id.cancel_the_order_tv)
    TextView mTvCancelTheOrder;
    @Bind(R.id.immediate_payment_tv)
    TextView mTvImmediatePayment;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;

    @Bind(R.id.tradehead_myorder_iv)
    ImageView mIvShopPhoto;
    @Bind(R.id.tradename_myorder_tv)
    TextView mTvShopTitle;
    @Bind(R.id.tradecolor_myorder_tv)
    TextView mTvShopColor;
    @Bind(R.id.tradeprice_myorder_tv)
    TextView mTvShopPrice;


    private SeckillBean seckillBean;
    private String orderNo="";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_seckillorder_details;
    }
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        orderNo=bundle.getString("ORDERNO");
        load(orderNo,true);
    }
    private void load(String orderNo,boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("orderno",orderNo);
        mPresenter.loadSecKillOrderDetails(map,false);
    }
    private void init(){
        if(seckillBean==null){
            return;
        }
        String state[]=new String[]{"您还未支付该订单","买家已付款,代发货","商家已发货","交易成功"};
        String tip[]=new String[]{"请尽快支付","请耐心等待,我们会尽快给您发货","请耐心等待,我们会尽快给您送货","欢迎再次光临"};
        mTvPayState.setText(state[seckillBean.getStatus()]);
        mTvShopTip.setText(tip[seckillBean.getStatus()]);
        mTvConsignee.setText(seckillBean.getLinkman());
        mTvConsigneePhone.setText(seckillBean.getMobile());
        mTvShipAddress.setText(seckillBean.getAddress());

        mTvOrderNo.setText(seckillBean.getOrderno());
        mTvOrderTime.setText(seckillBean.getAddtime());
        mTvPayWay.setText(seckillBean.getPayment()+"");
        mTvPayTime.setText(DateUtil.getDateToString(seckillBean.getPaytime()*1000));
        mTvDistributionway.setText("三四十");
        mTvTradelTotal.setText(seckillBean.getMarket_price()+"");
        if(seckillBean.getGoods()!=null){
            SeckillGoodsBean goodsBean=seckillBean.getGoods();
            mTvCompay.setText(goodsBean.getCompany());
            mTvShopTitle.setText(goodsBean.getTitle());
            mTvShopColor.setText(goodsBean.getIngredient());
            mTvShopPrice.setText(seckillBean.getMarket_price()+"");
            GlideUtil.getInstance().LoadContextBitmap(mContext,goodsBean.getLitpic(),mIvShopPhoto,GlideUtil.LOAD_BITMAP);
        }
        switchState(seckillBean.getStatus());
    }
    @Override
    public void loadoSecKillOrderSuccess(List<SeckillBean> data) {
        seckillBean=data.get(0);
        init();
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
                mTvAgeinBuy.setVisibility(View.GONE);
                break;
            case 1:
                mIvPayState.setImageResource(R.drawable.icon_daifahuo);
                mTvCancelTheOrder.setVisibility(View.VISIBLE);
                mTvImmediatePayment.setVisibility(View.GONE);
                mTvConfirmGoods.setVisibility(View.GONE);
                mTvImmediateEvaluation.setVisibility(View.GONE);
                mTvAgeinBuy.setVisibility(View.GONE);
                break;
            case 2:
                mIvPayState.setImageResource(R.drawable.icon_daishouhuo);
                mTvCancelTheOrder.setVisibility(View.GONE);
                mTvImmediatePayment.setVisibility(View.GONE);
                mTvConfirmGoods.setVisibility(View.VISIBLE);
                mTvImmediateEvaluation.setVisibility(View.GONE);
                mTvAgeinBuy.setVisibility(View.GONE);
                break;
            case 3:
                mIvPayState.setImageResource(R.drawable.icon_jiaoyiwancheng);
                mTvCancelTheOrder.setVisibility(View.GONE);
                mTvImmediatePayment.setVisibility(View.GONE);
                mTvConfirmGoods.setVisibility(View.GONE);
                mTvImmediateEvaluation.setVisibility(View.VISIBLE);
                mTvAgeinBuy.setVisibility(View.VISIBLE);
                break;
        }
    }
}
