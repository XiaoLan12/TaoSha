package com.yizhisha.taosha.ui.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.DateBean;
import com.yizhisha.taosha.bean.json.SeckillProductBean;
import com.yizhisha.taosha.ui.home.activity.SeckillActivityActivity;
import com.yizhisha.taosha.ui.home.contract.SeckillProductContract;
import com.yizhisha.taosha.ui.home.precenter.SeckillProductPresenter;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.log.LoggerDefault;

/**
 * Created by lan on 2017/8/17.
 */

public class SeckillProductYarnFragment extends BaseFragment{

    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.original_price_tv)
    TextView originalPriceTv;
    @Bind(R.id.seckill_price_tv)
    TextView seckillPriceTv;
    @Bind(R.id.day_tv)
    TextView dayTv;
    @Bind(R.id.hour_tv)
    TextView hourTv;
    @Bind(R.id.minute_tv)
    TextView minuteTv;
    @Bind(R.id.second_tv)
    TextView secondTv;
    @Bind(R.id.tv_title)
    TextView titleTv;
    @Bind(R.id.tv_company)
    TextView companyTv;
    @Bind(R.id.tv_favorite_num)
    TextView favoriteNumTv;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.activit_state_tv)
    TextView activitStateTv;

    private SeckillProductBean seckillProductBean;
    private ProductDetailImgAdapter mAdapter;
    private List<String> contentList=new ArrayList<>();
    private long startTime;
    private long endTime;
    private long nowTime;
    private long subTime;
    private MyThread timeThread;


    public static SeckillProductYarnFragment getInstance(SeckillProductBean bean) {
        SeckillProductYarnFragment sf = new SeckillProductYarnFragment();
        sf.seckillProductBean = bean;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_seckillproduct;
    }
    @Override
    protected void initView() {
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        banner.setIndicatorGravity(Banner.CENTER);
        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(false);
        initAdapter();
        if(seckillProductBean==null){
            return;
        }

        SeckillProductBean.Goods goods=seckillProductBean.getGoods();
        if(goods==null){
            return;
        }
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

        //加载商品详情
        SeckillProductBean.Seckilling seckilling=seckillProductBean.getSeckilling();
        if(seckilling==null){
            return;
        }
        contentList.clear();
        contentList.addAll(goods.getContent());
        mAdapter.setNewData(contentList);
        //加载商品信息

        originalPriceTv.setText("原价:￥"+seckilling.getMarket_price());
        seckillPriceTv.setText(seckilling.getPrice());
        titleTv.setText(seckilling.getTitle());
        companyTv.setText(goods.getCompany());

        //startTime=seckilling.getStarttime();
        //endTime=seckilling.getEndtime();
        nowTime=seckillProductBean.getNowtime();
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        long cur1 = curDate.getTime();// 获取当前时间
        subTime = nowTime*1000 -cur1;//获得时间差
        endTime=cur1+30*60*1000;
        startTime=cur1+1*60*1000;
        new Thread(new MyThread()).start();
    }

    private void initAdapter(){
        mAdapter=new ProductDetailImgAdapter(activity,contentList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
    }
    private void setDateInfo(Long startdate, Long endDate) {
        try {
            //前的时间
            Date sd = new Date(startdate);
            //后的时间
            Date ed = new Date(endDate);
            long diff = ed.getTime() - sd.getTime(); // 得到的差值是微秒级别，可以忽略
            long day = diff / 86400000;                         //以天数为单位取整
            long hours = diff % 86400000 / 3600000;               //以小时为单位取整
            long minutes = diff % 86400000 % 3600000 / 60000;       //以分钟为单位取整
            long seconds = diff % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整

            dayTv.setText(String.format("%02d", day));
            hourTv.setText(String.format("%02d", hours));
            minuteTv.setText( String.format("%02d", minutes));
            secondTv.setText( String.format("%02d", seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Date curDate = new Date();// 获取当前时间
                    long cur = curDate.getTime();// 获取当前时间

                   /* if(startTime*1000-(cur+subTime)<0){
                        activitStateTv.setText("活动未开始");
                    }*/
                    if (startTime - (cur+subTime) > 0) {
                        activitStateTv.setText("活动未开始");
                    } else if (endTime - (cur+subTime) > 0) {
                        activitStateTv.setText("距离结束还剩");
                        setDateInfo((cur+subTime),endTime);
                        //setDateInfo((cur+subTime),endTime*1000);
                    } else {
                        activitStateTv.setText("活动已结束");
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };
    class MyThread implements Runnable{
        //用来停止线程
        boolean endThread;

        @Override
        public void run() {
            while(!endThread){
                try{
                    Thread.sleep(1000);
                    Message message = new Message();
                    message.what = 1;
                    //发送信息给handler
                    handler.sendMessage(message);
                }catch (Exception e){

                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timeThread.endThread=false;
    }
}
