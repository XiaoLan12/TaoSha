package com.yizhisha.taosha.ui.home.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.adapter.SelectYarnColorAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.SelectYarnBean;
import com.yizhisha.taosha.bean.json.ProductDetailBean;
import com.yizhisha.taosha.ui.home.contract.SelectYarnColorContract;
import com.yizhisha.taosha.ui.home.precenter.SelectYarnColorPresenter;
import com.yizhisha.taosha.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/19.
 */

public class SelectYarnColorActivity extends BaseActivity<SelectYarnColorPresenter> implements SelectYarnColorContract.View,View.OnClickListener{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.recycleview1)
    RecyclerView recyclerView1;
    private ProductDetailImgAdapter adapter;
    private SelectYarnColorAdapter adapter1;
    private List<SelectYarnBean> list=new ArrayList<>();
    private ProductDetailBean productDetailBean=null;

    private int type;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_yarn_color;
    }

    @Override
    protected void initToolBar() {
        Bundle bundle=getIntent().getExtras();
        type=bundle.getInt("TYPE");
        if(type==1) {
            toolbar.setTitle("立即购买");
        }else{
            toolbar.setTitle("加入购物车");
        }
    }

    @Override
    protected void initView() {
        ArrayList<String> sekaList=new ArrayList<>();
        Bundle bundle=getIntent().getExtras();
        sekaList.addAll(bundle.getStringArrayList("DATA"));
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(SelectYarnColorActivity.this);
        linearLayoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager4);
        SelectYarnBean selectYarnBean=new SelectYarnBean();
        selectYarnBean.setColor("");
        selectYarnBean.setNum(0);
        selectYarnBean.setAdd(true);
        list.add(selectYarnBean);
        adapter1=new SelectYarnColorAdapter(SelectYarnColorActivity.this,list);
        recyclerView1.setAdapter(adapter1);
        adapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.img_delete:
                        if(list.get(position).isAdd()) {
                            if (list.size() > 7) {
                                ToastUtil.showShortToast("一个订单最多八组");
                                return;
                            }
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getColor().equals("")||list.get(i).getNum()==0) {
                                    ToastUtil.showShortToast("请先输入色号和数量");
                                    return;
                                }
                            }
                            SelectYarnBean selectYarnBean = new SelectYarnBean();
                            selectYarnBean.setColor("");
                            selectYarnBean.setNum(0);
                            selectYarnBean.setAdd(false);
                            list.add(0, selectYarnBean);
                            adapter1.setNewData(list);
                        }else{
                            adapter1.remove(position);
                        }
                        break;
                }
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SelectYarnColorActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(linearLayoutManager);
         adapter=new ProductDetailImgAdapter(SelectYarnColorActivity.this,sekaList);
         recyclerView.setAdapter(adapter);
    }
    @OnClick({R.id.sure_btn})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.sure_btn:
                if(type==1) {
                    StringBuilder str = new StringBuilder();
                    int amount = 0;
                    String detail = "";
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getColor().equals("")||list.get(i).getNum()==0) {
                            ToastUtil.showShortToast("请先输入色号和数量");
                            return;
                        }
                        str.append(list.get(i).getColor()).append("#");
                        str.append(list.get(i).getNum()).append("，");
                        amount += list.get(i).getNum();
                    }
                    if (str.length() > 0) {
                        detail = str.substring(0, str.length() - 1);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("gid", Integer.valueOf(productDetailBean.getGoods().getId()));
                    bundle.putInt("ORDERTYPE", 1);
                    bundle.putString("detail", detail);
                    bundle.putString("type", "order");
                    bundle.putInt("amount", amount);
                    startActivity(SureOrderActivity.class, bundle);
                }else{
                    StringBuilder str=new StringBuilder();
                    int amount=0;
                    for(int i=0;i<list.size();i++){
                        str.append(list.get(i).getColor()).append("#");
                        str.append(list.get(i).getNum()).append("，");
                        amount+=list.get(i).getNum();
                    }
                    if(str.length()<=0){
                        ToastUtil.showShortToast("请选择商品");
                    }
                    Map<String,String> map=new HashMap<>();
                    map.put("gid",String.valueOf(productDetailBean.getGoods().getId()));
                    map.put("uid",String.valueOf(AppConstant.UID));
                    map.put("savetype","add");
                    map.put("amount",String.valueOf(amount));
                    map.put("detail",str.substring(0,str.length()-1));
                    mPresenter.changeShopCart(map);
                }
                break;
        }
    }
    @Override
    public void changeShopCartSuccess(String msg) {
        ToastUtil.showShortToast("商品已添加到购物车");
        finish_Activity(this);
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }
}
