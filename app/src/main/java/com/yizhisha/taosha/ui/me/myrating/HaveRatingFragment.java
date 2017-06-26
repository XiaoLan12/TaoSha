package com.yizhisha.taosha.ui.me.myrating;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.HaveRatingAdapter;
import com.yizhisha.taosha.adapter.RemainRatingAdapter;
import com.yizhisha.taosha.base.BaseRVFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/6/26.
 */

public class HaveRatingFragment extends BaseRVFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rating;
    }
    @Override
    protected void initView() {
        initAdapter(new HaveRatingAdapter(getAllData()),true,false);
    }
    private List<String> getAllData(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add(i+"");
        }
        return list;
    }
}
