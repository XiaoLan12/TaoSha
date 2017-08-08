package com.yizhisha.taosha.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;

/**
 * 图片浏览大图的页面
 */
public class ShowImageActivity extends BaseActivity {
    @Bind(R.id.show_img_back_iv)
    ImageView ivBack;
    @Bind(R.id.show_img_page_tv)
    TextView tvPage;
    ImageView iv;
    int pagerNumber;
    @Bind(R.id.load_viewPager)
    ViewPager viewPager;
    private ArrayList<String> pathList = new ArrayList<String>();
    private final String URL="http://www.taoshamall.com/data/attached/comment/";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_image;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        pathList = intent.getStringArrayListExtra("list");
        pagerNumber = intent.getIntExtra("number", 0);
        ImageAdapter mAdapter = new ImageAdapter(this);
        mAdapter.setDatas(pathList);
        viewPager.setAdapter(mAdapter);
        tvPage.setText(1 + "/" + pathList.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                tvPage.setText(position + 1 + "/" + pathList.size());
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        viewPager.setCurrentItem(pagerNumber);
    }
    private  class ImageAdapter extends PagerAdapter{

        private List<String> datas = new ArrayList<String>();
        private LayoutInflater inflater;
        private Context context;

        public void setDatas(List<String> datas) {
            if(datas != null )
                this.datas = datas;
        }

        public ImageAdapter(Context context){
            this.context = context;
            this.inflater = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            if(datas == null) return 0;
            return datas.size();
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = inflater.inflate(R.layout.item_pager_image, container, false);
            if(view != null){
                final PhotoView imageView = (PhotoView) view.findViewById(R.id.image);
                //loading
                final ProgressBar loading = new ProgressBar(context);
                FrameLayout.LayoutParams loadingLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingLayoutParams.gravity = Gravity.CENTER;
                loading.setLayoutParams(loadingLayoutParams);
                ((FrameLayout)view).addView(loading);
                loading.setVisibility(View.VISIBLE);
                Glide.with(context).load(AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+datas.get(position))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.loading_dialog_progressbar)
                        .thumbnail(0.1f)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                loading.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                loading.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(imageView);

                container.addView(view, 0);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }


    }
    @OnClick({R.id.show_img_back_iv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.show_img_back_iv:
                finish_Activity(ShowImageActivity.this);
                break;
        }
    }
}
