package com.yizhisha.taosha.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * Created by lan on 2017/8/16.
 */

public class SekaFragment extends BaseFragment {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private ProductDetailImgAdapter mAdapter;
    private List<String> dataList=new ArrayList<>();

    public static SekaFragment getInstance(List<String> list) {
        SekaFragment sf = new SekaFragment();
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
        initAdapter();

    }
    private void initAdapter(){
        for(int i=0;i<dataList.size();i++){
            dataList.set(i,AppConstant.PRODUCT_DETAIL_SEKA_IMG_URL+dataList.get(i));
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new ProductDetailImgAdapter(activity,dataList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                PicShowDialog dialog=new PicShowDialog(activity,dataList,position);
                dialog.show();
            }
        });
    }
}
