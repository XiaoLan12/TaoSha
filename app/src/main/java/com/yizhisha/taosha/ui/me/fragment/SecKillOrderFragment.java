package com.yizhisha.taosha.ui.me.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.SecondKillOrderAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.bean.json.SeckillBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.ui.me.activity.SecKillOrderDetailActivity;
import com.yizhisha.taosha.ui.me.contract.SecKillOrderContract;
import com.yizhisha.taosha.ui.me.presenter.SecKillOrderPresenter;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/7/11.
 */

public class SecKillOrderFragment extends BaseFragment<SecKillOrderPresenter>
        implements SwipeRefreshLayout.OnRefreshListener,SecKillOrderContract.View{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private SecondKillOrderAdapter mAdapter;
    private int mType=0;
    private ArrayList<SeckillBean> dataList=new ArrayList<>();

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    public static SecKillOrderFragment getInstance(int type) {
        SecKillOrderFragment sf = new SecKillOrderFragment();
        sf.mType = type;
        return sf;
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
    protected int getLayoutId() {
        return R.layout.fragment_freesample;
    }
    @Override
    protected void initView() {
        isPrepared=true;
        initAdapter();
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter=new SecondKillOrderAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString("ORDERNO",dataList.get(position).getOrderno());
                startActivityForResult(SecKillOrderDetailActivity.class,bundle,100);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.cancel_the_order_tv:
                            Map<String, String> bodyMap = new HashMap<>();
                            bodyMap.put("uid", String.valueOf(AppConstant.UID));
                            bodyMap.put("orderno", String.valueOf(dataList.get(position).getOrderno()));
                            mPresenter.cancleOrder(bodyMap);
                        break;
                    case R.id.contact_the_merchant_tv:
                        final String phone=dataList.get(position).getGoods().getMobile();
                        new NormalAlertDialog.Builder(activity)
                                .setBoolTitle(false)
                                .setContentText(phone)
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
                                        Intent phoneIneten = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phone));
                                        startActivity(phoneIneten);
                                        dialog.dismiss();

                                    }
                                }).build().show();
                        break;
                }
            }
        });

    }
    private void load(int type,boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        if(type!=0) {
            map.put("status", String.valueOf(type));
        }
        mPresenter.loadSeckillOrder(map,isShowLoad);
    }
    public void search(String key){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        if(mType!=0) {
            map.put("status", String.valueOf(mType));
        }
        map.put("key",key);
        mPresenter.loadSeckillOrder(map,false);
    }
    @Override
    public void loadSuccess(List<SeckillBean> data) {
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataList.addAll(data);
        mAdapter.setNewData(dataList);
    }

    @Override
    public void cancleOrder(String msg) {
        onRefresh();
        ToastUtil.showCenterLongToast(msg);
    }

    @Override
    public void showLoading() {
        mLoadingView.load();
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
        dataList.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
    }

    @Override
    public void cancelFail(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public void onRefresh() {
        load(mType,false);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            onRefresh();
    }
}
