package com.yizhisha.taosha.ui.me.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.FreeSampleAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.ui.home.activity.YarnActivity;
import com.yizhisha.taosha.ui.me.contract.FreeSampleContract;
import com.yizhisha.taosha.ui.me.presenter.FreeSamplePresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by lan on 2017/7/5.
 */

public class FreeSampleFragment extends BaseFragment<FreeSamplePresenter> implements
        FreeSampleContract.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private FreeSampleAdapter mAdapter;
    private int mType=0;
    private List<FreeSampleBean.Active> dataList=new ArrayList<>();
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    public static FreeSampleFragment getInstance(int type) {
        FreeSampleFragment sf = new FreeSampleFragment();
        sf.mType = type;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_freesample;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(isPrepared){
                onRefresh();
            }

        }
    }
    @Override
    protected void initView() {
        isPrepared=true;
        initAdapter();
        dataList.clear();
        if(mAdapter.getData().size()<=0){
            load(mType,true);
        }
    }
    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter=new FreeSampleAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()){
                    case R.id.cancel_the_order_tv:
                        new NormalAlertDialog.Builder(activity)
                                .setBoolTitle(false)
                                .setContentText("确认取消订单？")
                                .setContentTextSize(18)
                                .setLeftText("取消")
                                .setRightText("确认")
                                .setWidth(0.75f)
                                .setHeight(0.33f)
                                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                                    @Override
                                    public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void clickRightButton(NormalAlertDialog dialog, View view) {
                                        Map<String, String> bodyMap = new HashMap<>();
                                        bodyMap.put("uid",String.valueOf(AppConstant.UID));
                                        bodyMap.put("id",String.valueOf(dataList.get(position).getId()));
                                        mPresenter.cancleFreeSample(bodyMap);
                                        dialog.dismiss();

                                    }
                                }).build().show();

                        break;
                    case R.id.contact_the_merchant_tv:
                        final String phone=dataList.get(position).getMobile();
                            new NormalAlertDialog.Builder(activity)
                                    .setBoolTitle(true)
                                    .setBoolSubtitleTitle(true)
                                    .setTitleText(dataList.get(position).getCompany())
                                    .setSubtitleTitleText(dataList.get(position).getLinkman())
                                    .setContentText(dataList.get(position).getMobile())
                                    .setContentTextSize(18)
                                    .setLeftText("取消")
                                    .setRightText("呼叫")
                                    .setWidth(0.75f)
                                    .setHeight(0.33f)
                                    .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                                        @Override
                                        public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void clickRightButton(NormalAlertDialog dialog, View view) {
                                            Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phone));
                                            startActivity(phoneIneten);
                                            dialog.dismiss();

                                        }
                                    }).build().show();
                        break;
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id", dataList.get(position).getGid());
                startActivity(YarnActivity.class, bundle);
            }
        });
    }
    private void load(int type,boolean isShowLoad){
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("uid",String.valueOf(AppConstant.UID));
        if(type!=-1){
            bodyMap.put("status",String.valueOf(type));
        }
        mPresenter.loadFreeSample(bodyMap,isShowLoad);
    }
    public void search(String key){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        if(mType!=-1) {
            map.put("status", String.valueOf(mType));
        }
        map.put("key",key);
        mPresenter.loadFreeSample(map,false);
    }
    @Override
    public void onRefresh() {
        load(mType,false);
    }
    @Override
    public void loadFreeSampleSuccess(List<FreeSampleBean.Active> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        dataList.clear();
        dataList.addAll(data);
        mAdapter.setNewData(data);
    }

    @Override
    public void cancleFreeSample(String msg) {
        onRefresh();
        ToastUtil.showCenterLongToast(msg);
    }
    @Override
    public void showLoading() {
        mLoadingView.load("");
    }

    @Override
    public void hideLoading() {
        mLoadingView.loadSuccess();
    }
    @Override
    public void showEmpty() {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(String msg) {
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(mType,true);
            }
        });
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
    }

    @Override
    public void cancleFreeSampleFail(String msg) {
        ToastUtil.showShortToast(msg);
    }

}
