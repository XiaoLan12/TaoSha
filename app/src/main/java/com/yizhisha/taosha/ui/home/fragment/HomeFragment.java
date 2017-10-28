package com.yizhisha.taosha.ui.home.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;
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
import com.yizhisha.taosha.bean.json.ListGoodsBean;
import com.yizhisha.taosha.common.dialog.LoadingDialog;
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
import com.yizhisha.taosha.utils.xunfei.IatSettings;
import com.yizhisha.taosha.utils.xunfei.JsonParser;
import com.yizhisha.taosha.widget.SpacesItemDecoration;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
   @Bind(R.id.img_tiao_search)
   ImageView img_tiao_search;
    @Bind(R.id.rl_banner)
    RelativeLayout rl_banner;


//    @Bind(R.id.loadingView)
//    CommonLoadingView mLoadingView;

    @Bind(R.id.img_mafang)
            ImageView img_mafang;
    @Bind(R.id.img_maofang)
    ImageView img_maofang;
    @Bind(R.id.img_mianfang)
    ImageView img_mianfang;
    @Bind(R.id.img_hunfang)
    ImageView img_hunfang;
    @Bind(R.id.img_hauxian)
    ImageView img_hauxian;
    @Bind(R.id.img_huashi)
    ImageView img_huashi;
    @Bind(R.id.img_remen)
    ImageView img_remen;

    @Bind(R.id.img_daily)
    ImageView img_daily;
    @Bind(R.id.img_nayang)
    ImageView img_nayang;
    @Bind(R.id.img_banmao)
    ImageView img_banmao;

    @Bind(R.id.img_index_daohang)
    ImageView img_index_daohang;

    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.tv_search)
    TextView tv_search;
    @Bind(R.id.img_scan)
    ImageView img_scan;

    private LoadingDialog mLoadingDialog;
    private String nayang="";
    private String daily="";
    private String bannao="";




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
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private SharedPreferences mSharedPreferences;

    private Toast mToast;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    private boolean mTranslateEnable = false;

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

        if(mLoadingDialog!=null){
            mLoadingDialog.cancelDialog();
        }
//        StatusBarCompat.setStatusBarColor(activity, Color.WHITE,125);
        int ss=DensityUtil.getScreenWidth(getActivity());
        //动态设置banner的高度
        RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) banner.getLayoutParams();

        linearParams.height = ss/2;// 控件的宽强制设成30
        banner.setLayoutParams(linearParams);


        LinearLayout.LayoutParams linearParam1 =(LinearLayout.LayoutParams) img_remen.getLayoutParams();
        linearParam1.height = ss/2;// 控件的宽强制设成30
        img_remen.setLayoutParams(linearParam1);
        img_mafang.setLayoutParams(linearParam1);
        img_mianfang.setLayoutParams(linearParam1);
        img_hunfang.setLayoutParams(linearParam1);
        img_hauxian.setLayoutParams(linearParam1);
        img_huashi.setLayoutParams(linearParam1);
        img_maofang.setLayoutParams(linearParam1);


        LinearLayout.LayoutParams linearParam2 =(LinearLayout.LayoutParams) img_index_daohang.getLayoutParams();
        linearParam2.height = ss/12;
        img_index_daohang.setLayoutParams(linearParam2);

       /* LinearLayout.LayoutParams linearParam3 =(LinearLayout.LayoutParams) img_daily.getLayoutParams();
        linearParam3.height = ss/2;
        img_daily.setLayoutParams(linearParam3);*/

      /*  LinearLayout.LayoutParams linearParam4 =(LinearLayout.LayoutParams) img_nayang.getLayoutParams();
        linearParam4.height = ss/4;
        img_daily.setLayoutParams(linearParam4);

        img_banmao.setLayoutParams(linearParam4);
*/
//        img_mafang.setLayoutParams(linearParam1);

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


        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {

                searchEt.setSelection(searchEt.getText().length());
                if(s.length()==0){
                    tv_search.setVisibility(View.GONE);
                    img_scan.setVisibility(View.VISIBLE);
                }else{
                    tv_search.setVisibility(View.VISIBLE);
                    img_scan.setVisibility(View.GONE);
                }
            }
        });
