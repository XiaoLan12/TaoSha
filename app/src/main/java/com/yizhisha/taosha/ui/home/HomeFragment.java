package com.yizhisha.taosha.ui.home;

import android.graphics.Color;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;

import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/6/22.
 */
public class HomeFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.setStatusBarColor(activity, Color.GREEN);
        }
    }
    @Override
    protected void initView() {

    }
}
