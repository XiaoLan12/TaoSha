package com.yizhisha.taosha.api;

import com.yizhisha.taosha.bean.json.AccountBean;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.CommentListBean;
import com.yizhisha.taosha.bean.json.CommentPicBean;
import com.yizhisha.taosha.bean.json.FootpringBean;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.bean.json.HotCommendBean;
import com.yizhisha.taosha.bean.json.IndexPPTBean;
import com.yizhisha.taosha.bean.json.IndexRecommendYarnBean;
import com.yizhisha.taosha.bean.json.ListGoodsBean;
import com.yizhisha.taosha.bean.json.MyCommentBean;
import com.yizhisha.taosha.bean.json.MyOrderListBean;
import com.yizhisha.taosha.bean.json.OrderSureBean;
import com.yizhisha.taosha.bean.json.PayReqBean;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
import com.yizhisha.taosha.bean.json.SearchBean;
import com.yizhisha.taosha.bean.json.SeckillActListBean;
import com.yizhisha.taosha.bean.json.SeckillListBean;
import com.yizhisha.taosha.bean.json.SeckillOrderSureBean;
import com.yizhisha.taosha.bean.json.SeckillProductBean;
import com.yizhisha.taosha.bean.json.ShopCartBean;
import com.yizhisha.taosha.bean.json.ShopCartOrderSureBean;
import com.yizhisha.taosha.bean.json.ShopcartListBean;
import com.yizhisha.taosha.bean.json.UserHeadBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;
import com.yizhisha.taosha.bean.json.WeChatPayStateBean;
import com.yizhisha.taosha.bean.json.WechatBean;
import com.yizhisha.taosha.bean.json.WechatInfoBean;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
    //获得收藏k列表
    public Observable<CollectListBean> loadCollectList(Map<String, String> param){
        return service.getCollectList(param);
    }
    //获得收藏k列表
    public Observable<RequestStatusBean> cacheCollect(Map<String, String> param){
        return service.cacheCollect(param);
    }
    //收藏商品
    public Observable<RequestStatusBean> collectProduct(Map<String, String> param){
        return service.collectProduct(param);
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
    //获得单个收货地址
    public Observable<AddressListBean.Address> loadOneAddress(Map<String,String> map){
        return service.getOneAddress(map);
    }
    //新增收货地址
    public Observable<RequestStatusBean> addAddress(Map<String,String> map){
        return service.addAddress(map);
    }
    //删除收货地址
    public Observable<RequestStatusBean> deleteAddress(int id){
        return service.deleteAddress(id);
    }
    //修改默认地址
    public Observable<RequestStatusBean> changeNormalAddress(Map<String,String> map){
        return service.changeNormalAddress(map);
    }
    //免费拿样
    public Observable<FreeSampleBean> loadFreeSample(Map<String,String> map){
        return service.getFreeSample(map);
    }
    //取消免费拿样
    public Observable<RequestStatusBean> cancelFreeSample(Map<String,String> map){
        return service.cancelFreeSample(map);
    }
    //我的评论
    public Observable<MyCommentBean> loadMyComment(int uid){
        return service.loadMyComment(uid);
    }
    //我的足迹
    public Observable<FootpringBean> loadFootprint(Map<String,String> map){
        return service.loadFootprint(map);
    }
    //删除我的足迹
    public Observable<RequestStatusBean> clearFootPrint(Map<String,String> map){
        return service.clearFootPrint(map);
    }
    //获得订单
    public Observable<MyOrderListBean> loadOrderList(Map<String,String> map){
        return service.getOrderList(map);
    }
    //确认收货
    public Observable<RequestStatusBean> sureGoods(Map<String,String> map){
        return service.sureGoods(map);
    }
    //取消订单
    public Observable<RequestStatusBean> cancelOrder(Map<String,String> map){
        return service.cancelOrder(map);
    }
    //获得订单详情
    public Observable<MyOrderListBean> loadOrderDetails(Map<String,String> map){
        return service.getOrderDetails(map);
    }
    //修改支付状态
    public Observable<RequestStatusBean> changePayWay(Map<String,String> map){
        return service.changePayWay(map);
    }

    //修改密码
    public Observable<RequestStatusBean> changePwd(Map<String,String> map){
        return service.changePwd(map);
    }
    //修改用户信息
    public Observable<RequestStatusBean> changeUserInfo(Map<String, String> map){
        return service.changeUserInfo(map);
    }
    //修改用户头像
    public Observable<UserHeadBean> changeUserHead(RequestBody uid,MultipartBody.Part body){
        return service.changeUserHead(uid,body);
    }
    //绑定手机号码
    public Observable<RequestStatusBean> bindPhone(Map<String, String> map){
        return service.bindPhone(map);
    }
    //获得绑定的手机号码
    public Observable<RequestStatusBean> loadBindPhone(int uid){
        return service.loadBindPhone(uid);
    }

    //获得绑定的微信
    public Observable<RequestStatusBean> loadBindWeChat(int uid){
        return service.loadBindWeChat(uid);
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
    //手机快捷登录获取验证码
    public Observable<RequestStatusBean> getPhoneLoginCode(Map<String,String> map){
        return service.getPhoneLoginCode(map);
    }
    //手机快捷登录
    public Observable<RequestStatusBean> phoneLogin(Map<String,String> map){
        return service.phoneLogin(map);
    }
    //获得微信登录的数据
    public Observable<WechatBean> getWeChatLoginData(String url){
        return service.getWeChatLoginData(url);
    }
    //微信登录
    public Observable<RequestStatusBean> weChatLogin(Map<String,String> map){
        return service.weChatLogin(map);
    }
    //绑定微信号
    public Observable<RequestStatusBean> bindWeChat(Map<String,String> map){
        return service.bindWeChat(map);
    }
    //微信解除绑定
    public Observable<RequestStatusBean> unBindWeChat(int uid){
        return service.unBindWeChat(uid);
    }
    //获得微信登录的数据
    public Observable<WechatInfoBean> getWeChatInfo(String url){
        return service.getWeChatInfo(url);
    }
    //微信解除绑定
    public Observable<RequestStatusBean> showBindWeChart(int uid){
        return service.showBindWeChart(uid);
    }

    //首页轮播
    public Observable<IndexPPTBean> getPPT(){
        return service.getPPT();
    }

    //首页推荐纺纱6种
    public Observable<IndexRecommendYarnBean> getRecommendYarn(){
        return service.getRecommendYarn();
    }
    //加载热门推荐
    public Observable<HotCommendBean> loadHotCommend(String type){
        return service.loadHotCommend(type);
    }
    //加载同类推荐
    public Observable<HotCommendBean> loadCommend(int gid){
        return service.loadCommend(gid);
    }
    // 搜索页搜索
    public Observable<SearchBean> search(Map<String,String> map){
        return service.search(map);
    }

    //获得评论列表
    public Observable<CommentListBean> getCommentList(Map<String,String> map){
        return service.getCommentList(map);
    }
    // 商品详情
    public Observable<ProductDetailBean> getProductDetail(Map<String,String> map){
        return service.getProductDetail(map);
    }
    // 精品商品详情
    public Observable<ProductDetailBean> getProductCommendDetail(String url){
        return service.getProductCommendDetail(url);
    }
    // 购物车
    public Observable<ShopcartListBean> getShoppCart(int uid){
        return service.getShoppCartList(uid);
    }
    //加载单个购物车
    public Observable<ShopCartBean> loadSingleShpCart(Map<String,String> map){
        return service.loadSingleShpCart(map);
    }
    //修改购物车
    public Observable<RequestStatusBean> changeShopCart(Map<String,String> map){
        return service.changeShopCart(map);
    }
    //删除购物车
    public Observable<RequestStatusBean> deleteShoppCart(Map<String,String> map){
        return service.deleteShoppCart(map);
    }
    // 秒杀订单
    public Observable<SeckillListBean> getSeckillOrder(Map<String,String> map){
        return service.getSeckillOrder(map);
    }
    //取消秒杀订单
    public Observable<RequestStatusBean> cancelSkillOrder(Map<String,String> map){
        return service.cancelSkillOrder(map);
    }
    // 秒杀订单详情
    public Observable<SeckillListBean> getSeckillOrderDetail(Map<String,String> map){
        return service.getSeckillOrderDetails(map);
    }
    //秒纱活动
    public Observable<SeckillActListBean> getSeckillActivity(Map<String,String> map){
        return service.getSeckillActivity(map);
    }
    //秒杀产品详情
    public Observable<SeckillProductBean> getSeckillProduct(Map<String,String> map){
        return service.getSeckillProduct(map);
    }
    //发布评论
    public Observable<RequestStatusBean> addComment(Map<String,String> map){
        return service.addComment(map);
    }
    //发布追评
    public Observable<RequestStatusBean> addAddComment(Map<String,String> map){
        return service.addAddComment(map);
    }
    //添加评论图片
    public Observable<CommentPicBean> addCommentPic(MultipartBody.Part body){
        return service.addCommentPic(body);
    }
    //账务中心
    public Observable<AccountBean> loadAccount(int uid){
        return service.loadAccount(uid);
    }

    //订单确认
    public Observable<OrderSureBean> orderSure(Map<String,String> map){
        return service.orderSure(map);
    }
    //购物车订单确认
    public Observable<ShopCartOrderSureBean> shopCartOrderSure(Map<String,String> map){
        return service.shopCartOrderSure(map);
    }

    //秒杀下单确认
    public Observable<SeckillOrderSureBean> seckillOrderSure(Map<String,String> map){
        return service.seckillOrderSure(map);
    }

    //拿样下单确认
    public Observable<AddressListBean> nayangOrderSure(Map<String,String> map){
        return service.nayangOrderSure(map);
    }

    //普通商品和板毛创建订单
    public Observable<RequestStatusBean> regularOrder(Map<String,String> map){
        return service.regularOrder(map);
    }

    //购物车创建订单
    public Observable<RequestStatusBean> shopCartCreateOrder(Map<String,String> map){
        return service.shopCartCreateOrder(map);
    }

    //拿样创建订单
    public Observable<RequestStatusBean> naYangCreateOrder(Map<String,String> map){
        return service.naYangCreateOrder(map);
    }

    //秒纱创建订单
    public Observable<RequestStatusBean> seckillCreateOrder(Map<String,String> map){
        return service.seckillCreateOrder(map);
    }


    //微信支付下单
    public Observable<PayReqBean> weChatPay(Map<String,String> map){
        return service.weChatPay(map);
    }

    //获得微信支付状态
    public Observable<WeChatPayStateBean> weChatPayState(Map<String,String> map){
        return service.weChatPayState(map);
    }

    public Observable<ListGoodsBean> getGoodList(String type){
        return service.getGoodList(type);
    }

}