//        tv_search.setFocusable(true);
//        tv_search.setFocusableInTouchMode(true);
//        focusableInTouchMode


        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(getActivity(), mInitListener);
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(getActivity(), mInitListener);
        mToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
        mSharedPreferences = getActivity().getSharedPreferences(IatSettings.PREFER_NAME,
                Activity.MODE_PRIVATE);

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
            requestPermissions( new String[]{Manifest.permission.CAMERA}, 3 );
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 3);
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

    @OnClick({R.id.img_tiao_search,R.id.img_scan,R.id.img_nayang,R.id.img_daily,R.id.img_banmao,R.id.voice_iv,R.id.tv_search})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.img_tiao_search:
//                startActivity(SearchActivity.class);
             /*   Bundle bundle4 = new Bundle();
                bundle4.putInt("YARNTYPE", 0);*/
                startActivity(SearchActivity.class);
                break;
            case R.id.img_scan:
                scan();
                break;
            case R.id.img_nayang:
                mPresenter.getListGoodsNaYang("nayang");


                break;

            case R.id.img_daily:
                mPresenter.getListGoodsDaily("daily");
               /* if(daily.equals("")){
                    ToastUtil.showShortToast("网络错误或者系统繁忙");
                }else{

                }*/

                break;
            case R.id.img_banmao:
                mPresenter.getListGoodsBannao("banmao");


                break;

            case R.id.tv_search:
                Bundle bundle1 = new Bundle();
                bundle1.putString("KEY", searchEt.getText().toString().trim());
                startActivity(SelectYarnActivity.class, bundle1);
                break;
            case R.id.voice_iv:
                //⑧申请录制音频的动态权限
                if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
              requestPermissions( new String[]{
                            android.Manifest.permission.RECORD_AUDIO}, 1);
                } else {
                    startRecord();
                }
                break;
        }
    }

    @Override
    public void showLoading() {
        mLoadingDialog=new LoadingDialog(activity,"加载中",false);
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if(mLoadingDialog!=null){
            mLoadingDialog.cancelDialog();
        }

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

        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getMafang().get(0).getImg()).into(img_mafang);
        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getMaofang().get(0).getImg()).into(img_maofang);
        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getMianfang().get(0).getImg()).into(img_mianfang);
        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getHunfang().get(0).getImg()).into(img_hunfang);
        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getHauxian().get(0).getImg()).into(img_hauxian);
        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getHuashi().get(0).getImg()).into(img_huashi);
        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getRemen().get(0).getImg()).into(img_remen);//.placeholder(R.drawable.icon_glide_loading).error(R.drawable.icon_glide_failed).
        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getMeiri().get(0).getImg()).into(img_daily);
        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getNayang().get(0).getImg()).into(img_nayang);
        Glide.with(getActivity()).load(AppConstant.INDEX_BANK_IMG_URL+model.getBanmao().get(0).getImg()).into(img_banmao);
