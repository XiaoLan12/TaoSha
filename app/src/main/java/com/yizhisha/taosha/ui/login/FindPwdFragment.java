package com.yizhisha.taosha.ui.login;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.ui.me.manageaddress.AddAddressActivity;
import com.yizhisha.taosha.utils.CountDownTimerUtil;
import com.yizhisha.taosha.utils.RescourseUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by lan on 2017/6/27.
 */

public class FindPwdFragment extends BaseFragment {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.getcode_findpwd_tv)
    TextView mTvGetCode;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_findpwd;
    }

    @Override
    protected void initView() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
    @OnClick({R.id.getcode_findpwd_tv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.getcode_findpwd_tv:

                CountDownTimerUtil mCountDownTimerUtils = new CountDownTimerUtil(mTvGetCode, 60000, 1000);
                mCountDownTimerUtils.start();
                break;
        }
    }



}