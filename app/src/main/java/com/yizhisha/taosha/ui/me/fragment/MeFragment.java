package com.yizhisha.taosha.ui.me.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.yizhisha.taosha.App;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.json.UserInfoBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.event.LoginEvent;
import com.yizhisha.taosha.event.UserHeadEvent;
import com.yizhisha.taosha.event.WeChatEvent;
import com.yizhisha.taosha.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.taosha.ui.login.activity.RegisterActivity;
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
import com.yizhisha.taosha.ui.me.contract.MeContract;
import com.yizhisha.taosha.ui.me.presenter.MePresenter;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by lan on 2017/6/22.
 */
public class MeFragment extends BaseFragment<MePresenter> implements MeContract.View{
    @Bind(R.id.head_me_iv)
    ImageView mIvHead;
    @Bind(R.id.username_me_tv)
    TextView mTVUserName;
    private Subscription subscription;

    private NormalSelectionDialog dialog;
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
        if(AppConstant.isLogin==true){
            load();
        }else{
            mTVUserName.setText("毛织网感谢您的支持,请登录");
        }
        event();

    }
    private void load(){
        mPresenter.getUserInfo(AppConstant.UID);
    }
    @Override
    public void getUserInfoSuccess(UserInfoBean info) {
        if(info!=null){
            AppConstant.infoBean=info;
            GlideUtil.getInstance().LoadContextCircleBitmap(getActivity(),AppConstant.USERHEAD+info.getAvatar(),mIvHead,
                    R.drawable.icon_head_normal,R.drawable.icon_head_normal);
            mTVUserName.setText(info.getUsername());
        }
    }
    @Override
    public void loadFail(String msg) {

    }
    private void event(){
        subscription= RxBus.$().toObservable(Object.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object event) {
                        if (event instanceof UserHeadEvent) {
                            UserHeadEvent headEvent = (UserHeadEvent) event;
                            GlideUtil.getInstance().LoadContextCircleBitmap(activity, headEvent.getAvatar(), mIvHead,
                                    R.drawable.icon_head_normal, R.drawable.icon_head_normal);
                        }else if(event instanceof LoginEvent){
                            load();
                        }
                    }
                });
    }
    private void showLoginDialog(){
        final List<String> mDatas1=new ArrayList<>();
        mDatas1.add("登录");
        mDatas1.add("注册");
        dialog=new NormalSelectionDialog.Builder(activity)
                .setBoolTitle(true)
                .setTitleText("温馨提示\n尊敬的用户,您尚未登录,请选择登录或注册")
                .setTitleHeight(55)
                .setItemHeight(45)
                .setItemTextColor(R.color.blue)
                .setItemTextSize(14)
                .setItemWidth(0.95f)
                .setCancleButtonText("取消")
                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                    @Override
                    public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                        switch (position){
                            case 0:
                                startActivity(LoginFragmentActivity.class);
                                break;
                            case 1:
                                startActivity(RegisterActivity.class);
                                break;
                        }
                        dialog.dismiss();
                    }
                }).setTouchOutside(true)
                .build();
        dialog.setData(mDatas1);
    }
    @OnClick({R.id.set_me_iv,R.id.myorder_set_tv,R.id.mycollect_set_tv,R.id.myfootprint_set_tv,
            R.id.accountcenter_me_rl,R.id.freesample_me_rl,R.id.myrating_me_rl,R.id.contactus_rl
            ,R.id.abouttaosha_rl,R.id.seckillorder_me_rl})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()){
            case R.id.set_me_iv:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(SettinActivity.class);
                break;
            case R.id.myorder_set_tv:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyOrderAcitvity.class);
                break;
            case R.id.mycollect_set_tv:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyCollectActivity.class);
                break;
            case R.id.myfootprint_set_tv:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyFootprintActivity.class);
                break;
            case R.id.accountcenter_me_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(AccountCenterActivity.class);
                break;
            case R.id.freesample_me_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(FreeSampleActivity.class);
                break;
            case R.id.myrating_me_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyRatingActivity.class);
                break;
            case R.id.contactus_rl:

                startActivity(ContactUsActivity.class);
                break;
            case R.id.abouttaosha_rl:
                startActivity(AboutActivity.class);
                break;
            case R.id.seckillorder_me_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(SecKillOrderActivity.class);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (subscription != null&&!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
