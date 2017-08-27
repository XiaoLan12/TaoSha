package com.yizhisha.taosha.ui.me.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by lan on 2017/8/25.
 */

public class AboutTaoShaFragment extends BaseFragment{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    public switchFragmentListener switchFragmentListener;
    public interface switchFragmentListener{
        void switchFragment(int index);
    }
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
        return R.layout.fragment_about_taosha;
    }
    @Override
    protected void initView() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
    @OnClick({R.id.about_rl1,R.id.about_rl3,R.id.call_tv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.about_rl1:
               switchFragmentListener.switchFragment(1);
                break;
            case R.id.about_rl3:
                switchFragmentListener.switchFragment(3);
                break;
            case R.id.call_tv:
                Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + "0769-83115811"));
                startActivity(phoneIneten);
                break;
        }
    }
}
