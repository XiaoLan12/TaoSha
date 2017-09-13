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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.DateBean;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.bean.json.SeckillProductBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.common.dialog.PicShowDialog;
import com.yizhisha.taosha.event.SecKillEvent;
import com.yizhisha.taosha.ui.home.activity.CommentYarnActivity;
import com.yizhisha.taosha.ui.home.activity.SeckillActivityActivity;
import com.yizhisha.taosha.ui.home.contract.ProductYarnContract;
import com.yizhisha.taosha.ui.home.contract.SeckillProductContract;
import com.yizhisha.taosha.ui.home.precenter.ProductYarnPresenter;
import com.yizhisha.taosha.ui.home.precenter.SeckillProductPresenter;
import com.yizhisha.taosha.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.taosha.ui.login.activity.RegisterActivity;
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
import butterknife.OnClick;
import uk.co.senab.photoview.log.LoggerDefault;

/**
 * Created by lan on 2017/8/17.
 */

public class SeckillProductYarnFragment extends BaseFragment<ProductYarnPresenter> implements
        ProductYarnContract.View{

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
    @Bind(R.id.activit_state_tv)
    TextView activitStateTv;

    //参数
    @Bind(R.id.tv_product_code)
    TextView tv_product_code;
    @Bind(R.id.tv_ingredient)
    TextView tv_ingredient;
    @Bind(R.id.tv_session_name)
    TextView tv_session_name;
    @Bind(R.id.tv_needle_name)
    TextView tv_needle_name;
    @Bind(R.id.tv_yam)
    TextView tv_yam;
    @Bind(R.id.tv_pname)
    TextView tv_pname;
    @Bind(R.id.tv_brand)
    TextView tv_brand;
    @Bind(R.id.merchant_addrss_tv)
    TextView merchantAddrssTv;
    @Bind(R.id.main_product_tv)
    TextView mainProductTv;
    //色卡
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    //详情
    @Bind(R.id.recycleview1)
    RecyclerView mRecyclerView1;
    //详情
    private List<String> contentList=new ArrayList<>();
    //色卡
    private List<String> sekaList=new ArrayList<>();
    //商品信息
    private SeckillProductBean seckillProductBean;
    private SeckillProductBean.Goods goods;

    private long startTime;
    private long endTime;
    private long nowTime;
    private long subTime;
    boolean stopThread=false;

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
        if(seckillProductBean==null){
            return;
        }
        goods=seckillProductBean.getGoods();
        if(goods==null){
            return;
        }
        //加载轮播
        List<String> albumList = new ArrayList<>();
        for (int i = 0; i <goods.getAlbum().size(); i++) {
            albumList.add(AppConstant.PRODUCT_DETAIL_ALBUM_IMG_URL + goods.getAlbum().get(i));
        }
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
        //加载商品详情
        SeckillProductBean.Seckilling seckilling=seckillProductBean.getSeckilling();
        if(seckilling==null){
            return;
        }
        //加载商品信息
        originalPriceTv.setText("原价:￥"+seckilling.getMarket_price());
        seckillPriceTv.setText(seckilling.getPrice());
        titleTv.setText(seckilling.getTitle());
        companyTv.setText(goods.getCompany());
        mainProductTv.setText(goods.getMajor());
        merchantAddrssTv.setText(goods.getAddress());

        startTime=seckilling.getStarttime()*1000;
        endTime=seckilling.getEndtime()*1000;

        nowTime=seckillProductBean.getNowtime();
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        long cur1 = curDate.getTime();// 获取当前时间
        subTime = nowTime*1000 -cur1;//获得时间差
        new Thread(mRunnable).start();

        initParameter();
        initSeka();
        initDetail();
    }

    private void initParameter() {
        tv_product_code.setText(goods.getCode());
        tv_ingredient.setText(goods.getColor());
        tv_needle_name.setText(goods.getNeedle_name());
        tv_yam.setText(goods.getYam());
        tv_pname.setText(goods.getPname());
        tv_brand.setText(goods.getBrand());
    }
    private void initSeka() {
        if(goods.getSeka()!=null){
            sekaList.addAll(goods.getSeka());
        }
        for(int i=0;i<sekaList.size();i++){
            sekaList.set(i,AppConstant.PRODUCT_DETAIL_SEKA_IMG_URL+sekaList.get(i));
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        ProductDetailImgAdapter adapter=new ProductDetailImgAdapter(activity,sekaList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicShowDialog dialog=new PicShowDialog(activity,sekaList,position);
                dialog.show();
            }
        });
    }
    private void initDetail() {
        contentList.addAll(goods.getContent());
        for(int i=0;i<contentList.size();i++){
            contentList.set(i,AppConstant.PRODUCT_DETAIL_SEKA_IMG_URL+contentList.get(i));
        }
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView1.setHasFixedSize(true);
        mRecyclerView1.setNestedScrollingEnabled(false);
        ProductDetailImgAdapter adapter=new ProductDetailImgAdapter(activity,contentList);
        mRecyclerView1.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicShowDialog dialog=new PicShowDialog(activity,contentList,position);
                dialog.show();
            }
        });
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
            if(getActivity()==null){
                return;
            }
            switch (msg.what){
                case 1:
                    Date curDate = new Date();// 获取当前时间
                    long cur = curDate.getTime();// 获取当前时间

                    if (startTime - (cur+subTime) > 0) {
                        activitStateTv.setText("活动未开始");
                        RxBus.$().postEvent(new SecKillEvent(1));
                    } else if (endTime - (cur+subTime) > 0) {
                        activitStateTv.setText("距离结束还剩");
                        setDateInfo((cur+subTime),endTime);
                        RxBus.$().postEvent(new SecKillEvent(2));
                        //setDateInfo((cur+subTime),endTime*1000);
                    } else {
                        activitStateTv.setText("活动已结束");
                        RxBus.$().postEvent(new SecKillEvent(3));
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };
    private Runnable mRunnable = new Runnable(){
        @Override
        public void run() {
            while(!stopThread){
                try{
                    Thread.sleep(1000);
                    Message message=handler.obtainMessage();
                    message.what = 1;
                    //发送信息给handler
                    handler.sendMessage(message);
                }catch (Exception e){

                }
            }
        }
    };
    @OnClick({R.id.collect_iv})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collect_iv:
                if(AppConstant.isLogin==false){
                    final List<String> mDatas1=new ArrayList<>();
                    mDatas1.add("登录");
                    mDatas1.add("注册");
                    NormalSelectionDialog dialog=new NormalSelectionDialog.Builder(activity)
                            .setBoolTitle(true)
                            .setTitleText("温馨提示\n尊敬的用户,您尚未登录,请选择登录或注册")
                            .setTitleHeight(55)
                            .setItemHeight(45)
                            .setItemTextColor(R.color.blue)
                            .setItemTextSize(14)
                            .setItemWidth(0.95f)
                            .setCancleButtonText("取消")
                            .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                                @Override
                                public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                    switch (position){
                                        case 0:
                                            startActivity(LoginFragmentActivity.class);
                                            break;
                                        case 1:
                                            startActivity(RegisterActivity.class);
                                            break;
                                    }
                                    dialog.dismiss();
                                }
                            }).setTouchOutside(true)
                            .build();
                    dialog.setData(mDatas1);
                    dialog.show();
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("gid",String.valueOf(seckillProductBean.getSeckilling().getGoods_id()));
                map.put("uid",String.valueOf(AppConstant.UID));
                mPresenter.collectProduct(map);
                break;
        }
    }
    @Override
    public void onDestroyView() {
        stopThread=true;
        super.onDestroyView();

    }
    @Override
    public void collectProductSuccess(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }
}
