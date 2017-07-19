package com.yizhisha.taosha.ui.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/19.
 */

public class SelectYarnColor extends BaseActivity{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    private ProductDetailImgAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_yarn_color;
    }

    @Override
    protected void initToolBar() {
        toolbar.setTitle("立即购买");
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(SelectYarnColor.this);
        linearLayoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager4);
        if(AppConstant.productDetailBean!=null){
            List<String> content=new ArrayList<>();
            List<String> content1=new ArrayList<>();
            content=AppConstant.productDetailBean.getGoods().getSeka();
            for(int i=0;i<content.size();i++){
                content1.add(AppConstant.PRODUCT_DETAIL_SEKA_IMG_URL+content.get(i));
            }
            adapter=new ProductDetailImgAdapter(SelectYarnColor.this,content1);
            recyclerView.setAdapter(adapter);

        }
    }
}
