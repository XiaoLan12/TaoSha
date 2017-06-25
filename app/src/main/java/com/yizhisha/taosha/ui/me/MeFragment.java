package com.yizhisha.taosha.ui.me;

import android.view.View;
import android.widget.Switch;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.ui.me.myorder.MyOrderAcitvity;
import com.yizhisha.taosha.ui.me.settin.SettinActivity;

import butterknife.OnClick;
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
    @OnClick({R.id.set_me_iv,R.id.myorder_set_tv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()){
            case R.id.set_me_iv:
                startActivity(SettinActivity.class);
                break;
            case R.id.myorder_set_tv:
                startActivity(MyOrderAcitvity.class);
                break;
        }
    }
}
