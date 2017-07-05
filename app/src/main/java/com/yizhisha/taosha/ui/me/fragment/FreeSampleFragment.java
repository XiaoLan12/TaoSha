package com.yizhisha.taosha.ui.me.fragment;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.FreeSampleAdapter;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.base.BaseRVFragment;
import com.yizhisha.taosha.bean.json.CollectListBean;
import com.yizhisha.taosha.bean.json.FreeSampleBean;
import com.yizhisha.taosha.ui.me.contract.FreeSampleContract;
import com.yizhisha.taosha.ui.me.presenter.FreeSamplePresenter;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by lan on 2017/7/5.
 */

public class FreeSampleFragment extends BaseRVFragment<FreeSamplePresenter,FreeSampleBean.Active> implements
        FreeSampleContract.View{
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    private int mType=0;
    private List<FreeSampleBean.Active> dataList=new ArrayList<>();

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
    protected void initView() {
        initAdapter(new FreeSampleAdapter(dataList),true,false);
        dataList.clear();
        if(mAdapter.getData().size()<=0){
            load(mType,true);
        }
    }
    private void load(int type,boolean isShowLoad){
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("uid",String.valueOf(240));
        if(type!=-1){
            bodyMap.put("status",String.valueOf(type));
        }
        mPresenter.loadFreeSample(bodyMap,isShowLoad);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        load(mType,false);
    }

    @Override
    public void loadFreeSampleSuccess(List<FreeSampleBean.Active> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        dataList.clear();
        mAdapter.setNewData(data);
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
}
