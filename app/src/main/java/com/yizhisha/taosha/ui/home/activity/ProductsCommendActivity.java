package com.yizhisha.taosha.ui.home.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.HotCommendAdapter;
import com.yizhisha.taosha.adapter.ProductsCommendAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.IndexADSBean;
import com.yizhisha.taosha.ui.home.contract.ProductsCommendContract;
import com.yizhisha.taosha.ui.home.precenter.ProductsCommendPresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ProductsCommendActivity extends BaseActivity<ProductsCommendPresenter>
implements ProductsCommendContract.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.toolbar_rightbutton)
    TextView rightIv;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ProductsCommendAdapter mAdapter;
    private List<IndexADSBean> dataLists=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_products_commend;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(ProductsCommendActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        initAdapter();
        mPresenter.loadProductsCommend(true);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter=new ProductsCommendAdapter(dataLists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putInt("TYPE",2);
                bundle.putString("URL",dataLists.get(position).getUrl());
                startActivity(YarnActivity.class,bundle);
            }
        });
    }
    @Override
    public void onRefresh() {
        mPresenter.loadProductsCommend(false);
    }

    @Override
    public void loadSuccess(List<IndexADSBean> bean) {
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataLists.addAll(bean);
        mAdapter.setNewData(dataLists);
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
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataLists);
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(String msg) {
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataLists);
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                mPresenter.loadProductsCommend(true);
            }
        });
    }
}
