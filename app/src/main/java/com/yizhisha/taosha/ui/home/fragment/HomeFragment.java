package com.yizhisha.taosha.ui.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.HomeYarnRecommendAdapter;
import com.yizhisha.taosha.adapter.HomeYarnTypeAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.HomeYarnTypeEntity;
import com.yizhisha.taosha.bean.json.IndexDeatailYarnBean;
import com.yizhisha.taosha.bean.json.IndexImgBean;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;
import com.yizhisha.taosha.ui.home.activity.SearchActivity;
import com.yizhisha.taosha.ui.home.activity.SelectYarnActivity;
import com.yizhisha.taosha.ui.home.activity.YarnActivity;
import com.yizhisha.taosha.ui.home.contract.HomeContract;
import com.yizhisha.taosha.ui.home.precenter.HomePresenter;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.SpacesItemDecoration;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/6/22.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, View.OnClickListener{
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.recycleview_type)
    RecyclerView recycleview_type;
    @Bind(R.id.recycleview1)
    RecyclerView recycleview1;
    @Bind(R.id.recycleview2)
    RecyclerView recycleview2;
    @Bind(R.id.recycleview3)
    RecyclerView recycleview3;
    @Bind(R.id.recycleview4)
    RecyclerView recycleview4;
    @Bind(R.id.recycleview5)
    RecyclerView recycleview5;
    @Bind(R.id.recycleview6)
    RecyclerView recycleview6;
    @Bind(R.id.tv_search)
    TextView tv_search;


    //设置图片资源:url或本地资源
    String[] images= new String[] {};
//     "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
//             "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
//             "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"
    HomeYarnTypeAdapter homeYarnTypeAdapter;
    List<HomeYarnTypeEntity> homeYarnTypeEntityList=new ArrayList<>();

    HomeYarnRecommendAdapter adapter1;
    List<IndexDeatailYarnBean> data1=new ArrayList<>();

    HomeYarnRecommendAdapter adapter2;
    List<IndexDeatailYarnBean> data2=new ArrayList<>();

    HomeYarnRecommendAdapter adapter3;
    List<IndexDeatailYarnBean> data3=new ArrayList<>();

    HomeYarnRecommendAdapter adapter4;
    List<IndexDeatailYarnBean> data4=new ArrayList<>();

    HomeYarnRecommendAdapter adapter5;
    List<IndexDeatailYarnBean> data5=new ArrayList<>();

    HomeYarnRecommendAdapter adapter6;
    List<IndexDeatailYarnBean> data6=new ArrayList<>();



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



        //recycleview_type
        //设置横向布局
        RecyclerView.LayoutManager linearLayoutManager=new GridLayoutManager(getActivity(),5);

        recycleview_type.setLayoutManager(linearLayoutManager);
        //设置间隔includeEdge
//        int spanCount =  DensityUtil.dip2px(5);
//        int spacing = DensityUtil.dip2px(10);
//        Log.e("HHH",spanCount+"---"+spacing);
//        boolean includeEdge = true;
//        recycleview_type.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
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
        int spacingInPixels1 = 8;
        recycleview1.addItemDecoration(new SpacesItemDecoration(spacingInPixels1));

        adapter1=new HomeYarnRecommendAdapter(data1);
        recycleview1.setAdapter(adapter1);

        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview2.setLayoutManager(linearLayoutManager2);
        int spacingInPixels2 = 8;
        recycleview2.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter2=new HomeYarnRecommendAdapter(data2);
        recycleview2.setAdapter(adapter2);

        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(getActivity());
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview3.setLayoutManager(linearLayoutManager3);
        recycleview3.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter3=new HomeYarnRecommendAdapter(data3);
        recycleview3.setAdapter(adapter3);

        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(getActivity());
        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview4.setLayoutManager(linearLayoutManager4);
        recycleview4.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter4=new HomeYarnRecommendAdapter(data4);
        recycleview4.setAdapter(adapter4);

        LinearLayoutManager linearLayoutManager5=new LinearLayoutManager(getActivity());
        linearLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview5.setLayoutManager(linearLayoutManager5);
        recycleview5.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter5=new HomeYarnRecommendAdapter(data5);
        recycleview5.setAdapter(adapter5);

        LinearLayoutManager linearLayoutManager6=new LinearLayoutManager(getActivity());
        linearLayoutManager6.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview6.setLayoutManager(linearLayoutManager6);
        recycleview6.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter6=new HomeYarnRecommendAdapter(data6);
        recycleview6.setAdapter(adapter6);

        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(YarnActivity.class);
            }
        });
        adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(YarnActivity.class);
            }
        });
        adapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(YarnActivity.class);
            }
        });
        adapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(YarnActivity.class);
            }
        });
        adapter5.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(YarnActivity.class);
            }
        });
        adapter6.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(YarnActivity.class);
            }
        });


        Map<String,String> map=new HashMap<>();
        //首页轮播图
        mPresenter.getPPT(map);
        //首页推荐纺纱6种
        mPresenter.getRecmomendYarn(map);
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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void loadFail(String msg) {

    }

    @Override
    public void getPPTSuccess(IndexPPTBean model) {

        List<IndexImgBean> imgs=new ArrayList<>();
       imgs=model.getPpt();
        List<String> imgss=new ArrayList<>();
        for (int i=0;i<imgs.size();i++){
            imgss.add(AppConstant.INDEX_BANK_IMG_URL+imgs.get(i).getImg());
        }
        Log.e("TTT","--"+imgss.toString());

        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        banner.setImages(imgss, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(getActivity()).load(url)
//                        .signature(new StringSignature(UUID.randomUUID().toString()))  // 重点在这行
                        .diskCacheStrategy( DiskCacheStrategy.NONE )//禁用磁盘缓存
                        .skipMemoryCache( true )//跳过内存缓存
                        .into(view);
            }
        });
        //设置点击事件，下标是从1开始
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {
                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void getPPTFail(String msg) {

    }

    @Override
    public void getRecommendSuccess(IndexRecommendYarnBean model) {
//        mafangsha;
//        private List<IndexDeatailYarnBean> maofangsha;
//        private List<IndexDeatailYarnBean>  mianfangsha;
//        private List<IndexDeatailYarnBean> hunfangsha;
//        private List<IndexDeatailYarnBean> huashisha;
//        private List<IndexDeatailYarnBean> huaxiansha;
        data1=model.getMafangsha();
        adapter1.setNewData(data1);
        Log.e("TTT","-"+data1.toString());
        data2=model.getMaofangsha();
        adapter2.setNewData(data2);

        data3=model.getMianfangsha();
        adapter3.setNewData(data3);

        data4=model.getHunfangsha();
        adapter4.setNewData(data4);

        data5=model.getHuashisha();
        adapter5.setNewData(data5);

        data6=model.getHuaxiansha();
        adapter6.setNewData(data6);

    }

    @Override
    public void getRecommendFali(String msg) {

    }
}
