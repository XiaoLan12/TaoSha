package com.yizhisha.taosha.ui.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.adapter.MyOrderDetailsAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.event.WeChatPayEvent;
import com.yizhisha.taosha.ui.home.activity.SureOrderActivity;
import com.yizhisha.taosha.ui.home.activity.YarnActivity;
import com.yizhisha.taosha.ui.me.contract.OrderDetailsContract;
import com.yizhisha.taosha.ui.me.presenter.OrderDetailsPresenter;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.LogUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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
    private Subscription subscription;

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
        event();

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
        switchState(order.getStatus(),order.getPayment());
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
    public void sureGoodsSuuccess(String msg) {
        ToastUtil.showShortToast(msg);
        setResult(2);
        finish_Activity(this);
    }

    @Override
    public void cancleOrder(String msg) {
        ToastUtil.showShortToast(msg);
        setResult(2);
        finish_Activity(this);
    }

    @Override
    public void weChatPay(PayReqBean bean) {
        PayReq req = new PayReq();
        req.appId			= bean.getAppid();
        req.partnerId		= bean.getPartnerid();
        req.prepayId		= bean.getPrepayid();
        req.nonceStr		= bean.getNoncestr();
        req.timeStamp		= bean.getTimestamp();
        req.packageValue	= "Sign=WXPay";
        req.sign			= bean.getSign();
        req.extData			= "app data"; // optional
        toWeiXinPay(req);
    }
    @Override
    public void loadWeChatPayState(WeChatPayStateBean bean) {
        new NormalAlertDialog.Builder(this)
                .setTitleText("支付结果")
                .setContentText(bean.getReturn_msg()+"请到\"我的订单\"查看供应商的联系方式,及时与供应商取得联系")
                .setSingleModel(true)
                .setSingleText("确认")
                .setWidth(0.75f)
                .setHeight(0.33f)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();
                        finish_Activity(OrderDetailsActivity.this);
                    }
                }).build().show();
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

    @Override
    public void cancelFail(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void loadWeChatPayStateFail(String msg) {
        new NormalAlertDialog.Builder(this)
                .setBoolTitle(false)
                .setContentText(msg)
                .setContentTextColor(R.color.blue)
                .setSingleModel(true)
                .setSingleText("确认")
                .setWidth(0.75f)
                .setHeight(0.33f)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                        finish_Activity(OrderDetailsActivity.this);
                    }
                }).build().show();
    }
    //调起微信支付
    private void toWeiXinPay(PayReq req){
        IWXAPI api = WXAPIFactory.createWXAPI(this, AppConstant.WEIXIN_APP_ID);
        api.registerApp(AppConstant.WEIXIN_APP_ID);
        if(!api.isWXAppInstalled())
        {
            NormalAlertDialog dialog = new NormalAlertDialog.Builder(this)
                    .setBoolTitle(false)
                    .setContentText("检测到手机没有安转微信")
                    .setSingleModel(true)
                    .setSingleText("确认")
                    .setHeight(0.23f)
                    .setWidth(0.65f)
                    .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                        @Override
                        public void clickSingleButton(NormalAlertDialog dialog, View view) {
                            dialog.dismiss();
                        }
                    }).setTouchOutside(false)
                    .build();
            dialog.show();
            return;
        }
        if(!api.isWXAppSupportAPI())
        {
            new NormalAlertDialog.Builder(this)
                    .setBoolTitle(false)
                    .setContentText("当前版本不支持支付功能")
                    .setSingleModel(true)
                    .setSingleText("确认")
                    .setHeight(0.23f)
                    .setWidth(0.65f)
                    .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                        @Override
                        public void clickSingleButton(NormalAlertDialog dialog, View view) {
                            dialog.dismiss();
                        }
                    }).setTouchOutside(false)
                    .build().show();
            return;
        }
        api.sendReq(req);
    }
    /**
     * 用来获取手机拨号上网（包括CTWAP和CTNET）时由PDSN分配给手机终端的源IP地址。
     *
     * @return
     * @author SHANHY
     */
    public static String getPsdnIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        //if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet6Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }
    //回调事件，成功调起微信支付后响应该事件
    private void event(){
        subscription= RxBus.$().toObservable(WeChatPayEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeChatPayEvent>() {
                    @Override
                    public void call(WeChatPayEvent event) {
                        Map<String,String> body=new HashMap<String, String>();
                        body.put("out_trade_no",orderNo);
                        mPresenter.loadWeChatPayState(body);
                    }
                });
    }
    @OnClick({R.id.cancel_the_order_tv,R.id.contact_the_merchant_tv,R.id.confirm_goods_tv,
            R.id.immediate_evaluation_tv,R.id.againbuy_tv,R.id.immediate_payment_tv,R.id.additional_comments_tv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_the_order_tv:
                Map<String, String> bodyMap = new HashMap<>();
                bodyMap.put("uid", String.valueOf(AppConstant.UID));
                bodyMap.put("orderno",order.getOrderno());
                mPresenter.cancleOrder(bodyMap);
                break;
            case R.id.contact_the_merchant_tv:
                new NormalAlertDialog.Builder(this)
                        .setBoolTitle(false)
                        .setContentText(order.getMobile_company())
                        .setContentTextColor(R.color.blue)
                        .setLeftText("取消")
                        .setLeftTextColor(R.color.blue)
                        .setRightText("确认")
                        .setRightTextColor(R.color.blue)
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                    dialog.dismiss();
                            }
                            @Override
                            public void clickRightButton(NormalAlertDialog dialog, View view) {
                                Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + order.getMobile_company()));
                                startActivity(phoneIneten);
                                dialog.dismiss();

                            }
                        }).build().show();
                break;
            case R.id.confirm_goods_tv:
                Map<String,String> body=new HashMap<String, String>();
                body.put("orderno",order.getOrderno());
                body.put("uid",String.valueOf(AppConstant.UID));
                body.put("type","order");
                mPresenter.sureGoods(body);
                break;
            case R.id.immediate_evaluation_tv:
                Bundle commentbundle = new Bundle();
                commentbundle.putInt("TYPE",1);
                commentbundle.putInt("ORDERID",order.getId());
                commentbundle.putInt("MZWUIID",order.getMzw_uid());
                startActivity(AddCommentActivity.class,commentbundle);
                break;
            case R.id.againbuy_tv:
                Bundle bundle=new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id",dataList.get(0).getGid());
                startActivity(YarnActivity.class,bundle);
                break;
            case R.id.immediate_payment_tv:
                final List<String> mDatas1=new ArrayList<>();
                mDatas1.add("微信支付(小额支付建议选此项)");
                mDatas1.add("支付宝支付(小额支付建议选此项)");
                mDatas1.add("货到付款(与商家联系付款及发货方式)");
                NormalSelectionDialog dialog1=new NormalSelectionDialog.Builder(this)
                        .setBoolTitle(true)
                        .setTitleText("温馨提示\n请选择您所需要的支付方式")
                        .setTitleHeight(55)
                        .setItemHeight(45)
                        .setItemTextColor(R.color.blue)
                        .setItemTextSize(14)
                        .setItemWidth(0.95f)
                        .setCancleButtonText("取消")
                        .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                            @Override
                            public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                switch (position){
                                    case 0:
                                        Map<String,String> body=new HashMap<String, String>();
                                        body.put("body",order.getGoods().get(0).getTitle());
                                        body.put("out_trade_no",orderNo);
                                        body.put("total_fee",String.valueOf((int)order.getTotalprice()));
                                        body.put("spbill_create_ip",getPsdnIp());
                                        body.put("attach","order");
                                        mPresenter.weChatPay(body);
                                        break;
                                    case 1:

                                        break;
                                    case 2:

                                        break;
                                }
                                dialog.dismiss();
                            }
                        }).setTouchOutside(true)
                        .build();
                dialog1.setData(mDatas1);
                dialog1.show();
                break;
            case R.id.additional_comments_tv:
                if(order.getCommentstatus()==2){
                    ToastUtil.showShortToast("已追加评论");
                    return;
                }
                Bundle addCommentbundle = new Bundle();
                addCommentbundle.putInt("TYPE",2);
                addCommentbundle.putInt("ORDERID",order.getId());
                addCommentbundle.putInt("MZWUIID",order.getMzw_uid());
                startActivity(AddCommentActivity.class,addCommentbundle);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null&&!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


    /**根据交易状态，切换布局显示
     * @param paystate
     * 0 未付款
    1 待发货
    2 待收货
    3 已收货
     */
    private void switchState(int paystate,int payment){
        switch (paystate){
            case 0:
                mIvPayState.setImageResource(R.drawable.icon_weifukuan);
                mTvCancelTheOrder.setVisibility(View.VISIBLE);
                mTvImmediatePayment.setVisibility(View.VISIBLE);
                mTvConfirmGoods.setVisibility(View.GONE);
                mTvImmediateEvaluation.setVisibility(View.GONE);
                mTvAddItionalComment.setVisibility(View.GONE);
                mTvAgeinBuy.setVisibility(View.GONE);
                if(payment==3){
                    mTvImmediatePayment.setVisibility(View.GONE);
                }else{
                    mTvImmediatePayment.setVisibility(View.VISIBLE);
                }
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
