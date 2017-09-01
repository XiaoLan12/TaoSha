package com.yizhisha.taosha.ui.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.MyOrderTabEntity;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.ui.home.contract.YarnContract;
import com.yizhisha.taosha.ui.home.fragment.DetailsYarnFragment;
import com.yizhisha.taosha.ui.home.fragment.ParameterYarnFragment;
import com.yizhisha.taosha.ui.home.fragment.ProductYarnFragnment;
import com.yizhisha.taosha.ui.home.fragment.SekaFragment;
import com.yizhisha.taosha.ui.home.precenter.YarnPresenter;
import com.yizhisha.taosha.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.taosha.ui.login.activity.RegisterActivity;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/2.
 */

public class YarnActivity extends BaseActivity<YarnPresenter> implements
        YarnContract.View {
    @Bind(R.id.commontablayout)
    CommonTabLayout commonTabLayout;

    private String[] mTitles = {"产品", "参数", "色卡","详情"};
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
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.nayang_ll)
    LinearLayout nayangLl;
    @Bind(R.id.banmao_ll)
    LinearLayout banmaoLl;

    private FragmentPagerAdapter mAdapter;
    private int id;
    private int type;
    private ProductDetailBean productDetailBean;
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
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            type=bundle.getInt("TYPE",1);
            if(type==1) {//普通商品
                id = bundle.getInt("id");
                Map map = new HashMap<>();
                map.put("id", String.valueOf(id));
                if (AppConstant.UID != 0) {
                    map.put("uid", String.valueOf(AppConstant.UID));
                }
                mPresenter.getProductDetail(map);
            }else if(type==2){//精品
                String url=bundle.getString("URL");
                String mUrl="http://www.taoshamall.com"+url;
                mPresenter.loadProductCommend(mUrl);
            }
        }


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
    }

    @OnClick({R.id.img_back,R.id.tv_shopping_cart,R.id.tv_shopping,R.id.nayang_ll,
    R.id.banmao_ll,R.id.call_us_ll})
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
                bundle.putBoolean("ISBANMAO",false);
                bundle.putSerializable("DATA",productDetailBean.getGoods());
                startActivity(SelectYarnColorActivity.class,bundle);
                break;
            case R.id.tv_shopping:
                Bundle bundle1=new Bundle();
                bundle1.putInt("TYPE",1);
                bundle1.putBoolean("ISBANMAO",false);
                bundle1.putSerializable("DATA",productDetailBean.getGoods());
                startActivity(SelectYarnColorActivity.class,bundle1);
                break;
            case R.id.nayang_ll:
                if(AppConstant.isLogin==false){
                    final List<String> mDatas1=new ArrayList<>();
                    mDatas1.add("登录");
                    mDatas1.add("注册");
                    NormalSelectionDialog dialog=new NormalSelectionDialog.Builder(this)
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
                    return;
                }
                Bundle bundle2=new Bundle();
                bundle2.putInt("ORDERTYPE",4);

                bundle2.putInt("gid",productDetailBean.getGoods().getId());
                startActivity(SureOrderActivity.class,bundle2);
                break;
            case R.id.banmao_ll:
                Bundle bundle3=new Bundle();
                bundle3.putInt("TYPE",1);
                bundle3.putBoolean("ISBANMAO",true);
                bundle3.putSerializable("DATA",productDetailBean.getGoods());
                startActivity(SelectYarnColorActivity.class,bundle3);
                break;
            case R.id.call_us_ll:
                new NormalAlertDialog.Builder(this)
                        .setBoolTitle(false)
                        .setContentText("0769-83115811")
                        .setContentTextSize(18)
                        .setLeftText("取消")
                        .setRightText("确认")
                        .setWidth(0.75f)
                        .setHeight(0.33f)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(NormalAlertDialog dialog, View view) {
                                Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + "0769-83115811"));
                                startActivity(phoneIneten);
                                dialog.dismiss();

                            }
                        }).build().show();
                break;
        }
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
        productDetailBean=null;
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void getProductDetailFail(String msg) {
        productDetailBean=null;
        mLoadingView.loadError();

        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                Map map = new HashMap<>();
                map.put("id", String.valueOf(id));
                if (AppConstant.UID != 0) {
                    map.put("uid", String.valueOf(AppConstant.UID));
                }
                mPresenter.getProductDetail(map);
            }
        });
    }
    @Override
    public void getProductDetailSuccess(ProductDetailBean model) {
        productDetailBean=model;
        id=model.getGoods().getId();
        if(productDetailBean.getGoods().getIs_banmao()==1){
            banmaoLl.setVisibility(View.VISIBLE);
        }else{
            nayangLl.setVisibility(View.GONE);
        }
        if(productDetailBean.getGoods().getIs_nayang()==1){
            nayangLl.setVisibility(View.VISIBLE);
        }else{
            nayangLl.setVisibility(View.GONE);
        }
        mFragments.add(ProductYarnFragnment.getInstance(id,productDetailBean));
        mFragments.add(ParameterYarnFragment.getInstance(2,null,productDetailBean.getGoods()));
        mFragments.add(SekaFragment.getInstance(productDetailBean.getGoods().getSeka()));
        mFragments.add(DetailsYarnFragment.getInstance(productDetailBean.getGoods().getContent_()));
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
    }
}
