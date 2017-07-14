package com.yizhisha.taosha;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by lan on 2017/6/22.
 */
public class App extends Application{
    private static App context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        //讯飞语音初始化工作
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5966e19d");
    }
    public static Context getAppContext() {
        return context;
    }
}
