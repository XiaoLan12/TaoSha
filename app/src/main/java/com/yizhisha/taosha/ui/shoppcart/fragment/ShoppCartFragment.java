package com.yizhisha.taosha.ui.shoppcart.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ShoppCartAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.GoodsBean;
import com.yizhisha.taosha.bean.StoreBean;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.Shopcart;
import com.yizhisha.taosha.bean.json.ShopcartGoods;
import com.yizhisha.taosha.ui.shoppcart.contract.ShoppCartContract;
import com.yizhisha.taosha.ui.shoppcart.presenter.ShoppCartPresenter;
import com.yizhisha.taosha.utils.LogUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.CommonLoadingView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by lan on 2017/6/22.
 */
public class ShoppCartFragment extends BaseFragment<ShoppCartPresenter> implements
        ShoppCartContract.View{
    @Bind(R.id.expandableListView)
    ExpandableListView expandableListView;
    @Bind(R.id.id_cb_select_all)
    CheckBox ivSelectAll;
    @Bind(R.id.btnSettle)
    TextView btnSettle;
    @Bind(R.id.tvCountMoney)
    TextView tvCountMoney;
    @Bind(R.id.rlBottomBar)
    RelativeLayout rlBottomBar;

    @Bind(R.id.loadingView)
    CommonLoadingView mLoadingView;

    RelativeLayout mRlNormal;
    RelativeLayout mRlEdit;
    TextView id_tv_edit_all;

    private ShoppCartAdapter adapter;
    //定义父列表项List数据集合
    List<Map<String, Object>> parentMapList = new ArrayList<Map<String, Object>>();
    //定义子列表项List数据集合
    List<List<Map<String, Object>>> childMapList_list = new ArrayList<List<Map<String, Object>>>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoppcart;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.setStatusBarColor(activity, Color.WHITE,125);
        }
    }
    @Override
    protected void initView() {
        initAdapter();
        mPresenter.loadShoppCart(AppConstant.UID,true);
    }

    private void initAdapter(){
        adapter= new ShoppCartAdapter(activity, parentMapList, childMapList_list);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "click：" + position, Toast.LENGTH_SHORT).show();
            }
        });


        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }
       /* ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeGoods();
                // Toast.makeText(MainActivity.this, "删除多选商品", Toast.LENGTH_SHORT).show();
            }
        });*/

        ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) v;
                    adapter.setupAllChecked(checkBox.isChecked());
                }
            }
        });

        btnSettle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "click：结算", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnGoodsCheckedChangeListener(new ShoppCartAdapter.OnGoodsCheckedChangeListener() {
            @Override
            public void onGoodsCheckedChange(int totalCount, double totalPrice) {
                tvCountMoney.setText(String.format(getString(R.string.total)+totalPrice));
                //id_tv_totalCount_jiesuan.setText(String.format(getString(R.string.jiesuan), totalCount+""));
            }
        });

        adapter.setOnAllCheckedBoxNeedChangeListener(new ShoppCartAdapter.OnAllCheckedBoxNeedChangeListener() {
            @Override
            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {
                ivSelectAll.setChecked(allParentIsChecked);
            }
        });

        adapter.setOnCheckHasGoodsListener(new ShoppCartAdapter.OnCheckHasGoodsListener() {
            @Override
            public void onCheckHasGoods(boolean isHasGoods) {
                //setupViewsShow(isHasGoods);
            }
        });
        //删除单项商品
        adapter.setOnDeleteShopListener(new ShoppCartAdapter.OnDeleteShopListener() {
            @Override
            public void onDeleteShop(int groupPosition, int childPosition) {
                adapter.removeOneGood(groupPosition,childPosition);
            }
        });

    }
    @Override
    public void loadSuccess(List<Shopcart> data) {
        for (int i = 0; i < data.size(); i++) {
            //提供父列表的数据
            Map<String, Object> parentMap = new HashMap<String, Object>();
            StoreBean store=new StoreBean();
            store.setMzw_uid(data.get(i).getMzw_uid());
            store.setCompany(data.get(i).getCompany());
            store.setChecked(false);
            store.setEditing(false);
            parentMap.put("parentName",store);
            parentMapList.add(parentMap);
            //提供当前父列的子列数据
            List<Map<String, Object>> childMapList = new ArrayList<Map<String, Object>>();
            List<ShopcartGoods> goods=data.get(i).getGoods();
            for (int j = 0; j < goods.size(); j++) {
                Map<String, Object> childMap = new HashMap<String, Object>();

                GoodsBean goodsBean=new GoodsBean();
                goodsBean.setGid(goods.get(j).getGid());
                goodsBean.setTitle(goods.get(j).getTitle());
                goodsBean.setPname(goods.get(j).getPname());
                goodsBean.setPrice(goods.get(j).getPrice());
                goodsBean.setLitpic(goods.get(j).getLitpic());
                goodsBean.setAmount(goods.get(j).getAmount());
                goodsBean.setDetail(goods.get(j).getDetail());
                goodsBean.setAddtime(goods.get(j).getAddtime());
                goodsBean.setChecked(false);
                goodsBean.setEditing(false);
                childMap.put("childName", goodsBean);
                childMapList.add(childMap);
            }
            childMapList_list.add(childMapList);
        }
        //需要展开分组，才会刷新childView
        for (int i = 0; i < parentMapList.size(); i++) {
            expandableListView.expandGroup(i);
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void deleteShoppCart() {

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
        mLoadingView.loadSuccess(true, R.drawable.icon_delete,"购物车空空的");
    }
    @Override
    public void loadFail(String msg) {

    }
    @Override
    public void deleteFail(String msg) {

    }
}
