package com.yizhisha.taosha.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.ProductDeatilItemBean;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.ui.home.activity.CommentYarnActivity;
import com.yizhisha.taosha.ui.home.contract.ProductYarnContract;
import com.yizhisha.taosha.ui.home.precenter.ProductYarnPresenter;
import com.yizhisha.taosha.utils.GlideUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/2.
 */

public class ProductYarnFragnment extends BaseFragment{
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
    RecyclerView mRecyclerView;
    @Bind(R.id.comment_amount_tv)
    TextView commentAmountTv;
    @Bind(R.id.userhead_iv)
    ImageView userheadIv;
    @Bind(R.id.userphone_tv)
    TextView userphoneTv;
    @Bind(R.id.comment_details_tv)
    TextView commentDetailsTv;
    @Bind(R.id.look_allcomment_tv)
    TextView lookAllcommentTv;
    @Bind(R.id.tv_fanxian)
    TextView tv_fanxian;
    @Bind(R.id.tv_lijian)
    TextView tv_lijian;
    @Bind(R.id.tv_banjiabanmao)
    TextView tv_banjiabanmao;
    @Bind(R.id.tv_free_sample)
    TextView tv_free_sample;
    @Bind(R.id.tv_favorite_num)
    TextView tv_favorite_num;

    private ProductDetailImgAdapter mAdapter;
    private ProductDetailBean productDetailBean;
    private List<String> contentList=new ArrayList<>();
    private int id;


    public static ProductYarnFragnment getInstance(int id,ProductDetailBean bean) {
        ProductYarnFragnment sf = new ProductYarnFragnment();
        sf.productDetailBean = bean;
        sf.id=id;
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
        initAdapter();
        if(productDetailBean==null){
            return;
        }
        ProductDeatilItemBean goods=productDetailBean.getGoods();
        //加载轮播
        List<String> albumList = new ArrayList<>();
        for (int i = 0; i <goods.getAlbum().size(); i++) {
            albumList.add(AppConstant.PRODUCT_DETAIL_ALBUM_IMG_URL + goods.getAlbum().get(i));
        }
        //自定义图片加载框架
        banner.setImages(albumList, new Banner.OnLoadImageListener() {
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
        //加载详情
        contentList.clear();
        contentList.addAll(goods.getContent_());
        mAdapter.setNewData(contentList);
        //加载商品信息
        if(goods.getIs_fanxian().equals("1")){
            tv_fanxian.setVisibility(View.VISIBLE);
        }else{
            tv_fanxian.setVisibility(View.GONE);
        }
        if(goods.getIs_nayang().equals("1")){
            tv_free_sample.setVisibility(View.VISIBLE);
        }else{
            tv_free_sample.setVisibility(View.GONE);
        }
        if(goods.getIs_dunjian().equals("1")){
            tv_lijian.setVisibility(View.VISIBLE);
        }else{
            tv_lijian.setVisibility(View.GONE);
        }
       /* if(model.getGoods().getIs_fanxian().equals("1")){
            tv_fanxian.setVisibility(View.VISIBLE);
        }else{
            tv_fanxian.setVisibility(View.GONE);
        }*/

        tv_favorite_num.setText(goods.getFavorite());
        tv_title.setText(goods.getTitle());
        tv_price.setText("￥" + goods.getPrice());
        tv_price_real.setText("板毛:￥" + goods.getPrice_real() + "/份");
        tv_company.setText(goods.getCompany());
        //加载评论
        ProductDetailBean.Comment comment=productDetailBean.getComment();
        if (comment != null && comment.getCount() > 0) {
            commentAmountTv.setText("全部评价(" + comment.getCount() + ")");
            GlideUtil.getInstance().LoadContextCircleBitmap(activity, AppConstant.AVATARURL + comment.getAvatar(), userheadIv);
            userphoneTv.setText(comment.getMobile());
            commentDetailsTv.setText(comment.getComment_detail());
        } else {
            commentAmountTv.setText("全部评价(0)");
            userheadIv.setImageResource(R.drawable.icon_head_normal);
            commentDetailsTv.setText("暂无评论");
        }

    }
    private void initAdapter(){
        mAdapter=new ProductDetailImgAdapter(activity,contentList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
    }
    @OnClick({R.id.look_allcomment_tv, R.id.comment_ll})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.look_allcomment_tv:
                Bundle bundle = new Bundle();
                bundle.putInt("ID", id);
                startActivity(CommentYarnActivity.class, bundle);
                break;
            case R.id.comment_ll:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("ID", id);
                startActivity(CommentYarnActivity.class, bundle1);
                break;
        }
    }
}
