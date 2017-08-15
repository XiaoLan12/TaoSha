package com.yizhisha.taosha.ui.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.CommentYarnAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.json.CommentBean;
import com.yizhisha.taosha.ui.home.contract.CommentYarnContract;
import com.yizhisha.taosha.ui.home.precenter.CommentYarnPresenter;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class CommentYarnActivity extends BaseActivity<CommentYarnPresenter> implements
        CommentYarnContract.View  {
    @Bind(R.id.comment_rv)
    RecyclerView mRecyclerView;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    private List<CommentBean> dataList=new ArrayList<>();
    private CommentYarnAdapter mAdapter;
    private int id;
    private int type;
    private final String COMMENTURL="http://www.taoshamall.com/data/attached/comment/";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_yarn;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            id=bundle.getInt("ID");
        }
        initAdapter();
        load(id,0, true);
    }
    private void initAdapter(){
        mAdapter=new CommentYarnAdapter(dataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));

    }
    private void load(int id, int type,boolean isShowLoad) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("id", String.valueOf(982));
        if(type!=0) {
            bodyMap.put("type", String.valueOf(type));
        }
        mPresenter.loadCommentList(bodyMap, isShowLoad);
    }
    @Override
    public void loadCommentListSuccess(List<CommentBean> data) {
        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).getComment_photos()!= null&&!"".equals(data.get(i).getComment_photos())) {
                String date[]=data.get(i).getComment_photos().split(",");
                List<String> list=new ArrayList<>();
                for (int j = 0; j < date.length; j++) {
                    list.add(COMMENTURL+date[j]);
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
                load(id,type,true);
            }
        });
        dataList.clear();
        mAdapter.setNewData(dataList);
        mLoadingView.loadError();
    }
    @OnClick({R.id.allcoment_tv,R.id.goodcoment_tv,R.id.mediumcomment_tv,
            R.id.badcomment_tv, R.id.havepiccomment_tv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.allcoment_tv:
                type=0;
                load(id,type,false);
                break;
            case R.id.goodcoment_tv:
                type=1;
                load(id,type,false);
                break;
            case R.id.mediumcomment_tv:
                type=2;
                load(id,type,false);
                break;
            case R.id.badcomment_tv:
                type=3;
                load(id,type,false);
                break;
            case R.id.havepiccomment_tv:
                type=4;
                load(id,type,false);
                break;
        }
    }
}
