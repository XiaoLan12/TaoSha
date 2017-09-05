package com.yizhisha.taosha.ui.me.activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.HaveRatingAdapter;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.DataHelper;
import com.yizhisha.taosha.bean.json.MyCommentListBean;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.ui.home.activity.YarnActivity;
import com.yizhisha.taosha.ui.me.contract.MyRatingContract;
import com.yizhisha.taosha.ui.me.presenter.MyRatingPresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MyRatingActivity extends BaseActivity<MyRatingPresenter> implements
        MyRatingContract.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HaveRatingAdapter mAdapter;
    private ArrayList<Object> dataList=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_rating;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(MyRatingActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        initAdapter();
        load(true);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter=new HaveRatingAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(dataList.get(position) instanceof MyCommentListBean.Goods) {
                    MyCommentListBean.Goods goods= (MyCommentListBean.Goods) dataList.get(position);
                if(adapter.getItemViewType(position)==2){
                    Bundle bundle = new Bundle();
                    bundle.putInt("TYPE",1);
                    bundle.putInt("id", goods.getGid());
                    startActivity(YarnActivity.class, bundle);
                }
                }
            }
        });

    }
    private void load(boolean isShowLoad){
        mPresenter.loadComment(AppConstant.UID,isShowLoad);
    }
    @Override
    public void onRefresh() {
        load(false);
    }

    @Override
    public void loadCommentSuccess(List<MyCommentListBean> data) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataList= DataHelper.getCommentDataAfterHandle(data);
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
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
               // load(mType,true);
            }
        });
    }
}
