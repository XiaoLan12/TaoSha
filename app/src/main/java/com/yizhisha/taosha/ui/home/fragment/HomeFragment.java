package com.yizhisha.taosha.ui.home.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xys.libzxing.zxing.activity.CaptureActivity;
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
import com.yizhisha.taosha.ui.home.activity.CreditLoanActivity;
import com.yizhisha.taosha.ui.home.activity.HotCommendActivity;
import com.yizhisha.taosha.ui.home.activity.ProductsCommendActivity;
import com.yizhisha.taosha.ui.home.activity.SearchActivity;
import com.yizhisha.taosha.ui.home.activity.SeckillActivityActivity;
import com.yizhisha.taosha.ui.home.activity.SelectYarnActivity;
import com.yizhisha.taosha.ui.home.activity.YarnActivity;
import com.yizhisha.taosha.ui.home.contract.HomeContract;
import com.yizhisha.taosha.ui.home.precenter.HomePresenter;
import com.yizhisha.taosha.utils.DensityUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.SpacesItemDecoration;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

import static android.app.Activity.RESULT_OK;

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
    @Bind(R.id.recycleview7)
    RecyclerView recycleview7;
    @Bind(R.id.ll_search)
    LinearLayout ll_search;
    @Bind(R.id.rl_banner)
    RelativeLayout rl_banner;
    @Bind(R.id.img_scan)
            ImageView img_scan;



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

    HomeYarnRecommendAdapter adapter7;
    List<IndexDeatailYarnBean> data7=new ArrayList<>();





    private static String TAG = HomeFragment.class.getSimpleName();
    // 语音听写对象
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.translucentStatusBar(getActivity(), true);
//            StatusBarCompat.setStatusBarColor(activity, Color.WHITE,125);

        }
    }
    @Override
    protected void initView() {
        StatusBarCompat.translucentStatusBar(getActivity(), true);
//        StatusBarCompat.setStatusBarColor(activity, Color.WHITE,125);

        //动态设置banner的高度
        RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) banner.getLayoutParams();
        int ss=DensityUtil.getScreenWidth(getActivity());
        linearParams.height = ss/2;// 控件的宽强制设成30
        banner.setLayoutParams(linearParams);

//        StatusBarCompat.translucentStatusBar(getActivity(), true);
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        banner.setIndicatorGravity(Banner.CENTER);
        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(true);
        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(5000);


        //recycleview_type
        //设置横向布局
        RecyclerView.LayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 5);

        recycleview_type.setLayoutManager(linearLayoutManager);
        //设置间隔includeEdge
