package com.yizhisha.taosha.ui.home;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.HomeYarnTypeAdapter;
import com.yizhisha.taosha.adapter.SearchAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.HomeYarnTypeEntity;
import com.yizhisha.taosha.widget.GridDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/1.
 */

public class SearchActivity  extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.img_back)
    ImageView img_back;


    SearchAdapter adapter;
    List<HomeYarnTypeEntity> dataLists=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
                //设置状态栏颜色
                    StatusBarCompat.setStatusBarColor(SearchActivity.this, this.getResources().getColor(R.color.home_search_color));
        //设置颜色分割线
        LinearLayoutManager mManagerColor = new GridLayoutManager(SearchActivity.this, 4);
        recyclerView.setLayoutManager(mManagerColor);
        recyclerView.addItemDecoration(new GridDivider(SearchActivity.this, 1, this.getResources().getColor(R.color.common_divider_narrow)));

        dataLists=setData();
        adapter=new SearchAdapter(dataLists);
        recyclerView.setAdapter(adapter);
    }
    private List<HomeYarnTypeEntity> setData(){
        List<HomeYarnTypeEntity> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            HomeYarnTypeEntity homeYarnTypeEntity=new HomeYarnTypeEntity();
            homeYarnTypeEntity.setImg(R.drawable.index_rc_1);
            homeYarnTypeEntity.setName("第"+i);
            list.add(homeYarnTypeEntity);
        }
        return list;
    }
    @OnClick({R.id.img_back})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.img_back:
                finish_Activity(SearchActivity.this);
                break;
        }
    }
}
