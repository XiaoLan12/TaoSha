package com.yizhisha.taosha.api;

import com.yizhisha.taosha.bean.json.AccountBean;
import com.yizhisha.taosha.bean.json.CommentPicBean;
import com.yizhisha.taosha.bean.json.FootpringBean;
import com.yizhisha.taosha.bean.json.MyCommentBean;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.ShopCartBean;
import com.yizhisha.taosha.bean.json.UserHeadBean;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.CommentListBean;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.bean.json.SeckillActListBean;
import com.yizhisha.taosha.bean.json.SeckillListBean;
import com.yizhisha.taosha.bean.json.ShopcartListBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;
import com.yizhisha.taosha.bean.json.WechatBean;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
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
    //取消收藏
    @GET("ios/ajax/goodsFavCancel/")
    Observable<RequestStatusBean> cacheCollect(@QueryMap Map<String, String> param);

    //获得个人资料信息
    @GET("ios/ucenter/profile/")
    Observable<PersonalDataBean> getPersonalData(@Query("uid") int uid);
    //修改个人资料
    @GET("ios/ucenter/profile_save/")
    Observable<RequestStatusBean> changePersonalData(@QueryMap Map<String, String> param);
    //获得收货地址
    @GET("ios/ucenter/address/")
    Observable<AddressListBean> getAddressList(@Query("uid") int uid);
    //获得单个收货地址
    @GET("ios/ucenter/address_each/")
    Observable<AddressListBean.Address> getOneAddress(@QueryMap Map<String, String> param);
    //新增收货地址
    @FormUrlEncoded
    @POST("ios/ucenter/address_save/")
    Observable<RequestStatusBean> addAddress(@FieldMap Map<String,String> map);
    //删除收货地址
    @GET("ios/ucenter/address_delete/")
    Observable<RequestStatusBean> deleteAddress(@Query("id") int id);

    //免费拿样
    @FormUrlEncoded
    @POST("ios/ucenter/active/")
    Observable<FreeSampleBean> getFreeSample(@FieldMap Map<String,String> map);

    //取消拿样
    @FormUrlEncoded
    @POST("ios/ucenter/active_delete/")
    Observable<RequestStatusBean> cancelFreeSample(@FieldMap Map<String,String> map);

    //我的评论
    @GET("ios/ucenter/commentList/")
    Observable<MyCommentBean> loadMyComment(@Query("uid") int uid);

    //我的足迹
    @GET("ios/ucenter/history/")
    Observable<FootpringBean> loadFootprint(@Query("uid") int uid);

    //获得订单
    @FormUrlEncoded
    @POST("ios/order/")
    Observable<MyOrderListBean> getOrderList(@FieldMap Map<String,String> map);

    //获得订单详情
    @GET("ios/order/detail")
    Observable<MyOrderListBean> getOrderDetails(@QueryMap Map<String,String> map);

    //修改用户信息
    @FormUrlEncoded
    @POST("ios/ucenter/profile_save/")
    Observable<RequestStatusBean> changeUserInfo(@FieldMap Map<String, String> map);

    //修改用户头像
    @Multipart
    @POST("ios/ajax/uploadAvatarPic/")
    Observable<UserHeadBean> changeUserHead(@Part MultipartBody.Part file);

    //手机号绑定
    @GET("ios/ucenter/mobile_save/")
    Observable<RequestStatusBean> bindPhone(@QueryMap Map<String,String> map);



    //登录
    @FormUrlEncoded
    @POST("ios/user/dologin/")
    Observable<RequestStatusBean> Login(@FieldMap Map<String,String> map);

    //注册
    @FormUrlEncoded
    @POST("ios/user/doreg/")
    Observable<RequestStatusBean> Register(@FieldMap Map<String,String> map);

    // 找回密码
    @POST("ios/user/getpassword/")
    Observable<RequestStatusBean> FindPwd(@QueryMap Map<String,String> map);

    // 获取验证码
    @GET("ios/ajax/checkpost/")
    Observable<RequestStatusBean> getCode(@QueryMap Map<String,String> map);

    // 手机快捷登录获取验证码
    @GET("ios/ajax/logincheck/")
    Observable<RequestStatusBean> getPhoneLoginCode(@QueryMap Map<String,String> map);
    //手机快捷登录
    @FormUrlEncoded
    @POST("ios/ajax/quicklogin/")
    Observable<RequestStatusBean> phoneLogin(@FieldMap Map<String,String> map);

    //修改密码
    @FormUrlEncoded
    @POST("ios/ucenter/password_save/")
    Observable<RequestStatusBean> changePwd(@FieldMap Map<String,String> map);

    //获得微信登录的数据
    @GET()
    Observable<WechatBean> getWeChatLoginData(@Url String url);

    //微信登录
    @FormUrlEncoded
    @POST("ios/user/wxlogin/")
    Observable<RequestStatusBean> weChatLogin(@FieldMap Map<String,String> map);

    //绑定微信号
    @GET("ios/user/wxreg/")
    Observable<RequestStatusBean> bindWeChat(@QueryMap Map<String, String> param);
    //首页轮播
    @GET("ios/index/")
    Observable<IndexPPTBean> getPPT(@QueryMap Map<String, String> param);

    //首页推荐纺纱6种
    @GET("ios/goods/goodsNew/")
    Observable<IndexRecommendYarnBean> getRecommendYarn(@QueryMap Map<String, String> param);


    // 搜索页搜索
    @FormUrlEncoded
    @POST("ios/goods/lists/")
    Observable<SearchBean> search(@FieldMap Map<String,String> map);


    //商品详情
    @GET("ios/goods/view/")
    Observable<ProductDetailBean> getProductDetail(@QueryMap Map<String, String> param);

    //商品评价列表
    @FormUrlEncoded
    @POST("ios/goods/goodsComment/")
    Observable<CommentListBean> getCommentList(@FieldMap Map<String, String> param);

    //购物车
    @GET("ios/ucenter/shopcart/")
    Observable<ShopcartListBean> getShoppCartList(@Query("uid") int id);

    //加载单个购物车
    @GET("ios/ucenter/shopcart_show/")
    Observable<ShopCartBean> loadSingleShpCart(@QueryMap Map<String, String> param);

    //修改购物车
    @FormUrlEncoded
    @POST("ios/ucenter/shopcart_update/")
    Observable<RequestStatusBean> changeShopCart(@FieldMap Map<String, String> param);
    //删除购物车商品
    @GET("ios/ucenter/shopcart_delete/")
    Observable<RequestStatusBean> deleteShoppCart(@QueryMap  Map<String, String> param);

    //秒杀订单

    @GET("ios/order/seckilling/")
    Observable<SeckillListBean> getSeckillOrder(@QueryMap Map<String, String> param);

    //秒杀订单详情
    @GET("ios/order/detail_seckilling/")
    Observable<SeckillListBean> getSeckillOrderDetails(@QueryMap Map<String, String> param);

    //秒纱h活动
    @GET("ios/goods/seckilling/")
    Observable<SeckillActListBean> getSeckillActivity(@QueryMap Map<String, String> param);

    //发布评论
    @FormUrlEncoded
    @POST("ios/ucenter/commentSave/")
    Observable<RequestStatusBean> addComment(@FieldMap Map<String, String> param);

    //发布追评
    @FormUrlEncoded
    @POST("ios/ucenter/commentAddSave/")
    Observable<RequestStatusBean> addAddComment(@FieldMap Map<String, String> param);

    //上传评论图片
    @Multipart
    @POST("ios/ajax/uploadCommentPic/")
    Observable<CommentPicBean> addCommentPic(@Part MultipartBody.Part file);

    //账务中心
    @GET("ios/ucenter/finance/")
    Observable<AccountBean> loadAccount(@Query("uid") int id);

    //订单确定
    @Multipart
    @POST("ios/order/confirm/")
    Observable<OrderSureBean> orderSure(@FieldMap Map<String, String> param);
}
