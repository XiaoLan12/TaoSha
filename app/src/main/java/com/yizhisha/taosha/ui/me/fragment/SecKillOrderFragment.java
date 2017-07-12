package com.yizhisha.taosha.ui.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.SecondKillOrderAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.OrderDataHelper;
import com.yizhisha.taosha.bean.json.SeckillBean;
import com.yizhisha.taosha.ui.me.contract.SecKillOrderContract;
import com.yizhisha.taosha.ui.me.presenter.SecKillOrderPresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/7/11.
 */

public class SecKillOrderFragment extends BaseFragment<SecKillOrderPresenter>
        implements SwipeRefreshLayout.OnRefreshListener,SecKillOrderContract.View{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private SecondKillOrderAdapter mAdapter;
    private int mType=0;
    private ArrayList<SeckillBean> dataList=new ArrayList<>();
    public static SecKillOrderFragment getInstance(int type) {
        SecKillOrderFragment sf = new SecKillOrderFragment();
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
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new SecondKillOrderAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);

    }
    private void load(int type,boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        if(type!=-1) {
            map.put("status", String.valueOf(type));
        }
        mPresenter.loadSeckillOrder(map,isShowLoad);
    }
    @Override
    public void loadSuccess(List<SeckillBean> data) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataList.addAll(data);
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

    @Override
    public void onRefresh() {
        load(mType,false);
    }
}