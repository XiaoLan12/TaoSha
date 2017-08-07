package com.yizhisha.taosha.ui.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.adapter.SelectYarnColorAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.SelectYarnBean;
import com.yizhisha.taosha.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/19.
 */

public class SelectYarnColor extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.recycleview1)
    RecyclerView recyclerView1;

    @Bind(R.id.tv_continue_add)
    TextView tv_continue_add;
    private ProductDetailImgAdapter adapter;
    private SelectYarnColorAdapter adapter1;
    private List<SelectYarnBean> list=new ArrayList<>();
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
        SelectYarnBean selectYarnBean=new SelectYarnBean("",1);
        list.add(selectYarnBean);
        adapter1=new SelectYarnColorAdapter(SelectYarnColor.this,list);
        recyclerView1.setAdapter(adapter1);
        adapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.img_delete:
                        adapter1.remove(position);
                        break;
                }
            }
        });



        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SelectYarnColor.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(linearLayoutManager);
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
    @OnClick({R.id.tv_continue_add})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_continue_add:
                if(list.size()>7){
                    ToastUtil.showShortToast("一个订单最多八组");
                    return;
                }
                SelectYarnBean selectYarnBean=new SelectYarnBean("",1);
                list.add(selectYarnBean);
                adapter1.notifyDataSetChanged();
                break;
        }

    }
}
