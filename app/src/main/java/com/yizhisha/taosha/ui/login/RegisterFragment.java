package com.yizhisha.taosha.ui.login;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.utils.RescourseUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by lan on 2017/6/27.
 */

public class RegisterFragment extends BaseFragment {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.getcode_register_tv)
    TextView mTvGetCode;

    private MyCount mc;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
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
    @OnClick({R.id.getcode_register_tv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.getcode_register_tv:
                /*if (mc == null) {
                    mc = new MyCount(60000, 1000);
                }
                mc.start();*/
                break;
        }
    }
    /**
     * 内部类,获取验证码
     */

    private class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTvGetCode.setEnabled(false);
            mTvGetCode.setTextColor(RescourseUtil.getColor(R.color.gray1));
            mTvGetCode.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            mTvGetCode.setEnabled(true);
            mTvGetCode.setTextColor(RescourseUtil.getColor(R.color.red2));
            mTvGetCode.setText("获取验证码");
        }
    }
}
