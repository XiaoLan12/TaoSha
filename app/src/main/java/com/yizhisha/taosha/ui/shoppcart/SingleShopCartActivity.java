package com.yizhisha.taosha.ui.shoppcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.adapter.ShopCartAddShopAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.ShopCartBean;
import com.yizhisha.taosha.common.dialog.PicShowDialog;
import com.yizhisha.taosha.ui.shoppcart.contract.SingleShopCartContract;
import com.yizhisha.taosha.ui.shoppcart.presenter.SingleShopCartPresenter;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class SingleShopCartActivity extends BaseActivity<SingleShopCartPresenter>
        implements SingleShopCartContract.View {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;
    @Bind(R.id.recycleview1)
    RecyclerView mRecycleview1;

    private ProductDetailImgAdapter mProductDetailImgAdapter;
    private ShopCartAddShopAdapter mShopCartAddShopAdapter;
    private List<ShopCartBean.ShopcartList> dataList;
    private List<String> imgList;

    private int gid;
    private int sid;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_shop_cart;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(SingleShopCartActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            gid=bundle.getInt("gid",0);
            sid=bundle.getInt("sid",0);
        }
        dataList=new ArrayList<>();
        imgList=new ArrayList<>();
        initAdapter();
        load(true);
    }
    private void load(boolean isShowLoad){
        Map<String,String> map=new HashMap<>();
        map.put("gid",String.valueOf(gid));
        map.put("sid",String.valueOf(sid));
        mPresenter.loadSingleShoppCart(map,isShowLoad);
    }
    private void initAdapter() {
        mProductDetailImgAdapter = new ProductDetailImgAdapter(this,imgList);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setAdapter(mProductDetailImgAdapter);
        mProductDetailImgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicShowDialog dialog=new PicShowDialog(SingleShopCartActivity.this,imgList,position);
                dialog.show();
            }
        });

        mShopCartAddShopAdapter = new ShopCartAddShopAdapter(dataList);
        mRecycleview1.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleview1.setAdapter(mShopCartAddShopAdapter);
        mRecycleview1.addItemDecoration(new RecyclerViewDriverLine(this, LinearLayoutManager.VERTICAL));
        mShopCartAddShopAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.img_delete:
                        if(dataList.get(position).isAdd()){
                            if(dataList.size()>7){
                                ToastUtil.showShortToast("一个订单最多八组");
                                return;
                            }
                            for(int i=0;i<dataList.size();i++){
                                if(dataList.get(i).getDetail().equals("")){
                                    ToastUtil.showShortToast("请先输入色号");
                                    return;
                                }
                            }
                            ShopCartBean.ShopcartList shopcartList=new ShopCartBean().new ShopcartList();
                            shopcartList.setAmount(1);
                            shopcartList.setDetail("");
                            shopcartList.setAdd(false);
                            dataList.add(0,shopcartList);
                            mShopCartAddShopAdapter.setNewData(dataList);
                        }else {
                            mShopCartAddShopAdapter.remove(position);
                        }
                        break;
                }
            }
        });
    }
    @Override
    public void loadSuccess(ShopCartBean data) {
        if(data.getShopcart()!=null&&data.getShopcart().size()>0){
            dataList.clear();
            dataList.addAll(data.getShopcart());
            dataList.get(dataList.size()-1).setAdd(true);
            mShopCartAddShopAdapter.setNewData(dataList);
        }else{
            ShopCartBean.ShopcartList shopcartList=new ShopCartBean().new ShopcartList();
            shopcartList.setAmount(0);
            shopcartList.setDetail("");
            shopcartList.setAdd(true);
            dataList.add(shopcartList);

        }
        if(data.getSeka()!=null&&data.getSeka().size()>0) {
            imgList.clear();
            imgList.addAll(data.getSeka());
            for(int i=0;i<imgList.size();i++){
                imgList.set(i,AppConstant.PRODUCT_DETAIL_SEKA_IMG_URL+imgList.get(i));
            }
            mProductDetailImgAdapter.setNewData(imgList);
        }else{
            showEmpty();
        }

    }

    @Override
    public void changeShopCartSuccess(String msg) {
        ToastUtil.showShortToast(msg);
        setResult(2);
        finish_Activity(this);

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
        mShopCartAddShopAdapter.setNewData(dataList);
        mLoadingView.loadSuccess(true);
    }
    @Override
    public void loadFail(String msg) {
        dataList.clear();
        mShopCartAddShopAdapter.setNewData(dataList);
        mLoadingView.loadError();
        mLoadingView.setLoadingHandler(new CommonLoadingView.LoadingHandler() {
            @Override
            public void doRequestData() {
                load(true);
            }
        });
    }
    @OnClick({R.id.sure_btn})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.sure_btn:
                StringBuilder str=new StringBuilder();
                int amount = 0;
                String detail = "";
                for(int i=0;i<dataList.size();i++){
                    if (dataList.get(i).getDetail().equals("")){
                        ToastUtil.showShortToast("请输入6位数内的色号");
                        return;
                    }
                    if (dataList.get(i).getAmount()==0) {
                        ToastUtil.showShortToast("请输入购买的数量数量");
                        return;
                    }
                    str.append(dataList.get(i).getDetail()).append("#");
                    str.append(dataList.get(i).getAmount()).append("，");
                    amount += dataList.get(i).getAmount();
                }
                if (str.length() > 0) {
                    detail = str.substring(0, str.length() - 1);
                }
                Map<String,String> map=new HashMap<>();
                map.put("gid",String.valueOf(gid));
                map.put("uid",String.valueOf(AppConstant.UID));
                map.put("savetype","edit");
                map.put("amount",String.valueOf(detail));
                map.put("detail",detail);
                mPresenter.changeShopCart(map);
                break;
        }

    }
}
