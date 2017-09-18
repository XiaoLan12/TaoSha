package com.yizhisha.taosha.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.common.dialog.PicShowDialog;

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
        if(list!=null){
            sf.dataList.addAll(list);
        }
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_details_yarn;
    }

    @Override
    protected void initView() {
        for(int i=0;i<dataList.size();i++){
            dataList.set(i,AppConstant.PRODUCT_DETAIL_SEKA_IMG_URL+dataList.get(i));
        }
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(getActivity());
        linearLayoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager4);
        adapter=new ProductDetailImgAdapter(getActivity(),dataList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicShowDialog dialog=new PicShowDialog(activity,dataList,position);
                dialog.show();
            }
        });

    }
}