//        GlideUtil.getInstance().LoadContextBitmap();
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

    @Override
    public void getListGoodsDailySuccess(ListGoodsBean model) {
//        Log.e("TTT","-3-"+model.getGoods());
//        daily=model.getGoods();
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE",1);
        bundle.putInt("id", Integer.parseInt(model.getGoods()));
        startActivity(YarnActivity.class, bundle);
    }

    @Override
    public void getListGoodsNaYangSuccess(ListGoodsBean model) {
//        Log.e("TTT","-2-"+model.getGoods());
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE",1);
        bundle.putInt("id", Integer.parseInt(model.getGoods()));
        startActivity(YarnActivity.class, bundle);
    }

    @Override
    public void getListGoodsBannaoSuccess(ListGoodsBean model) {
//        Log.e("TTT","-1-"+model.getGoods());
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE",1);
        bundle.putInt("id", Integer.parseInt(model.getGoods()));
        startActivity(YarnActivity.class, bundle);
    }

    @Override
    public void getListGoodsFail(String msg) {
        ToastUtil.showShortToast(msg);
    }

















    int ret = 0; // 函数调用返回值
    private void startRecord(){

        // 移动数据分析，收集开始听写事件
        FlowerCollector.onEvent(getActivity(), "iat_recognize");

        mIatResults.clear();
        // 设置参数
        setParam();
        setParam();
        boolean isShowDialog = mSharedPreferences.getBoolean(
                getString(R.string.pref_key_iat_show), true);
        if (isShowDialog) {
            // 显示听写对话框
            mIatDialog.setListener(mRecognizerDialogListener);
            mIatDialog.show();
            showTip(getString(R.string.text_begin));
        } else {
            // 不显示听写对话框
            ret = mIat.startListening(mRecognizerListener);
            if (ret != ErrorCode.SUCCESS) {
                showTip("听写失败,错误码：" + ret);
            } else {
                showTip(getString(R.string.text_begin));
            }
        }
    }
    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            }
        }
    };

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            showTip("开始说话");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
            if(mTranslateEnable && error.getErrorCode() == 14002) {
                showTip( error.getPlainDescription(true)+"\n请确认是否已开通翻译功能" );
            } else {
                showTip(error.getPlainDescription(true));
            }
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            showTip("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            if( mTranslateEnable ){
                printTransResult( results );
            }else{
                printResult(results);
            }

            if (isLast) {
                // TODO 最后的结果
            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            showTip("当前正在说话，音量大小：" + volume);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        searchEt.setText(resultBuffer.toString());
//        Toast.makeText(SelectYarnActivity.this,resultBuffer.toString(),Toast.LENGTH_SHORT).show();
//        mResultText.setText(resultBuffer.toString());
//        mResultText.setSelection(mResultText.length());
    }

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {
            if( mTranslateEnable ){
                printTransResult( results );
            }else{
                printResult(results);
            }

        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            if(mTranslateEnable && error.getErrorCode() == 14002) {
                showTip( error.getPlainDescription(true)+"\n请确认是否已开通翻译功能" );
            } else {
                showTip(error.getPlainDescription(true));
            }
        }

    };



    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    /**
     * 参数设置
     *
     * @paramparam
     * @return
     */
    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

        this.mTranslateEnable = mSharedPreferences.getBoolean( this.getString(R.string.pref_key_translate), false );
        if( mTranslateEnable ){
            Log.i( TAG, "translate enable" );
            mIat.setParameter( SpeechConstant.ASR_SCH, "1" );
            mIat.setParameter( SpeechConstant.ADD_CAP, "translate" );
            mIat.setParameter( SpeechConstant.TRS_SRC, "its" );
        }

        String lag = mSharedPreferences.getString("iat_language_preference",
                "mandarin");
        if (lag.equals("en_us")) {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
            mIat.setParameter(SpeechConstant.ACCENT, null);

            if( mTranslateEnable ){
                mIat.setParameter( SpeechConstant.ORI_LANG, "en" );
                mIat.setParameter( SpeechConstant.TRANS_LANG, "cn" );
            }
        } else {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, lag);

            if( mTranslateEnable ){
                mIat.setParameter( SpeechConstant.ORI_LANG, "cn" );
                mIat.setParameter( SpeechConstant.TRANS_LANG, "en" );
            }
        }

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, mSharedPreferences.getString("iat_vadbos_preference", "4000"));

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, mSharedPreferences.getString("iat_vadeos_preference", "1000"));

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, mSharedPreferences.getString("iat_punc_preference", "0"));

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
    }

    private void printTransResult (RecognizerResult results) {
        String trans  = JsonParser.parseTransResult(results.getResultString(),"dst");
        String oris = JsonParser.parseTransResult(results.getResultString(),"src");

        if( TextUtils.isEmpty(trans)|| TextUtils.isEmpty(oris) ){
            showTip( "解析结果失败，请确认是否已开通翻译功能。" );
        }else{
//            mResultText.setText( "原始语言:\n"+oris+"\n目标语言:\n"+trans );
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if( null != mIat ){
            // 退出时释放连接
            mIat.cancel();
            mIat.destroy();
        }
    }

    @Override
    public void onResume() {
        // 开放统计 移动数据统计分析
        FlowerCollector.onResume(getActivity());
        FlowerCollector.onPageStart(TAG);
        super.onResume();

    }

    @Override
    public void onPause() {
        // 开放统计 移动数据统计分析
        FlowerCollector.onPageEnd(TAG);
        FlowerCollector.onPause(getActivity());
        super.onPause();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            startRecord();
        }else {
            if (1 == requestCode) {
                Toast.makeText(getActivity(),"用户拒绝了权限",Toast.LENGTH_SHORT).show();
            }

        }

        if (3 == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent=  new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,0);
            } else {
                // 未授权
            }
        }

    }

}
