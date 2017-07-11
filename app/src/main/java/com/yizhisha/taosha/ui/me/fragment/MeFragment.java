package com.yizhisha.taosha.ui.me.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.taosha.ui.me.activity.AboutActivity;
import com.yizhisha.taosha.ui.me.activity.AccountCenterActivity;
import com.yizhisha.taosha.ui.me.activity.ContactUsActivity;
import com.yizhisha.taosha.ui.me.activity.FreeSampleActivity;
import com.yizhisha.taosha.ui.me.activity.MyCollectActivity;
import com.yizhisha.taosha.ui.me.activity.MyFootprintActivity;
import com.yizhisha.taosha.ui.me.activity.MyOrderAcitvity;
import com.yizhisha.taosha.ui.me.activity.MyRatingActivity;
import com.yizhisha.taosha.ui.me.activity.SecKillOrderActivity;
import com.yizhisha.taosha.ui.me.activity.SettinActivity;
import com.yizhisha.taosha.utils.GlideUtil;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/6/22.
 */
public class MeFragment extends BaseFragment{
    @Bind(R.id.head_me_iv)
    ImageView mIvHead;
    @Bind(R.id.username_me_tv)
    TextView mTVUserName;
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
    @OnClick({R.id.set_me_iv,R.id.myorder_set_tv,R.id.mycollect_set_tv,R.id.myfootprint_set_tv,
            R.id.accountcenter_me_rl,R.id.freesample_me_rl,R.id.myrating_me_rl,R.id.contactus_rl
            ,R.id.abouttaosha_rl,R.id.seckillorder_me_rl})
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
            case R.id.mycollect_set_tv:
                startActivity(MyCollectActivity.class);
                break;
            case R.id.myfootprint_set_tv:
                startActivity(MyFootprintActivity.class);
                break;
            case R.id.accountcenter_me_rl:
                startActivity(AccountCenterActivity.class);
                break;
            case R.id.freesample_me_rl:
                startActivity(FreeSampleActivity.class);
                break;
            case R.id.myrating_me_rl:
                startActivity(MyRatingActivity.class);
                break;
            case R.id.contactus_rl:
                startActivity(ContactUsActivity.class);
                break;
            case R.id.abouttaosha_rl:
                //startActivity(AboutActivity.class);
                startActivity(LoginFragmentActivity.class);
                break;
            case R.id.seckillorder_me_rl:
                startActivity(SecKillOrderActivity.class);
                break;
        }
    }
}
