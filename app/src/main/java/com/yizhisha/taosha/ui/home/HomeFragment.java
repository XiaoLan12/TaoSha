package com.yizhisha.taosha.ui.home;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.HomeYarnRecommendAdapter;
import com.yizhisha.taosha.adapter.HomeYarnTypeAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.HomeYarnTypeEntity;
import com.yizhisha.taosha.ui.home.selectyarn.SelectYarnActivity;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.GridSpacingItemDecoration;
import com.yizhisha.taosha.widget.SpacesItemDecoration;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/6/22.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener{
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.recycleview_type)
    RecyclerView recycleview_type;
    @Bind(R.id.recycleview1)
    RecyclerView recycleview1;
    @Bind(R.id.recycleview2)
    RecyclerView recycleview2;
    @Bind(R.id.tv_search)
    TextView tv_search;


    //设置图片资源:url或本地资源
    String[] images= new String[] {
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};
    HomeYarnTypeAdapter homeYarnTypeAdapter;
    List<HomeYarnTypeEntity> homeYarnTypeEntityList=new ArrayList<>();

    HomeYarnRecommendAdapter adapter1;
    List<HomeYarnTypeEntity> data1=new ArrayList<>();

    HomeYarnRecommendAdapter adapter2;
    List<HomeYarnTypeEntity> data2=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.translucentStatusBar(getActivity(), true);
//            StatusBarCompat.setStatusBarColor(activity, Color.GREEN);
        }
    }
    @Override
    protected void initView() {
        StatusBarCompat.translucentStatusBar(getActivity(), true);

        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        banner.setIndicatorGravity(Banner.RIGHT);
        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(true);
        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(5000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //自定义图片加载框架
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                System.out.println("加载中");
                Glide.with(getActivity()).load(url).into(view);
                System.out.println("加载完");
            }
        });
        //设置点击事件，下标是从1开始
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {
                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });

        //recycleview_type
        //设置横向布局
        RecyclerView.LayoutManager linearLayoutManager=new GridLayoutManager(getActivity(),5);

        recycleview_type.setLayoutManager(linearLayoutManager);
        //设置间隔includeEdge
        int spanCount = 5;
        int spacing = 50;
        boolean includeEdge = true;
        recycleview_type.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        homeYarnTypeEntityList=setData();
        homeYarnTypeAdapter=new HomeYarnTypeAdapter(homeYarnTypeEntityList);
        recycleview_type.setAdapter(homeYarnTypeAdapter);
        homeYarnTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(SelectYarnActivity.class);
                ToastUtil.showShortToast(position+1+"");
            }
        });

     LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview1.setLayoutManager(linearLayoutManager1);
        //RecycleView 增加边距
        int spacingInPixels = 8;
        recycleview1.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        data1 =seData1();
        adapter1=new HomeYarnRecommendAdapter(data1);
        recycleview1.setAdapter(adapter1);

        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview2.setLayoutManager(linearLayoutManager2);
        int spacingInPixels1 = 8;
        recycleview2.addItemDecoration(new SpacesItemDecoration(spacingInPixels1));
        adapter2=new HomeYarnRecommendAdapter(data1);
        recycleview2.setAdapter(adapter2);




    }
    private List<HomeYarnTypeEntity> seData1(){
        List<HomeYarnTypeEntity> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            HomeYarnTypeEntity homeYarnTypeEntity=new HomeYarnTypeEntity();
            homeYarnTypeEntity.setImg(R.drawable.index_rc_1);
            homeYarnTypeEntity.setName("第"+i);
            list.add(homeYarnTypeEntity);
        }
        return list;
    }

    private List<HomeYarnTypeEntity> setData(){
        List<HomeYarnTypeEntity> list=new ArrayList<>();
        String[] names= new String[] {
                "毛纺纱",
               "化纤纱","棉纺纱","花式纱","麻纺纱","混纺纱","快递信息","秒纱活动","信用贷","会员登录","会员登录"};
        int[] imgs=new int[]{R.drawable.index_mao,R.drawable.index_huaxian,R.drawable.index_mian,R.drawable.index_huashi,R.drawable.index_ma,
                R.drawable.index_hun,R.drawable.index_jingpin,R.drawable.index_miaosha,R.drawable.index_xinyongdai,R.drawable.index_remen,R.drawable.index_remen};
        for(int i=0;i<10;i++){
            HomeYarnTypeEntity homeYarnTypeEntity=new HomeYarnTypeEntity();
            homeYarnTypeEntity.setName(names[i]);
            homeYarnTypeEntity.setImg(imgs[i]);
            list.add(homeYarnTypeEntity);
        }
        return list;
    }

    @OnClick({R.id.tv_search})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_search:
                startActivity(SearchActivity.class);
                break;
        }
    }
}
