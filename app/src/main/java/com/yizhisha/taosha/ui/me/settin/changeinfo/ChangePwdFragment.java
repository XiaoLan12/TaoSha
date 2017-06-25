package com.yizhisha.taosha.ui.me.settin.changeinfo;

import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ChangePwdFragment extends BaseFragment{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_changepwd;
    }
    @Override
    protected void initView() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(getActivity());
            }
        });
    }
}
