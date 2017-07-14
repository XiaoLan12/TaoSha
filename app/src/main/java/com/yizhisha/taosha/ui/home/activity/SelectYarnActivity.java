package com.yizhisha.taosha.ui.home.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.MyOrderTabEntity;
import com.yizhisha.taosha.bean.PopupListBean;
import com.yizhisha.taosha.common.popupwindow.ListPopupwindow;
import com.yizhisha.taosha.ui.home.fragment.SelectYarnFragment;
import com.yizhisha.taosha.ui.me.fragment.FreeSampleFragment;
import com.yizhisha.taosha.utils.DensityUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/2.
 */

public class SelectYarnActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.commontablayout)
    CommonTabLayout commonTabLayout;
    @Bind(R.id.vp)
    ViewPager viewPager;
    @Bind(R.id.img_back)
    ImageView img_back;

    private String[] mTitles = {"棉纱", "针型", "价格", "排序"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private SelectYarnFragment mFragment;
    private FragmentPagerAdapter mAdapter;
    private ListPopupwindow mYarnPopup;
    private ListPopupwindow mPricePopup;
    private ListPopupwindow mNeedlePopup;
    private ListPopupwindow mOrderbyPopup;

    private int mYarnType=1;
    private String mNeedleType="3g";
    private int mPriceType=1;
    private int mOrderByType=1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_yarn;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(SelectYarnActivity.this, this.getResources().getColor(R.color.red3));
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new MyOrderTabEntity(mTitles[i]));
        }

        for (int i=0;i<mTitles.length;i++) {
            mFragments.add(SelectYarnFragment.getInstance());
        }
        commonTabLayout.setTabData(mTabEntities);
        mFragment= (SelectYarnFragment) mFragments.get(0);

        mAdapter = new FragmentPagerAdapter(SelectYarnActivity.this.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        };
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(0);

        // 对ViewPager设置滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            int lastPosition;
            @Override
            public void onPageSelected(int position) {

                commonTabLayout.setCurrentTab(position);
                mFragment= (SelectYarnFragment) mFragments.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //导航栏设置监听
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mFragment= (SelectYarnFragment) mFragments.get(position);
                switch (position){
                    case 0:
                        if(mYarnPopup==null){
                            initPopup1(position);
                        }
                        if(!mYarnPopup.isShowing()){
                            mYarnPopup.show(commonTabLayout.getTitleView(position));
                        }
                        break;
                    case 1:
                        if(mNeedlePopup==null){
                            initPopup3(position);
                        }
                        if(!mNeedlePopup.isShowing()){
                            mNeedlePopup.show(commonTabLayout.getTitleView(position));
                        }
                        break;
                    case 2:
                        if(mPricePopup==null){
                            initPopup2(position);
                        }
                        if(!mPricePopup.isShowing()){
                            mPricePopup.show(commonTabLayout.getTitleView(position));
                        }
                        break;
                    case 3:
                        if(mOrderbyPopup==null){
                            initPopup4(position);
                        }
                        if(!mOrderbyPopup.isShowing()){
                            mOrderbyPopup.show(commonTabLayout.getTitleView(position));
                        }
                        break;
                }
              }

            @Override
            public void onTabReselect(int position) {
                mFragment= (SelectYarnFragment) mFragments.get(position);
                switch (position){
                    case 0:
                        if(mYarnPopup==null){
                            initPopup1(position);
                        }
                        if(!mYarnPopup.isShowing()){
                            mYarnPopup.show(commonTabLayout.getTitleView(position));
                        }
                        break;
                    case 1:
                        if(mNeedlePopup==null){
                            initPopup3(position);
                        }
                        if(!mNeedlePopup.isShowing()){
                            mNeedlePopup.show(commonTabLayout.getTitleView(position));
                        }
                        break;
                    case 2:
                        if(mPricePopup==null){
                            initPopup2(position);
                        }
                        if(!mPricePopup.isShowing()){
                            mPricePopup.show(commonTabLayout.getTitleView(position));
                        }
                        break;
                    case 3:
                        if(mOrderbyPopup==null){
                            initPopup4(position);
                        }
                        if(!mOrderbyPopup.isShowing()){
                            mOrderbyPopup.show(commonTabLayout.getTitleView(position));
                        }
                        break;
                }
            }
        });
    }
    private void initPopup1(final int index) {

        int[] id = new int[]{1, 539, 540, 544, 541, 542, 545};
        String[] title = new String[]{"全部数据", "麻纺纱", "毛纺纱", "棉纺纱", "混纺纱", "花式纱", "化纤纱"};
        ArrayList<PopupListBean> list = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            PopupListBean popupListBean = new PopupListBean();
            popupListBean.setId(id[i]);
            popupListBean.setTitle(title[i]);
            list.add(popupListBean);
        }
        // 实例化标题栏弹窗
        mYarnPopup = new ListPopupwindow(this, DensityUtil.getScreenWidth(this)/4-10,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mYarnPopup.setItemOnClickListener(new ListPopupwindow.OnItemOnClickListener() {
            @Override
            public void onItemClick(PopupListBean item, int position) {
                    mYarnType=item.getId();
                    viewPager.setCurrentItem(index);
                    mFragment.loadSearch(mYarnType,mPriceType,mNeedleType,mOrderByType);

            }
        });
        // 给标题栏弹窗添加子类
        mYarnPopup.addAction(list);
    }
    private void initPopup2(final int index) {
        int[] id = new int[]{1, 2, 3, 4, 5, 6};
        String[] title = new String[]{"不限", "51~100", "101~200", "201~300", "301~500", ">500"};
        ArrayList<PopupListBean> list = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            PopupListBean popupListBean = new PopupListBean();
            popupListBean.setId(id[i]);
            popupListBean.setTitle(title[i]);
            list.add(popupListBean);
        }
        // 实例化标题栏弹窗
        mPricePopup = new ListPopupwindow(this, DensityUtil.getScreenWidth(this)/4-10,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPricePopup.setItemOnClickListener(new ListPopupwindow.OnItemOnClickListener() {
            @Override
            public void onItemClick(PopupListBean item, int position) {
                mPriceType=item.getId();
                viewPager.setCurrentItem(index);
                mFragment.loadSearch(mYarnType,mPriceType,mNeedleType,mOrderByType);
            }
        });
        // 给标题栏弹窗添加子类
        mPricePopup.addAction(list);
    }
    private void initPopup3(final int index) {

        String[] title = new String[]{"3g", "6g"};
        ArrayList<PopupListBean> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            PopupListBean popupListBean = new PopupListBean();
            popupListBean.setId(0);
            popupListBean.setTitle(title[i]);
            list.add(popupListBean);
        }
        // 实例化标题栏弹窗
        mNeedlePopup = new ListPopupwindow(this, DensityUtil.getScreenWidth(this)/4-10,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mNeedlePopup.setItemOnClickListener(new ListPopupwindow.OnItemOnClickListener() {
            @Override
            public void onItemClick(PopupListBean item, int position) {
                mNeedleType=item.getTitle();
                viewPager.setCurrentItem(index);
                mFragment.loadSearch(mYarnType,mPriceType,mNeedleType,mOrderByType);
            }
        });
        // 给标题栏弹窗添加子类
        mNeedlePopup.addAction(list);
    }
    private void initPopup4(final int index){
        int[] id=new int[]{1,2,3,4};
        String[] title=new String[]{"销量降序","点击降序","收藏降序","添加时间"};
        ArrayList<PopupListBean> list=new ArrayList<>();
        for(int i=0;i<id.length;i++){
            PopupListBean popupListBean=new PopupListBean();
            popupListBean.setId(id[i]);
            popupListBean.setTitle(title[i]);
            list.add(popupListBean);
        }
        // 实例化标题栏弹窗
        mOrderbyPopup = new ListPopupwindow(this, DensityUtil.getScreenWidth(this)/4,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mOrderbyPopup.setItemOnClickListener(new ListPopupwindow.OnItemOnClickListener() {
            @Override
            public void onItemClick(PopupListBean item, int position) {
                mOrderByType=item.getId();
                viewPager.setCurrentItem(index);
                mFragment.loadSearch(mYarnType,mPriceType,mNeedleType,mOrderByType);
            }
        });
        // 给标题栏弹窗添加子类
        mOrderbyPopup.addAction(list);
    }

    private ListPopupwindow.OnItemOnClickListener onitemClick=new ListPopupwindow.OnItemOnClickListener() {
        @Override
        public void onItemClick(PopupListBean item, int position) {

        }
    };
    @OnClick({R.id.img_back})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.img_back:
                finish_Activity(SelectYarnActivity.this);
                break;
        }
    }
}
