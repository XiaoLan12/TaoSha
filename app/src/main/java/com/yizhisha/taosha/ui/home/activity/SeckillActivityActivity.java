package com.yizhisha.taosha.ui.home.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyCollectAdapter;
import com.yizhisha.taosha.adapter.SeckillActivityAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.SeckillActBean;
import com.yizhisha.taosha.bean.json.SeckillActListBean;
import com.yizhisha.taosha.bean.json.SeckillGoodsBean;
import com.yizhisha.taosha.ui.home.contract.SeckillActivityContract;
import com.yizhisha.taosha.ui.home.precenter.SeckillActivityPresenter;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class SeckillActivityActivity extends BaseActivity<SeckillActivityPresenter>
implements SeckillActivityContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private SeckillActivityAdapter mAdapter;
    private List<SeckillActBean> dataLists=new ArrayList<>();
    MyThread   timeThread;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_seckill_activity;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        initAdapter();
        load(true);
    }
    //发送网络请求
    private void load(boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        mPresenter.loadSeckillActivity(map,isShowLoad);
    }

    private void initAdapter(){
      /*  mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);
*/
        mAdapter=new SeckillActivityAdapter(dataLists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
    }
    @Override
    public void loadSuccess(SeckillActListBean data) {
        dataLists.clear();
        //mSwipeRefreshLayout.setRefreshing(false);
        dataLists.addAll(data.getSeckilling());
        for(int i=0;i<dataLists.size();i++){
            dataLists.get(i).setNowtime(data.getNowtime());
        }
        mAdapter.setNewData(dataLists);
//        timeThread = new MyThread();
//        new Thread(timeThread).start();
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
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataLists);
        mLoadingView.loadSuccess(true);
    }

    @Override
    public void loadFail(String msg) {
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataLists);
        mLoadingView.loadError();
    }

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    //刷新适配器
                    //    mRecommendActivitiesAdapter.notifyDataSetChanged();
                    //优化刷新adapter的方法
                    mAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    class MyThread implements Runnable{
        //用来停止线程
        boolean endThread;

        @Override
        public void run() {
            while(!endThread){
                try{
                    Thread.sleep(1000);
                    Message message = new Message();
                    message.what = 1;
                    //发送信息给handler
                    handler.sendMessage(message);
                }catch (Exception e){

                }
            }
        }
    }
}
