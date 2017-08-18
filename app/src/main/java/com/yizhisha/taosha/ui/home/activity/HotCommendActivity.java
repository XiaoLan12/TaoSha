package com.yizhisha.taosha.ui.home.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.HotCommendAdapter;
import com.yizhisha.taosha.adapter.SeckillActivityAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.PopupListBean;
import com.yizhisha.taosha.bean.json.HotCommendBean;
import com.yizhisha.taosha.common.popupwindow.ListPopupwindow;
import com.yizhisha.taosha.ui.home.contract.HotCommendContract;
import com.yizhisha.taosha.ui.home.precenter.HotCommendPresenter;
import com.yizhisha.taosha.utils.DensityUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class HotCommendActivity extends BaseActivity<HotCommendPresenter>
implements HotCommendContract.View,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.toolbar_rightbutton)
    TextView rightIv;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HotCommendAdapter mAdapter;
    private List<HotCommendBean.Goods> dataLists=new ArrayList<>();
    private String curType="";
    private ListPopupwindow mPopup;
    private final String[] type = new String[]{"mafangsha", "maofangsha", "mianfangsha", "hunfangsha", "huaxiansha", "huashisha"};
    private final String[] title = new String[]{"麻纺热门推荐", "毛纺热门推荐", "棉纺热门推荐", "混纺热门推荐", "化纤热门推荐", "花式热门推荐"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotre_commend;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(HotCommendActivity.this);
            }
        });
        toolbar.showRightButton();
        toolbar.setRightButtonIcon(RescourseUtil.getDrawable(R.drawable.icon_menu));
        toolbar.setRightButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopup == null) {
                    initPopup();
                }
                if (!mPopup.isShowing()) {
                    mPopup.showringht(rightIv);
                }
            }
        });
    }
    @Override
    protected void initView() {
        initAdapter();
        mPresenter.loadHotCommend("mafangsha",true);
    }

    private void initAdapter(){
        mSwipeRefreshLayout.setColorSchemeColors(RescourseUtil.getColor(R.color.red),
                RescourseUtil.getColor(R.color.red));
        //设置刷新出现的位置
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 100);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter=new HotCommendAdapter(dataLists);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putInt("TYPE",1);
                bundle.putInt("id",dataLists.get(position).getId());
                startActivity(YarnActivity.class,bundle);
            }
        });
    }
    private void initPopup() {
        ArrayList<PopupListBean> list = new ArrayList<>();
        for (int i = 0; i < type.length; i++) {
            PopupListBean popupListBean = new PopupListBean();
            popupListBean.setType(type[i]);
            popupListBean.setTitle(title[i]);
            list.add(popupListBean);
        }
        // 实例化标题栏弹窗
        mPopup = new ListPopupwindow(this, DensityUtil.getScreenWidth(this)/2,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setItemOnClickListener(new ListPopupwindow.OnItemOnClickListener() {
            @Override
            public void onItemClick(PopupListBean item, int position) {
                mPresenter.loadHotCommend(item.getType(),false);
                toolbar.setTitle(item.getTitle());
            }
        });
        // 给标题栏弹窗添加子类
        mPopup.addAction(list);
        mPopup.setItemSelected(-1);
    }
    @Override
    public void loadSuccess(HotCommendBean bean) {
        dataLists.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        dataLists.addAll(bean.getGoods());
        mAdapter.setNewData(dataLists);
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
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
               mPresenter.loadHotCommend(curType,true);
            }
        });
    }
    @Override
    public void onRefresh() {
        mPresenter.loadHotCommend(curType,false);
    }
}
