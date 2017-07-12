package com.yizhisha.taosha.api;

import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.bean.json.SeckillActListBean;
import com.yizhisha.taosha.bean.json.SeckillBean;
import com.yizhisha.taosha.bean.json.SeckillListBean;
import com.yizhisha.taosha.bean.json.ShopcartListBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lan on 2017/7/3.
 */

public interface ApiService {

    //===============================个人中心====================================
    //获得用户信息
    @GET("ios/ucenter/")
    Observable<UserInfoBean> getUserInfo(@Query("uid") int uid);
    //获得收藏列表
    @GET("ios/ucenter/favorite/")
    Observable<CollectListBean> getCollectList(@QueryMap Map<String, String> param);
    //获得个人资料信息
    @GET("ios/ucenter/profile/")
    Observable<PersonalDataBean> getPersonalData(@Query("uid") int uid);
    //修改个人资料
    @GET("ios/ucenter/profile_save/")
    Observable<RequestStatusBean> changePersonalData(@QueryMap Map<String, String> param);
    //获得收货地址
    @GET("ios/ucenter/address/")
    Observable<AddressListBean> getAddressList(@Query("uid") int uid);
    //新增收货地址
    @POST("ios/ucenter/address_save/")
    Observable<RequestStatusBean> addAddress(@QueryMap Map<String,String> map);
    //删除收货地址
    @GET("ios/ucenter/address_delete/")
    Observable<RequestStatusBean> deleteAddress(@Query("id") int id);

    //免费拿样
    @POST("ios/ucenter/active/")
    Observable<FreeSampleBean> getFreeSample(@QueryMap Map<String,String> map);

    //取消拿样
    @POST("ios/ucenter/active_delete/")
    Observable<RequestStatusBean> cancelFreeSample(@QueryMap Map<String,String> map);


    //获得订单
    @POST("ios/order/")
    Observable<MyOrderListBean> getOrderList(@QueryMap Map<String,String> map);

    //获得订单详情
    @GET("ios/order/detail")
    Observable<MyOrderListBean> getOrderDetails(@QueryMap Map<String,String> map);



    //登录
    @POST("ios/user/dologin/")
    Observable<RequestStatusBean> Login(@QueryMap Map<String,String> map);

    //注册
    @POST("ios/user/doreg/")
    Observable<RequestStatusBean> Register(@QueryMap Map<String,String> map);

    // 找回密码
    @POST("ios/user/getpassword/")
    Observable<RequestStatusBean> FindPwd(@QueryMap Map<String,String> map);

    // 获取验证码
    @POST("ios/ajax/checkpost/")
    Observable<RequestStatusBean> getCode(@QueryMap Map<String,String> map);


    //首页轮播
    @GET("ios/index/")
    Observable<IndexPPTBean> getPPT(@QueryMap Map<String, String> param);

    //首页推荐纺纱6种
    @GET("ios/goods/goodsNew/")
    Observable<IndexRecommendYarnBean> getRecommendYarn(@QueryMap Map<String, String> param);


    // 搜索页搜索
    @POST("ios/goods/lists/")
    Observable<SearchBean> search(@QueryMap Map<String,String> map);


    //商品详情
    @GET("ios/goods/view/")
    Observable<ProductDetailBean> getProductDetail(@QueryMap Map<String, String> param);

    //购物车
    @GET("ios/ucenter/shopcart/")
    Observable<ShopcartListBean> getShoppCartList(@Query("uid") int id);

    //秒杀订单

    @GET("ios/order/seckilling/")
    Observable<SeckillListBean> getSeckillOrder(@QueryMap Map<String, String> param);

    //秒纱h活动
    @GET("ios/goods/seckilling/")
    Observable<SeckillActListBean> getSeckillActivity(@QueryMap Map<String, String> param);
}
