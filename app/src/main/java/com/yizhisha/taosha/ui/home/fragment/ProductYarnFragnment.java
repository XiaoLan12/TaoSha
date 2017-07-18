package com.yizhisha.taosha.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yizhisha.taosha.App;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.bean.json.ProductDetailImg;
import com.yizhisha.taosha.ui.home.contract.ProductYarnContract;
import com.yizhisha.taosha.ui.home.precenter.ProductYarnPresenter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/2.
 */

public class ProductYarnFragnment extends BaseFragment<ProductYarnPresenter> implements ProductYarnContract.View {
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_privce)
    TextView tv_price;
    @Bind(R.id.tv_price_real)
    TextView tv_price_real;
    @Bind(R.id.tv_company)
    TextView tv_company;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;


    private ProductDetailImgAdapter adapter;
    private String id="";


    public static ProductYarnFragnment getInstance(String yarnType) {
        ProductYarnFragnment sf = new ProductYarnFragnment();
        sf.id=yarnType;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yarn_product;
    }

    @Override
    protected void initView() {
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        banner.setIndicatorGravity(Banner.CENTER);
        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(false);
        //设置轮播图片间隔时间（不设置默认为2000）
//        banner.setDelayTime(5000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(getActivity());
        linearLayoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager4);

        if(!id.equals("")){
            Map map=new HashMap<>();
            map.put("id",id);
            mPresenter.getProductDetail(map);
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
    public void getProductDetailFail(String msg) {

    }

    @Override
    public void getProductDetailSuccess(ProductDetailBean model) {
        AppConstant.productDetailBean=model;
        tv_title.setText(model.getGoods().getTitle());
        tv_price.setText("￥"+model.getGoods().getPrice());
        tv_price_real.setText("板毛:￥"+model.getGoods().getPrice_real()+"/份");
        tv_company.setText(model.getGoods().getCompany());

        List<String> productDetailImg=new ArrayList<>();
        for(int i=0;i<model.getGoods().getAlbum().size();i++){
            Log.e("TTT",i+"--"+model.getGoods().getAlbum().size());
            productDetailImg.add(AppConstant.PRODUCT_DETAIL_ALBUM_IMG_URL+model.getGoods().getAlbum().get(i));
        }

        List<String> content=new ArrayList<>();
        content=model.getGoods().getContent_();
        adapter=new ProductDetailImgAdapter(getActivity(),content);
        recyclerView.setAdapter(adapter);



        //自定义图片加载框架
        banner.setImages(productDetailImg, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                view.setScaleType(ImageView.ScaleType.FIT_XY);
//                view.setAdjustViewBounds(true);
                Glide.with(getActivity()).load(url).into(view);
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
}
