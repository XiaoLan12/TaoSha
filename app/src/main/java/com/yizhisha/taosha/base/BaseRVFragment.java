package com.yizhisha.taosha.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.utils.NetWorkUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CustomLoadMoreView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lan on 2017/6/23.
 * RecyclerView的BaseFragment
 */
public abstract class BaseRVFragment<P extends BasePresenter,T> extends BaseFragment<P> implements
        SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected BaseQuickAdapter<T,BaseViewHolder> mAdapter;

    protected void initAdapter(boolean refreshable, boolean loadmoreable) {
        mRecyclerView= ButterKnife.findById(activity,R.id.recyclerview);
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setAdapter(mAdapter);
        }
        if (mAdapter != null) {
            if (loadmoreable) {
                mAdapter.setOnLoadMoreListener(this, mRecyclerView);
                mAdapter.setLoadMoreView(new CustomLoadMoreView());
            }
            if (refreshable) {
                mSwipeRefreshLayout=ButterKnife.findById(activity,R.id.swiperefreshlayout);
                //改变加载显示的颜色
                mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                        RescourseUtil.getColor(R.color.red));
                //设置刷新出现的位置
                mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
                mSwipeRefreshLayout.setOnRefreshListener(this);
            }
        }

    }

    /**
     * @param adapter 传入的Adapter
     * @param refreshable 是否开启刷新
     * @param loadmoreable 是否开启加载更多
     */
    protected void initAdapter(BaseQuickAdapter<T,BaseViewHolder> adapter, boolean refreshable, boolean loadmoreable) {
        mAdapter = adapter;
        initAdapter(refreshable, loadmoreable);
    }
    @Override
    public void onLoadMoreRequested() {

    }
    @Override
    public void onRefresh() {

    }
    //请求失败后的操作
    protected void loaddingError(){
        mAdapter.loadMoreComplete();
        mAdapter.loadMoreFail();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
