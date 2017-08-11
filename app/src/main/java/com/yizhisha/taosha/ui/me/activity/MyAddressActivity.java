package com.yizhisha.taosha.ui.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyAddressAdapter;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.ui.me.contract.MyAddressContract;
import com.yizhisha.taosha.ui.me.presenter.MyAddressPresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyAddressActivity extends BaseActivity<MyAddressPresenter> implements
        MyAddressContract.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MyAddressAdapter mAdapter;
    private List<AddressListBean.Address> dataList=new ArrayList<>();
    private CityPickerView mCityPickerView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_address;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(MyAddressActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        initAdapter();
        mPresenter.loadAddress(AppConstant.UID,true);
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter=new MyAddressAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.edit_myaddress_tv:
                        Bundle bundle=new Bundle();
                        bundle.putInt("TYPE",1);
                        bundle.putInt("ID",dataList.get(position).getId());
                        startActivityForResult(AddAddressActivity.class,bundle,2);
                        break;
                    case R.id.delete_myaddress_tv:
                        mPresenter.deleteAddress(dataList.get(position).getId());
                        dataList.remove(position);
                        break;
                }
            }
        });
    }
    @Override
    public void onRefresh() {
        mPresenter.loadAddress(AppConstant.UID,false);
    }

    @Override
    public void loadAddressSuccess(List<AddressListBean.Address> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        dataList.clear();
        dataList.addAll(data);
        mAdapter.setNewData(dataList);
    }

    @Override
    public void deleteAddress() {
        mAdapter.setNewData(dataList);
        if(dataList.size()<=0){
            mLoadingView.loadSuccess(true);
        }
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
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                mPresenter.loadAddress(AppConstant.UID,true);
            }
        });
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
    }

    @Override
    public void deleteFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }

    @OnClick({R.id.add_address_ll})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.add_address_ll:
                Bundle bundle=new Bundle();
                bundle.putInt("TYPE",0);
                startActivityForResult(AddAddressActivity.class,bundle,2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2&&resultCode==2){
            mPresenter.loadAddress(AppConstant.UID,false);
        }
    }
}
