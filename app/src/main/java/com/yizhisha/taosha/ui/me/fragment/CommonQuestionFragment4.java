package com.yizhisha.taosha.ui.me.fragment;

import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;

import butterknife.Bind;

/**
 * Created by lan on 2017/8/25.
 */

public class CommonQuestionFragment4 extends BaseFragment{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common_question4;
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
}
