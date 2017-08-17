package com.yizhisha.taosha.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/2.
 */

public class DetailsYarnFragment extends BaseFragment{
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    private ProductDetailImgAdapter adapter;
    private List<String> dataList=new ArrayList<>();
    public static DetailsYarnFragment getInstance(List<String> list) {
        DetailsYarnFragment sf = new DetailsYarnFragment();
        sf.dataList.addAll(list);
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_details_yarn;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(getActivity());
        linearLayoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager4);
        adapter=new ProductDetailImgAdapter(getActivity(),dataList);
        recyclerView.setAdapter(adapter);

    }
}
