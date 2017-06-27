package com.yizhisha.taosha.ui.login;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by lan on 2017/6/27.
 */

public class LoginFragment extends BaseFragment {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    public  switchFragmentListener switchFragmentListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //对传递进来的Activity进行接口转换
        if(getActivity() instanceof switchFragmentListener){
            switchFragmentListener = ((switchFragmentListener)getActivity());
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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
    public interface switchFragmentListener{
        void switchFragment(int index);
    }
    @OnClick({R.id.findpwd_tv,R.id.register_tv,R.id.phone_login_tv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.findpwd_tv:
                switchFragmentListener.switchFragment(1);
                break;
            case R.id.register_tv:
                switchFragmentListener.switchFragment(2);
                break;
            case R.id.phone_login_tv:
                switchFragmentListener.switchFragment(3);
                break;
        }
    }
}
