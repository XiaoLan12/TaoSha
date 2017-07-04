package com.yizhisha.taosha.ui.me.fragment;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.adapter.RemainRatingAdapter;
import com.yizhisha.taosha.base.BaseRVFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class RemainRatingFragment extends BaseRVFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rating;
    }
    @Override
    protected void initView() {
        initAdapter(new RemainRatingAdapter(getAllData()),false,false);
    }
    private List<String> getAllData(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add(i+"");
        }
        return list;
    }
}
