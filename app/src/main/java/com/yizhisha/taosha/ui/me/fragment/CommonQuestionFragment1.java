package com.yizhisha.taosha.ui.me.fragment;

import android.view.View;
import android.widget.ImageView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.common.dialog.PicShowDialog;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by lan on 2017/8/25.
 */

public class CommonQuestionFragment1 extends BaseFragment{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.iv)
    PhotoView iv;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common_question1;
    }

    @Override
    protected void initView() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        iv.setImageResource(R.drawable.icon_commonquestion1);


    }
}
