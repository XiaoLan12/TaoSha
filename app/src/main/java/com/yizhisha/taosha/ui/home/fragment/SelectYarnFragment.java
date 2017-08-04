package com.yizhisha.taosha.ui.home.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.adapter.SelectYarnAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.SearchDetailBean;
import com.yizhisha.taosha.ui.home.activity.YarnActivity;
import com.yizhisha.taosha.ui.home.contract.SelectYarnConstract;
import com.yizhisha.taosha.ui.home.precenter.SelectYarnPresenter;
import com.yizhisha.taosha.ui.me.fragment.MyCollectFragment;
import com.yizhisha.taosha.utils.LogUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/7/13.
 */

public class SelectYarnFragment extends BaseFragment<SelectYarnPresenter> implements SelectYarnConstract.View
        , SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private SelectYarnAdapter mAdapter;
    private List<SearchDetailBean> dataList=new ArrayList<>();

    private int mYarnType=1;
    private String mNeedleType="3g";
    private int mPriceType=1;
    private int mOrderByType=1;

    public static SelectYarnFragment getInstance(int yarnType) {
        SelectYarnFragment sf = new SelectYarnFragment();
        sf.mYarnType=yarnType;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycollect;
    }
    @Override
    protected void initView() {
        initAdapter();
        dataList.clear();
        if(mAdapter.getData().size()<=0){
            load(true);
        }
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter=new SelectYarnAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",dataList.get(position).getId());

                startActivity(YarnActivity.class,bundle);
            }
        });
    }
    private void load(boolean isShowLoad){
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("pid",String.valueOf(mYarnType));
        bodyMap.put("price",String.valueOf(mPriceType));
        bodyMap.put("needle",mNeedleType);
        bodyMap.put("orderby",String.valueOf(mOrderByType));
        mPresenter.loadSearch(bodyMap,isShowLoad);
    }

    public void loadSearch(int yarn,int price,String needle,int orderby){
        this.mYarnType=yarn;
        this.mPriceType=price;
        this.mNeedleType=needle;
        this.mOrderByType=orderby;
        load(false);
    }
    @Override
    public void loadSuccess(List<SearchDetailBean> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        dataList.clear();
        dataList.addAll(data);
        mAdapter.setNewData(data);
    }

    @Override
    public void showLoading() {
        if(mLoadingView!=null) {
            mLoadingView.load();
        }
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
                    }
                });

    }

    @Override
    public void onRefresh() {
        load(false);
    }
}
