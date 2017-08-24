package com.yizhisha.taosha.ui;

import android.support.annotation.BoolRes;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.taosha.App;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.MainTabEntity;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.ui.home.fragment.HomeFragment;
import com.yizhisha.taosha.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.taosha.ui.login.activity.RegisterActivity;
import com.yizhisha.taosha.ui.me.fragment.MeFragment;
import com.yizhisha.taosha.ui.shoppcart.fragment.ShoppCartFragment;
import com.yizhisha.taosha.utils.SharedPreferencesUtil;
import com.yizhisha.taosha.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"首页", "购物车","个人中心"};
    private int[] mIconUnselectIds = {
            R.drawable.index_home_normal,R.drawable.index_gouwuche_normal,R.drawable.index_me_ormal};
    private int[] mIconSelectIds = {
            R.drawable.index_home,R.drawable.index_gouwuche, R.drawable.index_me};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private HomeFragment homeFragment;
    private ShoppCartFragment shoppCartFragment;
    private MeFragment meFragment;

    private int currentPosition;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化frament
        initFragment(savedInstanceState);
        tabLayout.measure(0,0);
    }

    @Override
    protected void initView() {
        //初始化选项卡
        AppConstant.isLogin = (boolean) SharedPreferencesUtil.getValue(this, "ISLOGIN",new Boolean(false));
        initTab();
    }
    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new MainTabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if(position==1){
                    if(AppConstant.isLogin==false){
                        final List<String> mDatas1=new ArrayList<>();
                        mDatas1.add("登录");
                        mDatas1.add("注册");
                        NormalSelectionDialog dialog=new NormalSelectionDialog.Builder(MainActivity.this)
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
                        dialog.show();
                        tabLayout.setCurrentTab(currentPosition);
                        return;
                    }
                }
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("newsMainFragment");
            shoppCartFragment = (ShoppCartFragment) getSupportFragmentManager().findFragmentByTag("photosMainFragment");
            meFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag("videoMainFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            homeFragment = new HomeFragment();
            shoppCartFragment = new ShoppCartFragment();
            meFragment = new MeFragment();
            transaction.add(R.id.fl_body, homeFragment, "newsMainFragment");
            transaction.add(R.id.fl_body, shoppCartFragment, "photosMainFragment");
            transaction.add(R.id.fl_body, meFragment, "videoMainFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }
    /**
     * 切换
     */
    private void SwitchTo(int position) {
        currentPosition=position;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //首页
            case 0:
                transaction.hide(shoppCartFragment);
                transaction.hide(meFragment);
                transaction.show(homeFragment);
                transaction.commitAllowingStateLoss();
                break;
            //购物车
            case 1:
                transaction.hide(homeFragment);
                transaction.hide(meFragment);
                transaction.show(shoppCartFragment);
                transaction.commitAllowingStateLoss();
                break;
            //个人中心
            case 2:
                transaction.hide(homeFragment);
                transaction.hide(shoppCartFragment);
                transaction.show(meFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
    /**
     * 双击返回键退出应用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                ActivityManager.getActivityMar().exitApp(MainActivity.this);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
