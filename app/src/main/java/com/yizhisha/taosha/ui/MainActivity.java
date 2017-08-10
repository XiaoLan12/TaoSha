package com.yizhisha.taosha.ui;

import android.support.annotation.BoolRes;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.MainTabEntity;
import com.yizhisha.taosha.ui.home.fragment.HomeFragment;
import com.yizhisha.taosha.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.taosha.ui.me.fragment.MeFragment;
import com.yizhisha.taosha.ui.shoppcart.fragment.ShoppCartFragment;
import com.yizhisha.taosha.utils.SharedPreferencesUtil;

import java.util.ArrayList;

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
        if(SharedPreferencesUtil.getValue(this,"ISLOGIN", Boolean.class)!=null) {
            AppConstant.isLogin = (boolean) SharedPreferencesUtil.getValue(this, "ISLOGIN", Boolean.class);
        }

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
                if(position==1||position==2){
                    if(AppConstant.isLogin==false){
                        startActivity(LoginFragmentActivity.class);
                        tabLayout.setCurrentTab(0);
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
}
