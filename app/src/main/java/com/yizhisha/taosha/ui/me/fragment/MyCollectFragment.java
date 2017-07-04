package com.yizhisha.taosha.ui.me.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseRVFragment;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;
import com.yizhisha.taosha.ui.home.yran.YarnActivity;
import com.yizhisha.taosha.ui.me.contract.MyCollectConstract;
import com.yizhisha.taosha.ui.me.presenter.MyCollectPresenter;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/6/26.
 */

public class MyCollectFragment extends BaseRVFragment<MyCollectPresenter,CollectListBean.Favorite> implements
 MyCollectConstract.View{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    private int mType=0;
    private List<CollectListBean.Favorite> dataList=new ArrayList<>();

    public static MyCollectFragment getInstance(int type) {
        MyCollectFragment sf = new MyCollectFragment();
        sf.mType = type;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycollect;
    }
    @Override
    protected void initView() {
        initAdapter(new MyCollectAdapter(dataList),true,false);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(YarnActivity.class);
            }
        });
        dataList.clear();
        if(mAdapter.getData().size()<=0){
            load(mType,true);
        }


    }
    private void load(int type,boolean isShowLoad){
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("uid",String.valueOf(240));
        if(type!=0){
        bodyMap.put("pid",String.valueOf(type));
        }
        mPresenter.loadCollect(bodyMap,isShowLoad);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        load(mType,false);
    }
    @Override
    public void loadCollectSuccess(List<CollectListBean.Favorite> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        dataList.clear();
        mAdapter.setNewData(data);
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
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(mType,true);
            }
        });
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
    }
}
