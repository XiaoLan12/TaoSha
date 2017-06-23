package com.yizhisha.taosha.ui.shoppcart;

import android.graphics.Color;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;

import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/6/22.
 */
public class ShoppCartFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoppcart;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.setStatusBarColor(activity, Color.WHITE,125);
        }
    }
    @Override
    protected void initView() {

    }
}
