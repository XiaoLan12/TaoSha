package com.yizhisha.taosha.ui.welcome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.utils.SharedPreferencesUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/27 0027.
 */

public class WeclomeActivity extends AppCompatActivity {
    @Bind(R.id.vp)
    ViewPager viewPager;
    @Bind(R.id.radio_group)
    RadioGroup radio_group;
    @Bind(R.id.radio_button1)
    RadioButton radio_button1;
    @Bind(R.id.radio_button2)
    RadioButton radio_button2;
    @Bind(R.id.radio_button3)
    RadioButton radio_button3;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
  /*  @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_welcome);
        //添加注解
        ButterKnife.bind(this);
        initView();

    }

   /* @Override
    protected void initToolBar() {

    }*/


    protected void initView() {


        mFragments.add(WelcomeFragment.getInstance(1));
        mFragments.add(WelcomeFragment.getInstance(2));
        mFragments.add(WelcomeFragment.getInstance(3));
        mAdapter = new FragmentPagerAdapter(WeclomeActivity.this.getSupportFragmentManager()) {
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

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //这里做操作
            }
        });
        // 对ViewPager设置滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radio_group.check(position);
                if(position==0){
                    radio_button1.setChecked(true);
                }else if(position==1){
                    radio_button2.setChecked(true);
                }
                else if(position==2){
                    radio_button3.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

            SharedPreferencesUtil.putValue(WeclomeActivity.this,"WELCOME","yes");

    }
}
