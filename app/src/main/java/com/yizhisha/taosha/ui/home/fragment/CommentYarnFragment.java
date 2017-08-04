package com.yizhisha.taosha.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.CommentYarnAdapter;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.CommentBean;
import com.yizhisha.taosha.ui.home.activity.YarnActivity;
import com.yizhisha.taosha.ui.home.contract.CommentYarnContract;
import com.yizhisha.taosha.ui.home.precenter.CommentYarnPresenter;
import com.yizhisha.taosha.utils.LogUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/2.
 */

public class CommentYarnFragment extends BaseFragment<CommentYarnPresenter> implements
        CommentYarnContract.View {

    @Bind(R.id.comment_rv)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    private List<CommentBean> dataList=new ArrayList<>();
    private CommentYarnAdapter mAdapter;
    private int id;

    public static CommentYarnFragment getInstance(int id) {
        CommentYarnFragment sf = new CommentYarnFragment();
        sf.id = id;
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commentyarn;
    }

    @Override
    protected void initView() {
        initAdapter();
        load(id, true);
    }
    private void initAdapter(){
        mAdapter=new CommentYarnAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));

    }
    private void load(int id, boolean isShowLoad) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("id", String.valueOf(982));
        mPresenter.loadCommentList(bodyMap, isShowLoad);
    }

    @Override
    public void loadCommentListSuccess(List<CommentBean> data) {
        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).getComment_photos()!= null||!"".equals(data.get(i).getComment_photos())) {
                String date[]=data.get(i).getComment_photos().split(",");
                List<String> list=new ArrayList<>();
                for (int j = 0; j < date.length; j++) {
                    list.add(date[j]);
                }
                data.get(i).setcommentPhotos(list);
                data.get(i).setItemType(CommentBean.IMGS_TYPE);
                }else {

                data.get(i).setItemType(CommentBean.TEXT_TYPE);
                }
            }
        dataList.clear();
        dataList.addAll(data);
        mAdapter.setNewData(dataList);
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
        mAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(String msg) {
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(id,true);
            }
        });
        dataList.clear();
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
