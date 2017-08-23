package com.yizhisha.taosha.ui.home.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.MyOrderTabEntity;
import com.yizhisha.taosha.bean.json.SeckillProductBean;
import com.yizhisha.taosha.event.SecKillEvent;
import com.yizhisha.taosha.ui.home.contract.SeckillProductContract;
import com.yizhisha.taosha.ui.home.fragment.DetailsYarnFragment;
import com.yizhisha.taosha.ui.home.fragment.ParameterYarnFragment;
import com.yizhisha.taosha.ui.home.fragment.SeckillProductYarnFragment;
import com.yizhisha.taosha.ui.home.fragment.SekaFragment;
import com.yizhisha.taosha.ui.home.precenter.SeckillProductPresenter;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SeckillYarnActivity extends BaseActivity<SeckillProductPresenter>
        implements SeckillProductContract.View{
    @Bind(R.id.commontablayout)
    CommonTabLayout commonTabLayout;
    private String[] mTitles = {"产品", "参数", "色卡","详情"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Bind(R.id.vp)
    ViewPager viewPager;
    @Bind(R.id.img_back)
    ImageView img_back;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.tv_shopping)
    TextView shoppTv;
    private FragmentPagerAdapter mAdapter;
    private SeckillProductBean seckillProductBean;

    private Subscription subscription;

    private int id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_seckillproduct;
    }

    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(this, this.getResources().getColor(R.color.gray1));
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            id=bundle.getInt("id");
        }
        Map map = new HashMap<>();
        map.put("id", String.valueOf(id));
        if (AppConstant.UID != 0) {
            map.put("uid", String.valueOf(AppConstant.UID));
        }
        mPresenter.loadSeckillProduct(map);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new MyOrderTabEntity(mTitles[i]));
        }
        commonTabLayout.setTabData(mTabEntities);
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
        event();
    }
    private void event(){
        subscription= RxBus.$().toObservable(SecKillEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SecKillEvent>() {
                    @Override
                    public void call(SecKillEvent event) {
                     switch (event.getState()){
                         case 1:
                             shoppTv.setText("活动未开始");
                             shoppTv.setEnabled(false);
                             break;
                         case 2:
                             shoppTv.setText("立即抢购");
                             shoppTv.setEnabled(true);
                             break;
                         case 3:
                             shoppTv.setText("活动已结束");
                             shoppTv.setEnabled(false);
                             break;
                     }
                    }
                });
    }
    @Override
    public void loadSuccess(SeckillProductBean model) {
        seckillProductBean=model;
        mFragments.add(SeckillProductYarnFragment.getInstance(seckillProductBean));
        mFragments.add(ParameterYarnFragment.getInstance(1,seckillProductBean.getGoods(),null));
        mFragments.add(SekaFragment.getInstance(seckillProductBean.getGoods().getSeka()));
        mFragments.add(DetailsYarnFragment.getInstance(seckillProductBean.getGoods().getContent()));
        mAdapter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {
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
    }

    @Override
    public void showLoading() {
        mLoadingView.load();
    }

    @Override
    public void hideLoading() {
        mLoadingView.loadSuccess();
    }

    @Override
    public void showEmpty() {
        seckillProductBean=null;
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(String msg) {
        seckillProductBean=null;
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                Map map = new HashMap<>();
                map.put("id", String.valueOf(id));
                if (AppConstant.UID != 0) {
                    map.put("uid", String.valueOf(AppConstant.UID));
                }
                mPresenter.loadSeckillProduct(map);
            }
        });
    }
    @OnClick({R.id.img_back,R.id.tv_shopping})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish_Activity(SeckillYarnActivity.this);
                break;
            case R.id.tv_shopping:
                int id=0;
                if(seckillProductBean!=null){
                    id=seckillProductBean.getSeckilling().getId();
                }
                Bundle bundle=new Bundle();
                bundle.putInt("ORDERTYPE",3);
                bundle.putInt("id",id);
                startActivity(SureOrderActivity.class,bundle);
                break;
        }
    } @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null&&!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
