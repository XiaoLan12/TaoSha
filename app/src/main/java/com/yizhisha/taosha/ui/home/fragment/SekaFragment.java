package com.yizhisha.taosha.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.ProductDeatilItemBean;
import com.yizhisha.taosha.bean.json.SeckillProductBean;

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

    public static SekaFragment getInstance(List<String> list) {
        SekaFragment sf = new SekaFragment();
       sf.dataList.addAll(list);
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_details_yarn;
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
