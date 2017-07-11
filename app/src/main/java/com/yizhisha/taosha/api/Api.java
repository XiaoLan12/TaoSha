package com.yizhisha.taosha.api;

import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.bean.json.SeckillListBean;
import com.yizhisha.taosha.bean.json.ShopcartListBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by lan on 2017/7/3.
 */

public class Api {
    public final static String API_BASE_URL="http://www.taoshamall.com/";
    public static Api instance;
    private ApiService service;

    public Api() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static Api getInstance() {
        if (instance == null)
            instance = new Api();
        return instance;
    }
    //===============================个人中心=======================
    //获得个人信息
    public Observable<UserInfoBean> loadUserInfo(int uid){
        return service.getUserInfo(uid);
    }
    //获得个人信息
    public Observable<CollectListBean> loadCollectList(Map<String, String> param){
        return service.getCollectList(param);
    }
    //获得个人资料
    public Observable<PersonalDataBean> loadPersonalData(int uid){
        return service.getPersonalData(uid);
    }
    //修改个人资料
    public Observable<RequestStatusBean> changePersonalData(Map<String,String> map) {
        return service.changePersonalData(map);
    }
    //获得收货列表
    public Observable<AddressListBean> loadAddressList(int uid){
        return service.getAddressList(uid);
    }
    //新增收货地址
    public Observable<RequestStatusBean> addAddress(Map<String,String> map){
        return service.addAddress(map);
    }
    //删除收货地址
    public Observable<RequestStatusBean> deleteAddress(int id){
        return service.deleteAddress(id);
    }
    //免费拿样
    public Observable<FreeSampleBean> loadFreeSample(Map<String,String> map){
        return service.getFreeSample(map);
    }
    //取消免费拿样
    public Observable<RequestStatusBean> cancelFreeSample(Map<String,String> map){
        return service.cancelFreeSample(map);
    }
    //获得订单
    public Observable<MyOrderListBean> loadOrderList(Map<String,String> map){
        return service.getOrderList(map);
    }
    //获得订单详情
    public Observable<MyOrderListBean> loadOrderDetails(Map<String,String> map){
        return service.getOrderDetails(map);
    }
    //登录
    public Observable<RequestStatusBean> login(Map<String,String> map){
        return service.Login(map);
    }
    //注册
    public Observable<RequestStatusBean> register(Map<String,String> map){
        return service.Register(map);
    }
    //找回密码
    public Observable<RequestStatusBean> findPwd(Map<String,String> map){
        return service.FindPwd(map);
    }
    //获取验证码
    public Observable<RequestStatusBean> getCode(Map<String,String> map){
        return service.getCode(map);
    }

    //首页轮播
    public Observable<IndexPPTBean> getPPT(Map<String,String> map){
        return service.getPPT(map);
    }

    //首页推荐纺纱6种
    public Observable<IndexRecommendYarnBean> getRecommendYarn(Map<String,String> map){
        return service.getRecommendYarn(map);
    }

    // 搜索页搜索
    public Observable<SearchBean> search(Map<String,String> map){
        return service.search(map);
    }

    // 商品详情
    public Observable<ProductDetailBean> getProductDetail(Map<String,String> map){
        return service.getProductDetail(map);
    }
    // 购物车
    public Observable<ShopcartListBean> getShoppCart(int uid){
        return service.getShoppCartList(uid);
    }
    // 商品详情
    public Observable<SeckillListBean> getSeckillOrder(Map<String,String> map){
        return service.getSeckillOrder(map);
    }
}