//        int spanCount =  DensityUtil.dip2px(5);
//        int spacing = DensityUtil.dip2px(10);
//        Log.e("HHH",spanCount+"---"+spacing);
//        boolean includeEdge = true;
//        recycleview_type.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        homeYarnTypeEntityList = setData();
        homeYarnTypeAdapter = new HomeYarnTypeAdapter(homeYarnTypeEntityList);
        recycleview_type.setAdapter(homeYarnTypeAdapter);
        homeYarnTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putInt("YARNTYPE", 540);
                        startActivity(SelectYarnActivity.class, bundle);
                        break;
                    case 1:
                        Bundle bundle1 = new Bundle();
                        bundle1.putInt("YARNTYPE", 545);
                        startActivity(SelectYarnActivity.class, bundle1);
                        break;
                    case 2:
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("YARNTYPE", 544);
                        startActivity(SelectYarnActivity.class, bundle2);
                        break;
                    case 3:
                        Bundle bundle3 = new Bundle();
                        bundle3.putInt("YARNTYPE", 542);
                        startActivity(SelectYarnActivity.class, bundle3);
                        break;
                    case 4:
                        Bundle bundle4 = new Bundle();
                        bundle4.putInt("YARNTYPE", 539);
                        startActivity(SelectYarnActivity.class, bundle4);
                        break;
                    case 5:
                        Bundle bundle5=new Bundle();
                        bundle5.putInt("YARNTYPE",541);
                        startActivity(SelectYarnActivity.class,bundle5);
                        break;
                    case 6:
                        startActivity(HotCommendActivity.class);
                        break;
                    case 7:
                        startActivity(ProductsCommendActivity.class);
                        break;
                    case 8:
                        startActivity(SeckillActivityActivity.class);
                        break;
                    case 9:
                        startActivity(CreditLoanActivity.class);
                        break;
                }
            }
        });

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview1.setLayoutManager(linearLayoutManager1);
        //RecycleView 增加边距
        int spacingInPixels1 = 8;
        recycleview1.addItemDecoration(new SpacesItemDecoration(spacingInPixels1));

        adapter1 = new HomeYarnRecommendAdapter(data1);
        recycleview1.setAdapter(adapter1);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview2.setLayoutManager(linearLayoutManager2);
        int spacingInPixels2 = 8;
        recycleview2.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter2 = new HomeYarnRecommendAdapter(data2);
        recycleview2.setAdapter(adapter2);

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity());
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview3.setLayoutManager(linearLayoutManager3);
        recycleview3.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter3 = new HomeYarnRecommendAdapter(data3);
        recycleview3.setAdapter(adapter3);

        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getActivity());
        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview4.setLayoutManager(linearLayoutManager4);
        recycleview4.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter4 = new HomeYarnRecommendAdapter(data4);
        recycleview4.setAdapter(adapter4);

        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(getActivity());
        linearLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview5.setLayoutManager(linearLayoutManager5);
        recycleview5.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter5 = new HomeYarnRecommendAdapter(data5);
        recycleview5.setAdapter(adapter5);

        LinearLayoutManager linearLayoutManager6 = new LinearLayoutManager(getActivity());
        linearLayoutManager6.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview6.setLayoutManager(linearLayoutManager6);
        recycleview6.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter6 = new HomeYarnRecommendAdapter(data6);
        recycleview6.setAdapter(adapter6);

        LinearLayoutManager linearLayoutManager7 = new LinearLayoutManager(getActivity());
        linearLayoutManager7.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview7.setLayoutManager(linearLayoutManager7);
        recycleview7.addItemDecoration(new SpacesItemDecoration(spacingInPixels2));
        adapter7 = new HomeYarnRecommendAdapter(data7);
        recycleview7.setAdapter(adapter7);

        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", data1.get(position).getId());
                startActivity(YarnActivity.class, bundle);
            }
        });
        adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", data2.get(position).getId());
                startActivity(YarnActivity.class, bundle);
            }
        });
        adapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", data3.get(position).getId());
                startActivity(YarnActivity.class, bundle);
            }
        });
        adapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", data4.get(position).getId());
                startActivity(YarnActivity.class, bundle);
            }
        });
        adapter5.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", data5.get(position).getId());
                startActivity(YarnActivity.class, bundle);
            }
        });
        adapter6.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", data6.get(position).getId());
                startActivity(YarnActivity.class, bundle);
            }
        });
        adapter7.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", data7.get(position).getId());
                startActivity(YarnActivity.class, bundle);
            }
        });


        //首页轮播图
        mPresenter.getPPT();
        //首页推荐纺纱6种
        mPresenter.getRecmomendYarn();


    }

    private List<HomeYarnTypeEntity> setData(){
        List<HomeYarnTypeEntity> list=new ArrayList<>();
        String[] names= new String[] {
                "毛纺纱",
               "化纤纱","棉纺纱","花式纱","麻纺纱","混纺纱","热门推荐","精品推荐","秒纱","信用贷",};
        int[] imgs=new int[]{R.drawable.index_mao,R.drawable.index_huaxian,R.drawable.index_mian,R.drawable.index_huashi,R.drawable.index_ma,
                R.drawable.index_hun,R.drawable.index_remen,R.drawable.index_jingpin,R.drawable.index_miaosha,R.drawable.index_xinyongdai};
        for(int i=0;i<10;i++){
            HomeYarnTypeEntity homeYarnTypeEntity=new HomeYarnTypeEntity();
            homeYarnTypeEntity.setName(names[i]);
            homeYarnTypeEntity.setImg(imgs[i]);
            list.add(homeYarnTypeEntity);
        }
        return list;
    }
    //扫描二维码
    //https://cli.im/text?2dd0d2b267ea882d797f03abf5b97d88二维码生成网站
    public void scan() {
        // 扫描功能
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //申请CAMERA权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 3);
        } else {

            Intent intent=  new Intent(getActivity(), CaptureActivity.class);
            startActivityForResult(intent,0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result = bundle.getString("result");
//                searchEt.setText(result);

                if(result.contains("taoshamall")&&result.contains("id/")){
                    int i=result.indexOf("id/");
                    try{
                        Bundle bundle1=new Bundle();
                        bundle1.putInt("TYPE",1);
                        bundle1.putInt("id",Integer.parseInt(result.substring(i+3,result.length())));
                        startActivity(YarnActivity.class,bundle1);
                    }catch (Exception e){

                    }

                }else{
                    ToastUtil.showShortToast("尊敬的用户,扫码功能仅提供大朗淘纱商品二维码扫码");
                }
            }
        }
    }

    @OnClick({R.id.ll_search,R.id.img_scan})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ll_search:
//                startActivity(SearchActivity.class);
             /*   Bundle bundle4 = new Bundle();
                bundle4.putInt("YARNTYPE", 0);*/
                startActivity(SearchActivity.class);
                break;
            case R.id.img_scan:
                scan();
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
                view.setScaleType(ImageView.ScaleType.FIT_XY);
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
//                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_LONG).show();
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

        data5=model.getHuaxiansha();
        adapter5.setNewData(data5);

        data6=
        model.getHuashisha();
        adapter6.setNewData(data6);
//        Log.e("EEE",new Random().nextInt(5)+"---"+new Random().nextInt(5));
        data7.add(data1.get(new Random().nextInt(data1.size())));
        data7.add(data2.get(new Random().nextInt(data2.size())));
        data7.add(data3.get(new Random().nextInt(data3.size())));
        data7.add(data4.get(new Random().nextInt(data4.size())));
        data7.add(data5.get(new Random().nextInt(data1.size())));
        data7.add(data6.get(new Random().nextInt(data1.size())));
        adapter7.setNewData(data7);

    }

    @Override
    public void getRecommendFali(String msg) {

    }


}
