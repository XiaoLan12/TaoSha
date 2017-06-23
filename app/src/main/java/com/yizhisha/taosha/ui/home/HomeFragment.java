package com.yizhisha.taosha.ui.home;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.youth.banner.Banner;

import butterknife.Bind;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/6/22.
 */
public class HomeFragment extends BaseFragment{
    @Bind(R.id.banner)
    Banner banner;
    //设置图片资源:url或本地资源
    String[] images= new String[] {
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {


//            StatusBarCompat.setStatusBarColor(activity, Color.GREEN);
        }
    }
    @Override
    protected void initView() {
        StatusBarCompat.translucentStatusBar(getActivity(), true);

        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR	显示圆形指示器
        //2. Banner.NUM_INDICATOR	显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE	显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE	显示圆形指示器和标题
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT	指示器居左
        //Banner.CENTER	指示器居中
        //Banner.RIGHT	指示器居右
        banner.setIndicatorGravity(Banner.RIGHT);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
//        banner.setBannerTitle(titles);

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

    }
}
