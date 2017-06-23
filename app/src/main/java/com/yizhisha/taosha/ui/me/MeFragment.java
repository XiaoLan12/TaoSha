package com.yizhisha.taosha.ui.me;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;

import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/6/22.
 */
public class MeFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.translucentStatusBar(getActivity(), true);
        }
    }

    @Override
    protected void initView() {

    }
}
