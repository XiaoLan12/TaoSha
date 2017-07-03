package com.yizhisha.taosha.base.rx;
import android.app.Activity;
import android.content.Context;

import com.yizhisha.taosha.App;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.common.dialog.LoadingDialog;
import com.yizhisha.taosha.utils.NetWorkUtil;
import com.yizhisha.taosha.utils.RescourseUtil;

import rx.Subscriber;
/**
 * Created by lan on 2016/7/4.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {
    private Context mContext;
    private String msg;
    private boolean showDialog=true;
    public RxSubscriber(Context context, String msg, boolean showDialog){
        this.mContext = context;
        this.msg = msg;
        this.showDialog=showDialog;
    }
    public RxSubscriber(Context context) {
        this(context, App.getAppContext().getString(R.string.loading),true);
    }
    public RxSubscriber(Context context, boolean showDialog) {
        this(context, App.getAppContext().getString(R.string.loading),showDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.showDialogForLoading((Activity) mContext,msg,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCompleted(){
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
    }
    @Override
    public void onError(Throwable e) {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        //网络
        if (!NetWorkUtil.isNetConnected(App.getAppContext())) {
            onFailure(RescourseUtil.getString(R.string.no_net));
        }
        //服务器
        else{
            onFailure("访问失败");
        }

    }
    @Override
    public void onNext(T t) {
        onSuccess(t);

    }
    protected abstract void onSuccess(T t);

    protected abstract void onFailure(String message);
}
