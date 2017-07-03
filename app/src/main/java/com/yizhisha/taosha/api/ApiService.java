package com.yizhisha.taosha.api;

import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
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
    Observable<CollectListBean> getCollectList(@Query("uid") int uid,@Query("pid") int pid);
}
