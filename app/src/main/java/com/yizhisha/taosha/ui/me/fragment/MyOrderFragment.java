package com.yizhisha.taosha.ui.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.OrderBean;
import com.yizhisha.taosha.ui.home.yran.YarnActivity;
import com.yizhisha.taosha.ui.me.contract.MyOrderContract;
import com.yizhisha.taosha.ui.me.presenter.MyOrderPresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    private ArrayList<Order> dataList=new ArrayList<>();

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
        //if(mAdapter.getData().size()<=0){
            load(mType,true);
        //}
    }

    private void load(int type,boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(882));
        map.put("status",String.valueOf(0));
        mPresenter.loadOrder(map,isShowLoad);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new MyOrderAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(YarnActivity.class);
            }
        });
    }
    @Override
    public void onRefresh() {

    }
    @Override
    public void loadOrderSuccess(List<Order> data) {
        dataList.clear();
       /* for(int i=0;i<data.size();i++){
            OrderBean orderBean=new OrderBean();
            Order order=data.get(i);
            orderBean.setOrder(order);
            orderBean.setItemType(OrderBean.TYPE_1);
            for(int j=0;j<order.getGoods().size();j++){
                List<Goods> goodses=order.getGoods();

            }
            dataList.add(order);
            order.setItemType(Order.TYPE_3);
            dataList.add(order);
        }*/
        dataList.addAll(data);
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
