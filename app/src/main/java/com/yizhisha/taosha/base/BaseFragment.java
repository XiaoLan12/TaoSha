package com.yizhisha.taosha.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.utils.TUtil;

import butterknife.ButterKnife;

/**
 * Created by lan on 2017/6/22.
 * Fragment基类
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements View.OnClickListener{
    protected P mPresenter;
    protected View mFragmentRootView;
    protected FragmentActivity activity;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentRootView = inflater.inflate(getLayoutId(), container, false);
        activity = getSupportActivity();
        mContext = activity;
        return mFragmentRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,mFragmentRootView);
        mPresenter = TUtil.getT(this, 0);
        if(mPresenter!=null){
            mPresenter.mContext=activity;
            mPresenter.setV(this);
        }
        initView();
    }

    /**
     * @return
     * 获得管理的Activity
     */
    protected FragmentActivity getSupportActivity() {
        return super.getActivity();
    }
    //加载布局文件
    protected abstract int getLayoutId();
    //初始化操作
    protected abstract void initView();

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
        intent.setClass(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.onDestroy();
        }
        activity=null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {

    }
}
