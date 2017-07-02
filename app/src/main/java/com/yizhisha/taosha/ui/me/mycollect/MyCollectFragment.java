package com.yizhisha.taosha.ui.me.mycollect;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseRVFragment;
import com.yizhisha.taosha.ui.home.yran.YarnActivity;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class MyCollectFragment extends BaseRVFragment {
    private String mTitle;

    public static MyCollectFragment getInstance(String title) {
        MyCollectFragment sf = new MyCollectFragment();
        sf.mTitle = title;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycollect;
    }

    @Override
    protected void initView() {
        initAdapter(new MyCollectAdapter(getAllData()),true,false);
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
        }else {
            mAdapter.setNewData(getData(mTitle));
        }
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(YarnActivity.class);
            }
        });

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
    private List<String> getData(String string){
        List<String> data=new ArrayList<>();
        data.add(string);
        data.add(string);
        data.add(string);
        data.add(string);

        data.add(string);
        data.add(string);
        data.add(string);
        data.add(string);
        return data;
    }

}
