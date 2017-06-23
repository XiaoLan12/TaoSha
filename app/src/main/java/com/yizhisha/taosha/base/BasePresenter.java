package com.yizhisha.taosha.base;

import android.content.Context;

/**
 * Created by lan on 2017/6/22.
 */
public abstract class BasePresenter<V> {
    public Context mContext;
    public V mView;

    /**
     * @param v\
     * 设置View
     */
    public void setV(V v){
        this.mView=v;
    }
    public void onDestroy(){
        this.mView=null;
    }
    //子类实现，可做初始化操作
    public abstract void onStart();
}
