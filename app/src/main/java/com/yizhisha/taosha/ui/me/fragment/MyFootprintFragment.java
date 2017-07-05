package com.yizhisha.taosha.ui.me.fragment;


import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class MyFootprintFragment extends BaseFragment {
    private String mTitle;
    public static MyFootprintFragment getInstance(String title) {
        MyFootprintFragment sf = new MyFootprintFragment();
        sf.mTitle = title;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycollect;
    }

    @Override
    protected void initView() {
       /* initAdapter(new MyCollectAdapter(getAllData()),true,false);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        if(mTitle.equals("全部")){
            mAdapter.setNewData(getAllData());
        }else if(mTitle.equals("棉纺纱")){
            mAdapter.setNewData(getData1());
        }
        else if(mTitle.equals("麻纺纱")){
            mAdapter.setNewData(getData2());
        }
        else if(mTitle.equals("毛纺纱")){
            mAdapter.setNewData(getData3());
        }
        else if(mTitle.equals("化纤纱")){
            mAdapter.setNewData(getData4());
        }
        else if(mTitle.equals("混纺纱")){
            mAdapter.setNewData(getData5());
        }*/
    }
    private List<String> getAllData(){
        List<String> data=new ArrayList<>();
        data.add("全部");
        data.add("全部");
        data.add("全部");
        data.add("全部");

        data.add("全部");
        data.add("全部");
        data.add("全部");
        data.add("全部");
        return data;
    }
    private List<String> getData1(){
        List<String> data=new ArrayList<>();
        data.add("棉纺纱");
        data.add("棉纺纱");
        data.add("棉纺纱");
        data.add("棉纺纱");

        data.add("棉纺纱");
        data.add("棉纺纱");
        data.add("棉纺纱");
        data.add("棉纺纱");
        return data;
    }
    private List<String> getData2(){
        List<String> data=new ArrayList<>();
        data.add("麻纺纱");
        data.add("麻纺纱");
        data.add("麻纺纱");
        data.add("麻纺纱");

        data.add("麻纺纱");
        data.add("麻纺纱");
        data.add("麻纺纱");
        data.add("麻纺纱");
        return data;
    }
    private List<String> getData3(){
        List<String> data=new ArrayList<>();
        data.add("毛纺纱");
        data.add("毛纺纱");
        data.add("毛纺纱");
        data.add("毛纺纱");

        data.add("毛纺纱");
        data.add("毛纺纱");
        data.add("毛纺纱");
        data.add("毛纺纱");
        return data;
    }
    private List<String> getData4(){
        List<String> data=new ArrayList<>();
        data.add("化纤纱");
        data.add("化纤纱");
        data.add("化纤纱");
        data.add("化纤纱");

        data.add("化纤纱");
        data.add("化纤纱");
        data.add("化纤纱");
        data.add("化纤纱");
        return data;
    }
    private List<String> getData5(){
        List<String> data=new ArrayList<>();
        data.add("混纺纱");
        data.add("混纺纱");
        data.add("混纺纱");
        data.add("混纺纱");

        data.add("混纺纱");
        data.add("混纺纱");
        data.add("混纺纱");
        data.add("混纺纱");
        return data;
    }
}
