package com.yizhisha.taosha.ui.me.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseRVActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.MyOrderTabEntity;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MyOrderAcitvity extends BaseRVActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    //@Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.commontablayout)
    CommonTabLayout commonTabLayout;
    private String[] mTitles = {"全部", "待付款", "待发货", "待收货","已完成"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order_acitvity;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(MyOrderAcitvity.this);
            }
        });
    }
    @Override
    protected void initView() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new MyOrderTabEntity(mTitles[i]));
        }
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                commonTabLayout.setCurrentTab(position);
                    switch (position){
                        case 0:
                            mAdapter.setNewData(getAllData());
                            break;
                        case 1:
                            mAdapter.setNewData(getData1());
                            break;
                        case 2:
                            mAdapter.setNewData(getData2());
                            break;
                        case 3:
                            mAdapter.setNewData(getData3());
                            break;
                        case 4:
                            mAdapter.setNewData(getData4());
                            break;
                    }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        initAdapter(new MyOrderAdapter(getAllData()),false,false);
        if(mRecyclerView==null){
            Log.d("TTT","啊啊啊啊aaaa");
        }
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(OrderDetailsActivity.class);
            }
        });
    }
    private List<String> getAllData(){
        List<String> data=new ArrayList<>();
        data.add("待付款");
        data.add("待收货");
        data.add("待发货");
        data.add("已完成");

        data.add("待付款");
        data.add("待收货");
        data.add("待发货");
        data.add("已完成");

        data.add("待付款");
        data.add("待收货");
        data.add("待发货");
        data.add("已完成");

        return data;
    }
    private List<String> getData1(){
        List<String> data=new ArrayList<>();
        data.add("待付款");
        data.add("待付款");
        data.add("待付款");
        data.add("待付款");

        data.add("待付款");
        data.add("待付款");
        data.add("待付款");
        data.add("待付款");
        return data;
    }
    private List<String> getData2(){
        List<String> data=new ArrayList<>();
        data.add("待发货");
        data.add("待发货");
        data.add("待发货");
        data.add("待发货");

        data.add("待发货");
        data.add("待发货");
        data.add("待发货");
        data.add("待发货");


        return data;
    }
    private List<String> getData3(){
        List<String> data=new ArrayList<>();
        data.add("待收货");
        data.add("待收货");
        data.add("待收货");
        data.add("待收货");

        data.add("待收货");
        data.add("待收货");
        data.add("待收货");
        data.add("待收货");
        return data;
    }
    private List<String> getData4(){
        List<String> data=new ArrayList<>();
        data.add("已完成");
        data.add("已完成");
        data.add("已完成");
        data.add("已完成");

        data.add("已完成");
        data.add("已完成");
        data.add("已完成");
        data.add("已完成");
        return data;
    }

    @Override
    public void onLoadMoreRequested() {
        super.onLoadMoreRequested();
            mAdapter.loadMoreEnd();
    }
}
