package com.yizhisha.taosha.api;

import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
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
    Observable<RequestStatusBean> addAddress(@QueryMap Map<String,String> msp);

}
