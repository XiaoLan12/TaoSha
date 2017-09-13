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
import com.yizhisha.taosha.adapter.SeckillOrderSureAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.OrderSureGoodBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.SeckillOrderSureBean;
import com.yizhisha.taosha.bean.json.ShopCartOrderSureBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.LoadingDialog;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.event.UpdateShopCartEvent;
import com.yizhisha.taosha.event.WeChatEvent;
import com.yizhisha.taosha.event.WeChatPayEvent;
import com.yizhisha.taosha.ui.home.contract.SureOrderContract;
import com.yizhisha.taosha.ui.home.precenter.SureOrderPresenter;
import com.yizhisha.taosha.ui.me.activity.FreeSampleActivity;
import com.yizhisha.taosha.ui.me.activity.MyAddressActivity;
import com.yizhisha.taosha.ui.me.activity.MyOrderAcitvity;
import com.yizhisha.taosha.ui.me.activity.SecKillOrderActivity;
import com.yizhisha.taosha.ui.me.activity.SecKillOrderDetailActivity;
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
    @Bind(R.id.distribution_way_rl)
    RelativeLayout distributionWayRl;
    @Bind(R.id.distribution_way_tv)
    TextView distributionWayTv;
    @Bind(R.id.pay_way_rl)
    RelativeLayout payWayRl;
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
    private SeckillOrderSureAdapter mAdapter1;
    private List<OrderSureGoodBean> dataList=new ArrayList<>();
    private OrderSureGoodBean orderSureBean=null;

    private LoadingDialog mLoadingDialog;
    private Subscription subscription;

    private int seckillId;//秒纱的id
    private int nayangGid;//拿样的gid
    private String orderType;//是普通订单还是板毛
    private int mType=0;//当前订单类型,1:普通订单  2:购物车订单 3:秒纱订单 4:拿样订单
    private int mPayType=5;//支付方式  5:微信  2:支付宝 3:到付
    private int mExpressType=2;//配送方式 1:物流发货 2：快递发货  3:朗通快递

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sure_order;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(SureOrderActivity.this);
            }
        });
    }
    @Override
    protected void initView() {

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            mType=bundle.getInt("ORDERTYPE");
          if(mType==1){
              initAdapter();
              loadOrder(bundle);
          }else if(mType==2){
              initAdapter();
              loadShopCartOrder(bundle);
          }else if(mType==3){
              initSeckillAdapter();
              loadSeckillOrder(bundle);
          }else if(mType==4){
              distributionWayRl.setEnabled(false);
              payWayRl.setEnabled(false);
              distributionWayTv.setText("顺丰到付");
              payWayTv.setText("样品免费");
              payWayTv.setTextColor(RescourseUtil.getColor(R.color.red1));
              remarkEt.setHint("可以备注说明您想要的是色卡、布片或者您想要样纱的颜色等");
              loadnayangOrder(bundle);
            }
        }
    event();
    }
    //普通商品和板毛确认订单
    private void loadOrder(Bundle bundle){
        orderType=bundle.getString("type");
        Map<String,String> map=new HashMap();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("gid",String.valueOf(bundle.getInt("gid",0)));

        map.put("type",orderType);
        map.put("detail",bundle.getString("detail"));
        map.put("amount",String.valueOf(bundle.getInt("amount")));
        mPresenter.loadOrderSure(map);
    }
    //购物车确认订单
    private void loadShopCartOrder(Bundle bundle){
        Map<String,String> map=new HashMap();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("gid",bundle.getString("gid"));
        mPresenter.loadShopCartOrderSure(map);
    }
    //秒杀确认订单
    private void loadSeckillOrder(Bundle bundle){
        seckillId=bundle.getInt("id",0);
        Map<String,String> map=new HashMap();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("id",String.valueOf(seckillId));
        mPresenter.loadSeckillOrder(map);
    }
    //拿样确认订单
    private void loadnayangOrder(Bundle bundle){
        nayangGid=bundle.getInt("gid");
        Map<String,String> map=new HashMap();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("gid",String.valueOf(nayangGid));
        mPresenter.loadNayangOrderOrder(map);
    }
    //初始化一般商品适配器
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
    //初始化秒纱适配器
    private void initSeckillAdapter(){
        mAdapter1=new SeckillOrderSureAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter1);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //startActivity(YarnActivity.class);
            }
        });
    }

    //生成普通订单
    private void createOrder(){
        StringBuilder str=new StringBuilder();
        StringBuilder gidStr=new StringBuilder();
        String detail="";
        String gid="";
        for(int i=0;i<dataList.size();i++){
            str.append(dataList.get(i).getDetail()).append(",");
            gidStr.append(dataList.get(i).getGid()).append(",");

        }
        if(str.length()>0){
            detail=str.substring(0,str.length()-1);
        }
        if(gidStr.length()>0){
            gid=gidStr.substring(0,gidStr.length()-1);
        }
        Map<String,String> body=new HashMap<String, String>();
        body.put("uid",String.valueOf(AppConstant.UID));
        body.put("gid",gid);
        body.put("type",orderType);
        body.put("goodsprice",String.valueOf(Double.valueOf(orderSureBean.getTotalprice())));
        body.put("totalamount",String.valueOf(orderSureBean.getTotalamount()));

        body.put("addressid",String.valueOf(orderSureBean.getAddressId()));
        body.put("payment",String.valueOf(mPayType));
        body.put("detail",detail);
        body.put("remark",remarkEt.getText().toString());
        body.put("express_type",String.valueOf(mExpressType));
        mPresenter.regularOrder(body);
    }
    //创建购物车订单
    private void createShopCartOrder(){
        StringBuilder gidStr=new StringBuilder();
        String gid="";
        for(int i=0;i<dataList.size();i++){
            gidStr.append(dataList.get(i).getGid()).append(",");

        }
        if(gidStr.length()>0){
            gid=gidStr.substring(0,gidStr.length()-1);
        }
        Map<String,String> body=new HashMap<String, String>();
        body.put("uid",String.valueOf(AppConstant.UID));
        body.put("gid",gid);
        body.put("addressid",String.valueOf(orderSureBean.getAddressId()));
        body.put("payment",String.valueOf(mPayType));
        body.put("express_type",String.valueOf(mExpressType));
        mPresenter.shopcartOrder(body);
    }
    //创建拿样订单
    private void createNayangOrder(){

        Map<String,String> body=new HashMap<String, String>();
        body.put("uid",String.valueOf(AppConstant.UID));
        body.put("gid",String.valueOf(nayangGid));
        body.put("addressid",String.valueOf(orderSureBean.getAddressId()));
        body.put("express_type",String.valueOf(mExpressType));
        body.put("detail",remarkEt.getText().toString());
        mPresenter.nayangOrder(body);
    }
    //创建秒纱订单
    private void createSeckillOrder(){

        Map<String,String> body=new HashMap<String, String>();
        body.put("uid",String.valueOf(AppConstant.UID));
        body.put("id",String.valueOf(seckillId));
        body.put("payment",String.valueOf(mPayType));
        body.put("addressid",String.valueOf(orderSureBean.getAddressId()));
        mPresenter.seckillOrder(body);
    }
    //加载普通商品确认订单成功的回调
    @Override
    public void loadOrderSuccess(OrderSureBean data) {
        dataList.clear();
        int addressId=0;
        if(data.getAddress()!=null&&data.getAddress().size()>0){
            List<OrderSureBean.Address> addressList=data.getAddress();
            for(OrderSureBean.Address address:addressList){
                if(address.getIndex().equals("1")){
                    consigneeNameTv.setText(address.getLinkman());
                    consigneePhoneTv.setText(address.getMobile());
                    shippingaddressTv.setText(address.getAddress());
                    addressId=address.getId();
                }
            }

        }
        if(orderType.equals("banmao")){
            costTv.setText("合计:￥"+data.getPrice_real());
            OrderSureGoodBean goodBean=new OrderSureGoodBean();
            goodBean.setGid(data.getGid());
            goodBean.setTitle(data.getTitle());
            goodBean.setLitpic(data.getLitpic());
            goodBean.setAmount(data.getAmount());
            goodBean.setDetail(data.getDetail());
            goodBean.setPrice(data.getPrice_real());
            goodBean.setAddressId(addressId);
            goodBean.setTotalprice(data.getTotalprice());
            goodBean.setTotalamount(data.getAmount());
            orderSureBean=goodBean;
            dataList.add(goodBean);
        }else{
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

        }


        mAdapter.setNewData(dataList);
    }
    //加载购物车确认订单成功后的回调
    @Override
    public void loadShopCartOrderSuccess(ShopCartOrderSureBean data) {
        dataList.clear();
        int addressId=0;

        if(data.getAddress()!=null&&data.getAddress().size()>0){
            List< ShopCartOrderSureBean.Address> addressList=data.getAddress();
            for(  ShopCartOrderSureBean.Address address:addressList){
                if(address.getIndex().equals("1")){
                    consigneeNameTv.setText(address.getLinkman());
                    consigneePhoneTv.setText(address.getMobile());
                    shippingaddressTv.setText(address.getAddress());
                    addressId=address.getId();
                }
            }

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
        RxBus.$().postEvent(new UpdateShopCartEvent());
    }
    //加载秒纱确认订单后的回调
    @Override
    public void loadSeckillOrderSuccess(SeckillOrderSureBean data) {
        dataList.clear();
        int addressId=0;
        if(data.getAddress()!=null&&data.getAddress().size()>0){
            List<SeckillOrderSureBean.Address> addressList=data.getAddress();
            for( SeckillOrderSureBean.Address address:addressList){
                if(address.getIndex().equals("1")){
                    consigneeNameTv.setText(address.getLinkman());
                    consigneePhoneTv.setText(address.getMobile());
                    shippingaddressTv.setText(address.getAddress());
                    addressId=address.getId();
                }
            }

        }
       if(data.getGoods()!=null){
           SeckillOrderSureBean.Goods goods=data.getGoods();
           OrderSureGoodBean goodBean=new OrderSureGoodBean();
           goodBean.setGid(goods.getId());
           goodBean.setTitle(goods.getTitle());
           goodBean.setLitpic(goods.getLitpic());
           goodBean.setDetail(goods.getIngredient());
           goodBean.setMarket_price(goods.getMarket_price());
           goodBean.setPrice(goods.getPrice());
           goodBean.setAddressId(addressId);
           goodBean.setTotalprice(goods.getPrice());
           goodBean.setTotalamount(1);
           orderSureBean=goodBean;
           costTv.setText("合计:￥"+goods.getPrice());
           dataList.add(goodBean);
       }
        mAdapter1.setNewData(dataList);
    }
    //加载拿样确认订单后的回调
    @Override
    public void loadNayangOrderSuccess(List<AddressListBean.Address> data) {
           int addressId=0;
            for(AddressListBean.Address address:data){
                if(address.getIndex().equals("1")){
                    consigneeNameTv.setText(address.getLinkman());
                    consigneePhoneTv.setText(address.getMobile());
                    shippingaddressTv.setText(address.getAddress());
                    addressId=address.getId();
                }
            }
        OrderSureGoodBean goodBean=new OrderSureGoodBean();
        goodBean.setAddressId(addressId);
        orderSureBean=goodBean;
        dataList.add(orderSureBean);
        costTv.setText("合计:样品免费");
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
    //普通商品创建订单后的回调
    @Override
    public void regularOrderSuccess(RequestStatusBean bean) {
        switch (mPayType){
            case 2:
                break;
            case 3:
                if(mLoadingDialog!=null){
                    mLoadingDialog.cancelDialog();
                }
                new NormalAlertDialog.Builder(this)
                        .setTitleText("支付结果")
                        .setContentText(bean.getInfo()+"请到\"我的订单\"查看供应商的联系方式,及时与供应商取得联系")
                        .setSingleModel(true)
                        .setSingleText("确认")
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickSingleButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                                if(mType==3){
                                    startActivity(SecKillOrderActivity.class);
                                }else{
                                    startActivity(MyOrderAcitvity.class);
                                }

                            }
                        }).setTouchOutside(false)
                        .setCancelable(false)
                        .build().show();
                break;
            case 5:
                orderNo=bean.getOrderno();
                Map<String,String> body=new HashMap<String, String>();
                body.put("body",orderSureBean.getTitle());
                body.put("out_trade_no",orderNo);
                body.put("total_fee",String.valueOf((int)orderSureBean.getTotalprice()*100));
                body.put("spbill_create_ip",getPsdnIp());
                if(mType==3){
                    body.put("attach","seckilling");
                }else {
                    body.put("attach", "order");
                }
                mPresenter.weChatPay(body);
                break;
        }

    }

    @Override
    public void shoppCartOrderSuccess(RequestStatusBean bean) {
            RxBus.$().postEvent(new UpdateShopCartEvent());
            new NormalAlertDialog.Builder(this)
                    .setBoolTitle(true)
                    .setTitleText("温馨提示")
                    .setContentText("成功生成订单,请到个人中心'我的订单'查看订单并支付订单,谢谢")
                    .setSingleModel(true)
                    .setSingleText("确认")
                    .setWidth(0.75f)
                    .setHeight(0.33f)
                    .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                        @Override
                        public void clickSingleButton(NormalAlertDialog dialog, View view) {
                            dialog.dismiss();
                            startActivity(MyOrderAcitvity.class);
                        }
                    }).setTouchOutside(false)
                    .setCancelable(false)
                    .build().show();
    }

    @Override
    public void nayangOrderSuccess(String msg) {
        new NormalAlertDialog.Builder(this)
                .setTitleText("支付结果")
                .setContentText(msg)
                .setSingleModel(true)
                .setSingleText("确认")
                .setWidth(0.75f)
                .setHeight(0.33f)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();
                        startActivity(FreeSampleActivity.class);
                    }
                }).setTouchOutside(false)
                .setCancelable(false).build().show();
    }

    //获得微信支付的状态
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
                        if(mType==3){
                            startActivity(SecKillOrderActivity.class);
                        }else{
                            startActivity(MyOrderAcitvity.class);
                        }
                    }
                }).setTouchOutside(false)
                .setCancelable(false)
                .build().show();
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
    @Override
    public void loadFail(String msg) {

        new NormalAlertDialog.Builder(this)
                .setBoolTitle(false)
                .setContentText(msg)
                .setSingleModel(true)
                .setSingleText("确认")
                .setHeight(0.23f)
                .setWidth(0.65f)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                        finish_Activity(SureOrderActivity.this);
                        dialog.dismiss();
                    }
                }).setTouchOutside(false)
                .setCancelable(false)
                .build().show();
    }

    @Override
    public void createFail(String msg) {
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
                Bundle bundle=new Bundle();
                bundle.putInt("TYPE",1);
                startActivityForResult(MyAddressActivity.class,bundle,2);
                break;
            case R.id.distribution_way_rl:
                final List<String> mDatas=new ArrayList<>();
                mDatas.add("快递发货(到付)");
                mDatas.add("物流发货(到付)");
                mDatas.add("朗通快递(到付)");
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
                                        mExpressType=2;
                                        break;
                                    case 1:
                                        distributionWayTv.setText("物流发货");
                                        mExpressType=1;
                                        break;
                                    case 2:
                                        distributionWayTv.setText("朗通快递");
                                        mExpressType=3;
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
                //mDatas1.add("支付宝支付(小额支付建议选此项)");
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
                                        mPayType=5;
                                        break;
                                    case 1:
                                        payWayTv.setText("货到付款");
                                        mPayType=3;

                                        break;
                                  /*  case 2:
                                        payWayTv.setText("支付宝支付");
                                        mPayType=2;
                                        break;*/
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
                if(consigneeNameTv.getText().toString()==null||consigneeNameTv.getText().toString().equals("")){
                    new NormalAlertDialog.Builder(this)
                            .setBoolTitle(false)
                            .setContentText("请添加收货地址")
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
                            .setCancelable(false)
                            .build().show();
                    return;
                }
                if(mType==1){
                    createOrder();
                }else if(mType==2){
                    createShopCartOrder();
                }else if(mType==3){
                  createSeckillOrder();
                }else if(mType==4){
                   createNayangOrder();
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
            orderSureBean.setAddressId(address.getId());
            shippingaddressTv.setText(address.getAddress());
        }
    }
}
