package com.yizhisha.taosha.ui.me.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.DataHelper;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.ui.me.activity.OrderDetailsActivity;
import com.yizhisha.taosha.ui.me.contract.MyOrderContract;
import com.yizhisha.taosha.ui.me.presenter.MyOrderPresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/7/5.
 */

public class MyOrderFragment extends BaseFragment<MyOrderPresenter> implements
        MyOrderContract.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MyOrderAdapter mAdapter;
    private int mType=0;
    private ArrayList<Object> dataList=new ArrayList<>();

    public static MyOrderFragment getInstance(int type) {
        MyOrderFragment sf = new MyOrderFragment();
        sf.mType = type;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_freesample;
    }

    @Override
    protected void initView() {

        initAdapter();
        if(mAdapter.getData().size()<=0){
            load(mType,true);
        }
    }

    private void load(int type,boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("status", String.valueOf(type));
        mPresenter.loadOrder(map,isShowLoad);
    }
    public void search(String key){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("status", String.valueOf(mType));
        map.put("key",key);
        mPresenter.loadOrder(map,false);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new MyOrderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemTypeClickListener(new MyOrderAdapter.OnItemTypeClickListener() {
            @Override
            public void onItemClick(View view, int type, int position) {

                if(type==2){
                    String  orderno;
                    if(dataList.get(position) instanceof Goods) {
                        Goods goods= (Goods) dataList.get(position);
                        orderno=goods.getOrderno();
                        Bundle bundle=new Bundle();
                        bundle.putString("ORDERNO",orderno);
                        startActivityForResult(OrderDetailsActivity.class,bundle,100);
                    }
                }
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.cancel_the_order_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            Map<String, String> bodyMap = new HashMap<>();
                            bodyMap.put("uid", String.valueOf(AppConstant.UID));
                            bodyMap.put("orderno", String.valueOf(orderFootBean.getOrderno()));
                            mPresenter.cancleOrder(bodyMap);
                        }
                        break;
                    case R.id.immediate_evaluation_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            Bundle bundle=new Bundle();
                            bundle.putString("ORDERNO",orderFootBean.getOrderno());
                            startActivityForResult(OrderDetailsActivity.class,bundle,100);
                        }

                        break;
                    case R.id.immediate_payment_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            if(orderFootBean.getCommentstatus()==1){
                                ToastUtil.showShortToast("已评论,可追加评论");
                                return;
                            }
                            Bundle bundle=new Bundle();
                            bundle.putString("ORDERNO",orderFootBean.getOrderno());
                            startActivityForResult(OrderDetailsActivity.class,bundle,100);
                        }
                        break;
                    case R.id.additional_comments_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            if(orderFootBean.getCommentstatus()==2){
                                ToastUtil.showShortToast("已追加评论,不可重复追加");
                                return;
                            }
                            Bundle bundle=new Bundle();
                            bundle.putString("ORDERNO",orderFootBean.getOrderno());
                            startActivityForResult(OrderDetailsActivity.class,bundle,100);
                        }
                        break;
                    case R.id.contact_the_merchant_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                            final OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            new NormalAlertDialog.Builder(activity)
                                    .setBoolTitle(false)
                                    .setContentText(orderFootBean.getMobile_company())
                                    .setLeftText("取消")
                                    .setRightText("确认")
                                    .setWidth(0.75f)
                                    .setHeight(0.33f)
                                    .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                                        @Override
                                        public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void clickRightButton(NormalAlertDialog dialog, View view) {
                                            Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + orderFootBean.getMobile_company()));
                                            startActivity(phoneIneten);
                                            dialog.dismiss();

                                        }
                                    }).build().show();
                        }
                        break;
                    case R.id.confirm_goods_tv:
                        if(dataList.get(position) instanceof OrderFootBean) {
                             OrderFootBean orderFootBean= (OrderFootBean) dataList.get(position);
                            Map<String,String> body=new HashMap<String, String>();
                            body.put("orderno",orderFootBean.getOrderno());
                            body.put("uid",String.valueOf(AppConstant.UID));
                            body.put("type","order");
                            mPresenter.sureGoods(body);
                        }
                        break;
                }
            }
        });
    }
    @Override
    public void onRefresh() {
        load(mType,false);
    }
    @Override
    public void loadOrderSuccess(List<Order> data) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataList= DataHelper.getDataAfterHandle(data);
        mAdapter.setNewData(dataList);

    }

    @Override
    public void sureGoodsSuuccess(String msg) {
        onRefresh();
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void cancleOrder(String msg) {
        onRefresh();
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void showLoading() {
        mLoadingView.load();
    }

    @Override
    public void hideLoading() {
        mLoadingView.loadSuccess();
    }
    @Override
    public void showEmpty() {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(String msg) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(mType,true);
            }
        });
    }

    @Override
    public void cancelFail(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==2){
            onRefresh();
        }
    }
}
