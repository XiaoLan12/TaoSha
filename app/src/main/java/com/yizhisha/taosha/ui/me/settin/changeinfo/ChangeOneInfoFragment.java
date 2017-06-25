package com.yizhisha.taosha.ui.me.settin.changeinfo;

import android.os.Bundle;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.ui.me.settin.SetInfoActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ChangeOneInfoFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_changeoneinfo;
    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.changeusernamee_rl})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.changeusernamee_rl:
                startActivity(ChangeUserNameActivity.class);
                break;

        }
    }
}
