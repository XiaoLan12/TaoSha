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
    private boolean isLoad=false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_details_yarn;
    }
    @Override
    public void setUserVisibleHint(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(hidden){
            if(AppConstant.productDetailBean!=null&isLoad==false){

                isLoad=true;
                List<String> content=new ArrayList<>();
                List<String> content1=new ArrayList<>();
                content=AppConstant.productDetailBean.getGoods().getContent_();
                for(int i=0;i<content.size();i++){
                    content1.add(AppConstant.PRODUCT_DETAIL_SEKA_IMG_URL+content.get(i));
                }
                adapter=new ProductDetailImgAdapter(getActivity(),content1);
                recyclerView.setAdapter(adapter);
            }

        }
    }



    @Override
    protected void initView() {
        Log.e("YY","---");
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(getActivity());
        linearLayoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager4);

    }
}
