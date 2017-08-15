package com.yizhisha.taosha.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.adapter.OrderSureAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.OrderSureGoodBean;
import com.yizhisha.taosha.bean.json.ShopCartOrderSureBean;
import com.yizhisha.taosha.ui.home.contract.SureOrderContract;
import com.yizhisha.taosha.ui.home.precenter.SureOrderPresenter;
import com.yizhisha.taosha.ui.me.activity.MyAddressActivity;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private OrderSureAdapter mAdapter;
    private List<OrderSureGoodBean> dataList=new ArrayList<>();

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
    //加载普通订单
    @Override
    public void loadOrderSuccess(OrderSureBean data) {
        dataList.clear();
        if(data.getAddress()!=null&&data.getAddress().size()>0){
            OrderSureBean.Address address=data.getAddress().get(0);
            consigneeNameTv.setText(address.getLinkman());
            consigneePhoneTv.setText(address.getMobile());
            shippingaddressTv.setText(address.getArea_app());
        }
        costTv.setText("合计:Y"+data.getTotalprice());
        OrderSureGoodBean goodBean=new OrderSureGoodBean();
        goodBean.setGid(data.getGid());
        goodBean.setTitle(data.getTitle());
        goodBean.setLitpic(data.getLitpic());
        goodBean.setAmount(data.getAmount());
        goodBean.setDetail(data.getDetail());
        goodBean.setPrice(data.getPrice());
        dataList.add(goodBean);
        mAdapter.setNewData(dataList);
    }
    //加载购物车订单
    @Override
    public void loadShopCartOrderSuccess(ShopCartOrderSureBean data) {
        dataList.clear();
        if(data.getShopcart()!=null&&data.getShopcart().size()>0){
            for(ShopCartOrderSureBean.Shopcart shopcart:data.getShopcart()){
                OrderSureGoodBean goodBean=new OrderSureGoodBean();
                goodBean.setGid(shopcart.getGid());
                goodBean.setTitle(shopcart.getTitle());
                goodBean.setLitpic(shopcart.getLitpic());
                goodBean.setAmount(shopcart.getAmount());
                goodBean.setDetail(shopcart.getDetail());
                goodBean.setPrice(shopcart.getPrice());
                dataList.add(goodBean);
            }
        }
        costTv.setText("合计:￥"+data.getPrice());
        if(data.getAddress()!=null&&data.getAddress().size()>0){
            ShopCartOrderSureBean.Address address=data.getAddress().get(0);
            consigneeNameTv.setText(address.getLinkman());
            consigneePhoneTv.setText(address.getMobile());
            shippingaddressTv.setText(address.getArea_app());
        }
        mAdapter.setNewData(dataList);
    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }
    @OnClick({R.id.shippingaddress_ll})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shippingaddress_ll:
                startActivityForResult(MyAddressActivity.class,2);
                break;
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
