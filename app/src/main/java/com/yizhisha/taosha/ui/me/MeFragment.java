package com.yizhisha.taosha.ui.me;

import android.view.View;
import android.widget.Switch;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.ui.login.LoginFragmentActivity;
import com.yizhisha.taosha.ui.me.abouttaosha.AboutActivity;
import com.yizhisha.taosha.ui.me.accountingcenter.AccountCenterActivity;
import com.yizhisha.taosha.ui.me.contactus.ContactUsActivity;
import com.yizhisha.taosha.ui.me.freesample.FreeSampleActivity;
import com.yizhisha.taosha.ui.me.mycollect.MyCollectActivity;
import com.yizhisha.taosha.ui.me.myfootprint.MyFootprintActivity;
import com.yizhisha.taosha.ui.me.myorder.MyOrderAcitvity;
import com.yizhisha.taosha.ui.me.myrating.MyRatingActivity;
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
    @OnClick({R.id.set_me_iv,R.id.myorder_set_tv,R.id.mycollect_set_tv,R.id.myfootprint_set_tv,
    R.id.accountcenter_me_rl,R.id.freesample_me_rl,R.id.myrating_me_rl,R.id.contactus_rl,R.id.abouttaosha_rl})
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
        }
    }
}
