package com.yizhisha.taosha.ui.me.fragment;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.FreeSampleAdapter;
import com.yizhisha.taosha.adapter.MyAddressAdapter;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.base.BaseRVFragment;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.ui.me.contract.FreeSampleContract;
import com.yizhisha.taosha.ui.me.presenter.FreeSamplePresenter;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/7/5.
 */

public class MyOrderFragment extends BaseRVFragment{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    private int mType=0;
    private List<FreeSampleBean.Active> dataList=new ArrayList<>();

    public static MyOrderFragment getInstance(int type) {
        MyOrderFragment sf = new MyOrderFragment();
        sf.mType = type;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_freesample;
    }

    @Override
    protected void initView() {
        initAdapter(new MyOrderAdapter(getAllData()),true,false);
        dataList.clear();
       /* if(mAdapter.getData().size()<=0){
            load(mType,true);
        }*/
    }
    private List<String> getAllData(){
        List<String> data=new ArrayList<>();
        data.add("待付款");
        data.add("待收货");
        data.add("待发货");
        data.add("已完成");

        data.add("待付款");
        data.add("待收货");
        data.add("待发货");
        data.add("已完成");

        data.add("待付款");
        data.add("待收货");
        data.add("待发货");
        data.add("已完成");

        return data;
    }
    private List<String> getData1(){
        List<String> data=new ArrayList<>();
        data.add("待付款");
        data.add("待付款");
        data.add("待付款");
        data.add("待付款");

        data.add("待付款");
        data.add("待付款");
        data.add("待付款");
        data.add("待付款");
        return data;
    }
    private List<String> getData2(){
        List<String> data=new ArrayList<>();
        data.add("待发货");
        data.add("待发货");
        data.add("待发货");
        data.add("待发货");

        data.add("待发货");
        data.add("待发货");
        data.add("待发货");
        data.add("待发货");


        return data;
    }
    private List<String> getData3(){
        List<String> data=new ArrayList<>();
        data.add("待收货");
        data.add("待收货");
        data.add("待收货");
        data.add("待收货");

        data.add("待收货");
        data.add("待收货");
        data.add("待收货");
        data.add("待收货");
        return data;
    }
    private List<String> getData4(){
        List<String> data=new ArrayList<>();
        data.add("已完成");
        data.add("已完成");
        data.add("已完成");
        data.add("已完成");

        data.add("已完成");
        data.add("已完成");
        data.add("已完成");
        data.add("已完成");
        return data;
    }

}
