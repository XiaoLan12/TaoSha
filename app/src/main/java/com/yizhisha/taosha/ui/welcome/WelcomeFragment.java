package com.yizhisha.taosha.ui.welcome;

import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.ui.MainActivity;
import com.yizhisha.taosha.ui.home.fragment.SelectYarnFragment;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/8/27 0027.
 */

public class WelcomeFragment extends BaseFragment {
    @Bind(R.id.img)
    ImageView img;
    private int type=1;
    @Bind(R.id.tv_go)
    TextView tv_go;

    public static WelcomeFragment getInstance(int type) {
        WelcomeFragment sf = new WelcomeFragment();
        sf.type=type;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welcome1;
    }

    @Override
    protected void initView() {
        if(type==1){
            img.setImageResource(R.drawable.welcome1);
            tv_go.setVisibility(View.GONE);
        }else if(type==2){
            img.setImageResource(R.drawable.welcome2);
            tv_go.setVisibility(View.GONE);
        }else if(type==3){
            img.setImageResource(R.drawable.welcome3);
            tv_go.setVisibility(View.VISIBLE);
        }
        tv_go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.class);
                getActivity().finish();
            }
        });

    }
}
