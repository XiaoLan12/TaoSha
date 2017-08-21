package com.yizhisha.taosha.ui.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.adapter.OrderSureAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.OrderSureGoodBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.ShopCartOrderSureBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.LoadingDialog;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.event.WeChatEvent;
import com.yizhisha.taosha.event.WeChatPayEvent;
import com.yizhisha.taosha.ui.home.contract.SureOrderContract;
import com.yizhisha.taosha.ui.home.precenter.SureOrderPresenter;
import com.yizhisha.taosha.ui.me.activity.MyAddressActivity;
import com.yizhisha.taosha.ui.me.activity.SetInfoActivity;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.utils.ToastUtil;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Bind(R.id.distribution_way_tv)
    TextView distributionWayTv;
    @Bind(R.id.pay_way_tv)
    TextView payWayTv;
    @Bind(R.id.cost_tv)
    TextView costTv;
    @Bind(R.id.ishave_freight_tv)
    TextView ishaveFreightTv;
    @Bind(R.id.remark_et)
    EditText remarkEt;

    //订单号
    private String orderNo;

    private OrderSureAdapter mAdapter;
    private List<OrderSureGoodBean> dataList=new ArrayList<>();
    private OrderSureGoodBean orderSureBean=null;

    private LoadingDialog mLoadingDialog;
    private Subscription subscription;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sure_order;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        initAdapter();
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
          if(bundle.getInt("ORDERTYPE")==1){
              loadOrder(bundle);
          }else if(bundle.getInt("ORDERTYPE")==2){
                loadShopCartOrder(bundle);
          }
        }
    event();
    }
    private void loadOrder(Bundle bundle){
        Map<String,String> map=new HashMap();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("gid",String.valueOf(bundle.getInt("gid",0)));
        map.put("type",bundle.getString("type"));
        map.put("detail",bundle.getString("detail"));
        map.put("amount",String.valueOf(bundle.getInt("amount")));
        mPresenter.loadOrderSure(map);
    }
    private void loadShopCartOrder(Bundle bundle){
        Map<String,String> map=new HashMap();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("gid",bundle.getString("gid"));
        mPresenter.loadShopCartOrderSure(map);
    }
    private void initAdapter(){
        mAdapter=new OrderSureAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //startActivity(YarnActivity.class);
            }
        });
    }
    //生成订单
    private void createOrder(String type,int payment,int expressType){
        StringBuilder str=new StringBuilder();
        for(int i=0;i<dataList.size();i++){
            str.append(dataList.get(i).getDetail()).append("#");
            str.append(dataList.get(i).getAmount()).append("，");
        }

        Map<String,String> body=new HashMap<String, String>();
        body.put("uid",String.valueOf(AppConstant.UID));
        //暂时写死
        //body.put("gid",orderSureBean.getGid());
        body.put("gid",String.valueOf(981));
        body.put("type",type);
        body.put("goodsprice",String.valueOf(Double.valueOf(orderSureBean.getTotalprice())));
        body.put("totalamount",String.valueOf(orderSureBean.getTotalamount()));
        body.put("addressid",String.valueOf(orderSureBean.getAddressId()));
        body.put("payment",String.valueOf(payment));
        body.put("detail",str.substring(0,str.length()-1));
        body.put("remark",remarkEt.getText().toString());
        body.put("express_type",String.valueOf(expressType));
        mPresenter.regularOrder(body);
    }
    //加载普通订单
    @Override
    public void loadOrderSuccess(OrderSureBean data) {
        dataList.clear();
        int addressId=0;
        if(data.getAddress()!=null&&data.getAddress().size()>0){
            OrderSureBean.Address address=data.getAddress().get(0);
            consigneeNameTv.setText(address.getLinkman());
            consigneePhoneTv.setText(address.getMobile());
            shippingaddressTv.setText(address.getArea_app());
            addressId=address.getId();
        }
        costTv.setText("合计:￥"+data.getTotalprice());
        OrderSureGoodBean goodBean=new OrderSureGoodBean();
        goodBean.setGid(data.getGid());
        goodBean.setTitle(data.getTitle());
        goodBean.setLitpic(data.getLitpic());
        goodBean.setAmount(data.getAmount());
        goodBean.setDetail(data.getDetail());
        goodBean.setPrice(data.getPrice());
        goodBean.setAddressId(addressId);
        goodBean.setTotalprice(data.getTotalprice());
        goodBean.setTotalamount(data.getAmount());
        orderSureBean=goodBean;
        dataList.add(goodBean);
        mAdapter.setNewData(dataList);
    }
    //加载购物车订单
    @Override
    public void loadShopCartOrderSuccess(ShopCartOrderSureBean data) {
        dataList.clear();
        int addressId=0;
        if(data.getAddress()!=null&&data.getAddress().size()>0){
            ShopCartOrderSureBean.Address address=data.getAddress().get(0);
            consigneeNameTv.setText(address.getLinkman());
            consigneePhoneTv.setText(address.getMobile());
            shippingaddressTv.setText(address.getArea_app());
            addressId=address.getId();
        }
        if(data.getShopcart()!=null&&data.getShopcart().size()>0){
            for(ShopCartOrderSureBean.Shopcart shopcart:data.getShopcart()){
                OrderSureGoodBean goodBean=new OrderSureGoodBean();
                goodBean.setGid(shopcart.getGid());
                goodBean.setTitle(shopcart.getTitle());
                goodBean.setLitpic(shopcart.getLitpic());
                goodBean.setAmount(shopcart.getAmount());
                goodBean.setDetail(shopcart.getDetail());
                goodBean.setPrice(shopcart.getPrice());
                goodBean.setAddressId(addressId);
                goodBean.setTotalprice(data.getPrice());
                goodBean.setTotalamount(data.getAmount());
                dataList.add(goodBean);
            }
        }
        orderSureBean=dataList.get(0);
        costTv.setText("合计:￥"+data.getPrice());
        mAdapter.setNewData(dataList);
    }
    //获得微信支付签名
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
    public void regularOrderSuccess(String msg) {
        orderNo=msg;
        Map<String,String> body=new HashMap<String, String>();
        body.put("body",orderSureBean.getTitle());
        body.put("out_trade_no",msg);
        body.put("total_fee",String.valueOf((int)orderSureBean.getTotalprice()));
        //body.put("total_fee",String.valueOf(Double.valueOf(1)));
        body.put("spbill_create_ip",getPsdnIp());
        body.put("attach","order");
        mPresenter.weChatPay(body);
    }

    @Override
    public void loadWeChatPayState(WeChatPayStateBean bean) {
        new NormalAlertDialog.Builder(this)
                .setBoolTitle(false)
                .setContentText(bean.getReturn_msg())
                .setContentTextColor(R.color.blue)
                .setSingleModel(true)
                .setSingleText("确认")
                .setWidth(0.75f)
                .setHeight(0.33f)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                        finish_Activity(SureOrderActivity.this);
                    }
                }).build().show();
    }

    @Override
    public void showLoading() {
        mLoadingDialog=new LoadingDialog(this,"请稍后...",false);
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.cancelDialog();
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
    @Override
    public void loadFail(String msg) {
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
                        finish_Activity(SureOrderActivity.this);
                    }
                }).build().show();
    }

    @OnClick({R.id.shippingaddress_ll,R.id.distribution_way_rl,R.id.pay_way_rl,
    R.id.call_us_tv,R.id.sureorder_tv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shippingaddress_ll:
                startActivityForResult(MyAddressActivity.class,2);
                break;
            case R.id.distribution_way_rl:
                final List<String> mDatas=new ArrayList<>();
                mDatas.add("快递发货(到付)");
                mDatas.add("物流发货(到付)");
                NormalSelectionDialog dialog=new NormalSelectionDialog.Builder(this)
                        .setBoolTitle(true)
                        .setTitleText("温馨提示\n订单完成后请与供应商联系具体发什么快递/物流")
                        .setTitleHeight(55)
                        .setItemHeight(45)
                        .setItemTextColor(R.color.blue)
                        .setItemTextSize(14)
                        .setItemWidth(0.95f)
                        .setCancleButtonText("取消")
                        .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                            @Override
                            public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                distributionWayTv.setText(mDatas.get(position));
                                switch (position){
                                    case 0:
                                        distributionWayTv.setText("快递发货");
                                        break;
                                    case 1:
                                        distributionWayTv.setText("物流发货");
                                        break;
                                }
                                dialog.dismiss();
                            }
                        }).setTouchOutside(true)
                        .build();
                dialog.setData(mDatas);
                dialog.show();
                break;
            case R.id.pay_way_rl:
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
                                        payWayTv.setText("微信支付");
                                        break;
                                    case 1:
                                        payWayTv.setText("支付宝支付");
                                        break;
                                    case 2:
                                        payWayTv.setText("货到付款");
                                        break;
                                }
                                dialog.dismiss();
                            }
                        }).setTouchOutside(true)
                        .build();
                dialog1.setData(mDatas1);
                dialog1.show();
                break;
            case R.id.call_us_tv:
                new NormalAlertDialog.Builder(this)
                        .setBoolTitle(false)
                        .setContentText("2142142354")
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
                                Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + "1241234"));
                                startActivity(phoneIneten);
                                dialog.dismiss();

                            }
                        }).build().show();
                break;
            case R.id.sureorder_tv:
                String payType=payWayTv.getText().toString();
                if(payType.equals("微信支付")){
                    createOrder("order",5,1);
                }else if(payType.equals("支付宝支付")){

                }else if(payType.equals("货到付款")){

                }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2&&resultCode==2){
            AddressListBean.Address address= (AddressListBean.Address) data.getSerializableExtra("ADDRESS");
            consigneeNameTv.setText(address.getLinkman());
            consigneePhoneTv.setText(address.getMobile());
            shippingaddressTv.setText(address.getAddress());
        }
    }
}
