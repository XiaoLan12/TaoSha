package com.yizhisha.taosha.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.utils.TUtil;

import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/6/22.
 * Activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements View.OnClickListener{
    //使用泛型定义Presenter
    protected T mPresenter;
    //上下文
    protected Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        this.setContentView(getLayoutId());
        StatusBarCompat.setStatusBarColor(this, Color.WHITE,125);
        //添加注解
        ButterKnife.bind(this);
        mContext=this;
        /*
        通过反射获得Presenter,设置View
         */
        mPresenter= TUtil.getT(this,0);
        if(mPresenter!=null){
            mPresenter.setV(this);
            mPresenter.mContext=this;
        }
        this.initToolBar();
        this.initView();

        //把Activity添加到集合，统一管理
        ActivityManager.getActivityMar().addActivity(this);

    }
    /*********************子类实现*****************************/
    //获取布局文件
    protected abstract int getLayoutId();
    //初始化ActionBar
    protected abstract void initToolBar();
    //初始化view
    protected abstract void initView();
    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivityForResult(Class<?> cls, Bundle bundle,
                                          int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    /**
     * 关闭Activity
     * @param activity
     */
    protected void finish_Activity(Activity activity){
        activity.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.onDestroy();
        }
        ButterKnife.unbind(this);
        //Activity销毁时，从集合移除
        ActivityManager.getActivityMar().finishActivity(this);
    }

    @Override
    public void onClick(View v) {

    }
}
