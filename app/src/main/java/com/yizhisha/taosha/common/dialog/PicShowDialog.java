package com.yizhisha.taosha.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 主界面点击发布，弹出半透明对话框
 * Created by xiaoke on 2016/4/28.
 */
public class PicShowDialog extends Dialog {
    private Context context;
    private View view;
    private List<String> imageUrl;
    private MyViewPager vp;
    private List<View> views = new ArrayList<View>();
    private LayoutAnimationController lac;
    private LinearLayout ll_point;
    private ViewPagerAdapter pageAdapter;
    private int position;
    private LinearLayout.LayoutParams paramsL = new LinearLayout.LayoutParams(10, 10);

    public PicShowDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    public PicShowDialog(Context context, String url, int position) {
        this(context, R.style.Pic_Dialog);
        imageUrl=new ArrayList<>();
        this.imageUrl.add(url);
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dialog_pic);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        getWindow().setLayout(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        vp = (MyViewPager) findViewById(R.id.vp);
//        init();
        initMyPageAdapter();
//        vp.setAdapter(new ViewPagerAdapter());
        vp.setCurrentItem(position);

    }
    /***
     * 初始化viewpager适配器
     */

    private void initMyPageAdapter() {
        if (pageAdapter == null) {
            pageAdapter = new ViewPagerAdapter();
            if (vp != null) {
                vp.setAdapter(pageAdapter);
            }

        } else {
            pageAdapter.notifyDataSetChanged();
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return imageUrl.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(context, R.layout.item_pic_show, null);
            PhotoView photoView = (PhotoView) view.findViewById(R.id.pic_pv);
            GlideUtil.getInstance().LoadContextBitmap(context,imageUrl.get(position),photoView,GlideUtil.LOAD_BITMAP);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    dismiss();
                }
            });
                    ((ViewPager) container).addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }
        private   int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}

