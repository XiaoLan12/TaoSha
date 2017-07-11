package com.yizhisha.taosha.ui.me.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.OrderDataHelper;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.OrderHeadBean;
import com.yizhisha.taosha.ui.me.activity.OrderDetailsActivity;
import com.yizhisha.taosha.ui.me.contract.MyOrderContract;
import com.yizhisha.taosha.ui.me.presenter.MyOrderPresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
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
        if(type!=-1) {
            map.put("status", String.valueOf(type));
        }
        mPresenter.loadOrder(map,isShowLoad);
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
        /*mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if(dataList.get(position) instanceof OrderHeadBean) {
                    OrderHeadBean orderHeadBean= (OrderHeadBean) dataList.get(position);
                    orderno=orderHeadBean.getOrderno();
                    Bundle bundle=new Bundle();
                    bundle.putString("ORDERNO",orderno);
                    startActivity(OrderDetailsActivity.class,bundle);
                }

            }
        });*/
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
                        startActivity(OrderDetailsActivity.class,bundle);


                    }
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
        dataList=OrderDataHelper.getDataAfterHandle(data);
        mAdapter.setNewData(dataList);

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
    }
}
