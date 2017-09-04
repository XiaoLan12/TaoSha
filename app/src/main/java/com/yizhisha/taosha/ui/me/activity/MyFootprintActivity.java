package com.yizhisha.taosha.ui.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.HaveRatingAdapter;
import com.yizhisha.taosha.adapter.MyFootprintAdapter;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.DataHelper;
import com.yizhisha.taosha.bean.json.FootprintListBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.ui.home.activity.YarnActivity;
import com.yizhisha.taosha.ui.me.contract.MyFootprintContract;
import com.yizhisha.taosha.ui.me.fragment.MyOrderFragment;
import com.yizhisha.taosha.ui.me.presenter.MyFootprintPresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.ClearEditText;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class MyFootprintActivity extends BaseActivity<MyFootprintPresenter>
        implements MyFootprintContract.View, SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.search_ll)
    LinearLayout searchLl;
    @Bind(R.id.search_et)
    ClearEditText searchEt;
    @Bind(R.id.search_tv)
    TextView searchTv;
    @Bind(R.id.cacel_tv)
    TextView cacelTv;
    private MyFootprintAdapter mAdapter;
    private List<FootprintListBean> dataLists=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.acticity_my_footprint;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(MyFootprintActivity.this);
            }
        });
        toolbar.setRightButtonText("清除");
        toolbar.showRightButton();
        toolbar.setRightButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataLists.size()==0){
                   return;
                }
                new NormalAlertDialog.Builder(MyFootprintActivity.this)
                        .setBoolTitle(false)
                        .setContentText("删除全部数据?")
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
                                Map<String,String> map=new HashMap<String, String>();
                                map.put("uid",String.valueOf(AppConstant.UID));
                                map.put("type",dataLists.get(0).getType());
                                mPresenter.clearFootPrint(map);
                                dialog.dismiss();

                            }
                        }).build().show();

            }
        });
    }
    @Override
    protected void initView() {
        initAdapter();
        load(true);
    }
    private void load(boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        mPresenter.loadFootPrintr(map,isShowLoad);
    }
    private void load(String key,boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("key",key);
        mPresenter.loadFootPrintr(map,isShowLoad);
    }

    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter=new MyFootprintAdapter(dataLists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(this,RecyclerViewDriverLine.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()){
                    case R.id.delete_tv:
                        new NormalAlertDialog.Builder(MyFootprintActivity.this)
                                .setBoolTitle(false)
                                .setContentText("确定删除该条数据吗?")
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
                                        Map<String,String> map=new HashMap<String, String>();
                                        map.put("id",dataLists.get(position).getId());
                                        mPresenter.clearFootPrint(map);
                                        dialog.dismiss();

                                    }
                                }).build().show();

                        break;
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", dataLists.get(position).getTypeid());
                startActivity(YarnActivity.class, bundle);
            }
        });

    }
    @Override
    public void onRefresh() {
        load(false);
    }
    @Override
    public void loadSuccess(List<FootprintListBean> data) {
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataLists.addAll(data);
        mAdapter.setNewData(dataLists);
    }

    @Override
    public void clearFootPrint(String msg) {
        load(false);
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
                // load(mType,true);
            }
        });
    }

    @Override
    public void clearFail(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @OnClick({R.id.search_tv,R.id.cacel_tv,R.id.search_iv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_tv:
             load(searchEt.getText().toString().trim(),false);
                break;
            case R.id.cacel_tv:
                searchLl.setVisibility(View.GONE);
                break;
            case R.id.search_iv:
                searchLl.setVisibility(View.VISIBLE);
                break;

        }
    }
}
