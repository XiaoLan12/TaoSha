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
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.SelectYarnBean;
import com.yizhisha.taosha.bean.json.ProductDeatilItemBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.common.dialog.PicShowDialog;
import com.yizhisha.taosha.event.UpdateShopCartEvent;
import com.yizhisha.taosha.ui.home.contract.SelectYarnColorContract;
import com.yizhisha.taosha.ui.home.precenter.SelectYarnColorPresenter;
import com.yizhisha.taosha.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.taosha.ui.login.activity.RegisterActivity;
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
    @Bind(R.id.buynum_tv)
    TextView buyNumTv;
    private ProductDetailImgAdapter adapter;
    private SelectYarnColorAdapter adapter1;
    private List<SelectYarnBean> list=new ArrayList<>();
    private ProductDeatilItemBean productDeatilItemBean=null;
    private  ArrayList<String> sekaList=new ArrayList<>();

    private int type;
    private boolean isBanmao=false;

    private NormalSelectionDialog dialog;
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
        productDeatilItemBean= (ProductDeatilItemBean) bundle.getSerializable("DATA");
        isBanmao=bundle.getBoolean("ISBANMAO",false);
        if(isBanmao){
            buyNumTv.setText("购买数量(份)");
        }
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(SelectYarnColorActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        sekaList.addAll(productDeatilItemBean.getSeka());
        for(int i=0;i<sekaList.size();i++){
            sekaList.set(i,AppConstant.PRODUCT_DETAIL_SEKA_IMG_URL+sekaList.get(i));
        }
        LinearLayoutManager linearLayoutManager4=new LinearLayoutManager(SelectYarnColorActivity.this);
        linearLayoutManager4.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager4);
        SelectYarnBean selectYarnBean=new SelectYarnBean();
        selectYarnBean.setColor("");
        selectYarnBean.setNum(1);
        selectYarnBean.setAdd(true);
        list.add(selectYarnBean);
        adapter1=new SelectYarnColorAdapter(SelectYarnColorActivity.this,list);
        recyclerView1.setAdapter(adapter1);
        if(!isBanmao) {
            adapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.img_delete:
                            if (list.get(position).isAdd()) {
                                for (int i = 0; i < list.size(); i++) {
                                    if (list.get(i).getColor().equals("")) {
                                        ToastUtil.showShortToast("请先输入色号");
                                        return;
                                    }
                                }
                                if (list.size() > 7) {
                                    ToastUtil.showShortToast("一个订单最多八组");
                                    return;
                                }
//                                for (int i = 0; i < list.size(); i++) {
//                                   list.get(i).setAdd(false);
//                                }
                                SelectYarnBean selectYarnBean = new SelectYarnBean();
                                selectYarnBean.setColor("");
                                selectYarnBean.setNum(1);
                                selectYarnBean.setAdd(false);
                                list.add(0,selectYarnBean);
                                adapter1.setNewData(list);
                            } else {
                                adapter1.remove(position);
                            }
                            break;
                        case R.id.tv_add:
                            int num= list.get(position).getNum();
                            num++;
                            list.get(position).setNum(num);
                            adapter1.notifyItemChanged(position);
                            break;
                        case R.id.tv_reduce:
                            int num1= list.get(position).getNum();

                            if(num1>1){
                                num1--;
                                list.get(position).setNum(num1);
                                adapter1.notifyItemChanged(position);
                            }
                            break;
                    }
                }
            });
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SelectYarnColorActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(linearLayoutManager);
        adapter=new ProductDetailImgAdapter(SelectYarnColorActivity.this,sekaList);
         recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                PicShowDialog dialog=new PicShowDialog(SelectYarnColorActivity.this,sekaList,position);
                dialog.show();
            }
        });
    }
    private void showLoginDialog(){
        final List<String> mDatas1=new ArrayList<>();
        mDatas1.add("登录");
        mDatas1.add("注册");
        dialog=new NormalSelectionDialog.Builder(this)
                .setBoolTitle(true)
                .setTitleText("温馨提示\n尊敬的用户,您尚未登录,请选择登录或注册")
                .setTitleHeight(55)
                .setItemHeight(45)
                .setItemTextColor(R.color.blue)
                .setItemTextSize(14)
                .setItemWidth(0.95f)
                .setCancleButtonText("取消")
                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                    @Override
                    public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                        switch (position){
                            case 0:
                                startActivity(LoginFragmentActivity.class);
                                break;
                            case 1:
                                startActivity(RegisterActivity.class);
                                break;
                        }
                        dialog.dismiss();
                    }
                }).setTouchOutside(true)
                .build();
        dialog.setData(mDatas1);
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
                        if (list.get(i).getColor().equals("")){
                            ToastUtil.showShortToast("请输入6位数内的色号");
                            return;
                        }
                        str.append(list.get(i).getColor()).append("#");
                        str.append(list.get(i).getNum()).append("，");
                        amount += list.get(i).getNum();
                    }
                    if(AppConstant.isLogin==false){
                        if(dialog==null){
                            showLoginDialog();
                        }
                        dialog.show();
                        return;
                    }
                    if (str.length() > 0) {
                        detail = str.substring(0, str.length() - 1);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("gid", Integer.valueOf(productDeatilItemBean.getId()));
                    bundle.putInt("ORDERTYPE", 1);
                    bundle.putString("detail", detail);
                    if(isBanmao) {
                        bundle.putString("type", "banmao");
                    }else{
                        bundle.putString("type", "order");
                    }
                    bundle.putInt("amount", amount);
                    startActivity(SureOrderActivity.class, bundle);
                }else{
                    StringBuilder str=new StringBuilder();
                    int amount = 0;
                    String detail = "";
                    for(int i=0;i<list.size();i++){
                        if (list.get(i).getColor().equals("")){
                            ToastUtil.showShortToast("请输入6位数内的色号");
                            return;
                        }
                        if (list.get(i).getNum()==0) {
                            ToastUtil.showShortToast("请输入购买的数量数量");
                            return;
                        }
                        str.append(list.get(i).getColor()).append("#");
                        str.append(list.get(i).getNum()).append("，");
                        amount+=list.get(i).getNum();
                    }
                    if(AppConstant.isLogin==false){
                        if(dialog==null){
                            showLoginDialog();
                        }
                        dialog.show();
                        return;
                    }
                    if(str.length()<=0){
                        detail = str.substring(0, str.length() - 1);
                    }
                    Map<String,String> map=new HashMap<>();
                    map.put("gid",String.valueOf(productDeatilItemBean.getId()));
                    map.put("uid",String.valueOf(AppConstant.UID));
                    map.put("savetype","add");
                    map.put("amount",String.valueOf(amount));
                    map.put("detail",detail);
                    mPresenter.changeShopCart(map);
                }
                break;
        }
    }
    @Override
    public void changeShopCartSuccess(String msg) {
        ToastUtil.showShortToast("商品已添加到购物车");
        RxBus.$().postEvent(new UpdateShopCartEvent());
        finish_Activity(this);
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }
}
