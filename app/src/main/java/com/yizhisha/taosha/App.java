package com.yizhisha.taosha;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=59a8c40b");
        //测试上传
    }
    public static Context getAppContext() {
        return context;
    }

}
