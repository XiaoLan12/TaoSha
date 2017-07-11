package com.yizhisha.taosha.ui.me.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.AreaAdapter;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.AreaInfo;
import com.yizhisha.taosha.ui.home.activity.YarnActivity;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lan on 2017/7/11.
 */

public class AreaFragment extends BaseFragment{
    private static final String ARG_PARAM1 = "parentCode";
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    private String mParam1;
    private OnFragmentInteractionListener mListener;

    private AreaAdapter mAdapter;
    private List<AreaInfo> dataLists=new ArrayList<>();
    public static AreaFragment newInstance(String param1) {
        AreaFragment fragment = new AreaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_area;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    protected void initView() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        initAdapter();
    }
    private void initAdapter() {

        mAdapter = new AreaAdapter(dataLists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(AreaInfo areaInfo);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
