package com.yizhisha.taosha.ui.me.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.AccountCenterAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.AccountBean;
import com.yizhisha.taosha.ui.me.contract.AccountCenterContract;
import com.yizhisha.taosha.ui.me.presenter.AccountCenterPresenter;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountCenterActivity extends BaseActivity<AccountCenterPresenter>
        implements SwipeRefreshLayout.OnRefreshListener, AccountCenterContract.View {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.username_accountcenter_tv)
    TextView usernameTv;
    @Bind(R.id.balance_accountcenter_tv)
    TextView balanceTv;
    @Bind(R.id.integral_accountcenter_tv)
    TextView integralTv;
    @Bind(R.id.head_accoutcenter_iv)
    ImageView headIv;

    private AccountCenterAdapter mAdapter;
    private List<AccountBean.AccountList> dataList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_center;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ActivityManager.getActivityMar().finishActivity(AccountCenterActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        if(AppConstant.infoBean!=null){
            String url="http://www.taoshamall.com/data/attached/avatar/100x100/";
            GlideUtil.getInstance().LoadContextCircleBitmap(this,url+AppConstant.infoBean.getAvatar(),headIv);
            usernameTv.setText(AppConstant.infoBean.getUsername());
        }
        initAdapter();
        load(true);
    }

    private void load(boolean isShow) {
        mPresenter.loadAccount(240, isShow);
    }

    private void initAdapter() {
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = new AccountCenterAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));

    }

    @Override
    public void onRefresh() {
        load(false);
    }
    @Override
    public void loadAccountSuccess(AccountBean data) {
        mSwipeRefreshLayout.setRefreshing(false);
        balanceTv.setText(data.getMoney()+"元");
        integralTv.setText(data.getMoney()+"分");
        dataList.clear();
        dataList.addAll(data.getDetail());
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
                load(true);
            }
        });
    }
}
