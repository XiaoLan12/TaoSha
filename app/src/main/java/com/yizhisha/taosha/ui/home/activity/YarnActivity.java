package com.yizhisha.taosha.ui.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.MyOrderTabEntity;
import com.yizhisha.taosha.ui.home.fragment.DetailsYarnFragment;
import com.yizhisha.taosha.ui.home.fragment.ParameterYarnFragment;
import com.yizhisha.taosha.ui.home.fragment.ProductYarnFragnment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/2.
 */

public class YarnActivity extends BaseActivity {
    @Bind(R.id.commontablayout)
    CommonTabLayout commonTabLayout;
    private String[] mTitles = {"产品", "参数", "详情"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Bind(R.id.vp)
    ViewPager viewPager;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.tv_shopping_cart)
    TextView tv_shopping_cart;
    @Bind(R.id.tv_shopping)
    TextView tv_shopping;

    private FragmentPagerAdapter mAdapter;
    private int id;
//    private DetailsYarnFragment detailsYarnFragment=null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yarn;
    }

    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(YarnActivity.this, this.getResources().getColor(R.color.gray1));
        AppConstant.productDetailBean=null;

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            id=bundle.getInt("id");
        }
        mFragments.add(ProductYarnFragnment.getInstance(id));
        mFragments.add(new ParameterYarnFragment());
        mFragments.add(new DetailsYarnFragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new MyOrderTabEntity(mTitles[i]));
        }
        commonTabLayout.setTabData(mTabEntities);
//

        mAdapter = new FragmentPagerAdapter(YarnActivity.this.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
             /*   if(detailsYarnFragment!=null&&position==1){
                    detailsYarnFragment.isVisible();
                }*/
                return mFragments.get(position);
            }
        };
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(5);

        // 对ViewPager设置滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            int lastPosition;

            @Override
            public void onPageSelected(int position) {
                // 页面被选中

                // 修改position
                position = position % mFragments.size();

                // 替换位置
                lastPosition = position;
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //导航栏设置监听
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                commonTabLayout.setCurrentTab(position);
                viewPager.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick({R.id.img_back,R.id.tv_shopping_cart,R.id.tv_shopping})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish_Activity(YarnActivity.this);
                break;
            case R.id.tv_shopping_cart:
                Bundle bundle=new Bundle();
                bundle.putInt("TYPE",2);
                startActivity(SelectYarnColorActivity.class,bundle);
                break;
            case R.id.tv_shopping:
                Bundle bundle1=new Bundle();
                bundle1.putInt("TYPE",1);
                startActivity(SelectYarnColorActivity.class,bundle1);
                break;
        }
    }
}
