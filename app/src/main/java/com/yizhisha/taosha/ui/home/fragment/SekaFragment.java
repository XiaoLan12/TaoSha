package com.yizhisha.taosha.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lan on 2017/8/16.
 */

public class SekaFragment extends BaseFragment {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private ProductDetailImgAdapter mAdapter;
    private List<String> dataList=new ArrayList<>();
    private boolean isLoad=false;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_details_yarn;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (AppConstant.productDetailBean != null & isLoad == false) {
                dataList.addAll(AppConstant.productDetailBean.getGoods().getSeka());
                mAdapter.setNewData(dataList);
            }
        }
    }

    @Override
    protected void initView() {
        initAdapter();

    }
    private void initAdapter(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new ProductDetailImgAdapter(activity,dataList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
