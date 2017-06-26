package com.yizhisha.taosha.ui.me.accountingcenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.AccountCenterAdapter;
import com.yizhisha.taosha.base.BaseRVActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class AccountCenterActivity extends BaseRVActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_center;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {

        initAdapter(new AccountCenterAdapter(getAllData()),true,false);
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext, LinearLayoutManager.VERTICAL));
    }
    private List<String> getAllData(){
        List<String> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add(i+"");
        }
        return list;
    }
}
