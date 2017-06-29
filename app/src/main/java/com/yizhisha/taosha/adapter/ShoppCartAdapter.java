package com.yizhisha.taosha.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.GoodsBean;
import com.yizhisha.taosha.bean.StoreBean;

import java.util.List;
import java.util.Map;

/**
 * Created by lan on 2017/6/28.
 */

public class ShoppCartAdapter extends BaseExpandableListAdapter {
    List<Map<String, Object>> parentMapList;
    List<List<Map<String, Object>>> childMapList_list;
    Context context;
    int totalCount = 0;
    double totalPrice = 0.00;
    OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener;
    OnGoodsCheckedChangeListener onGoodsCheckedChangeListener;
    OnCheckHasGoodsListener onCheckHasGoodsListener;

    public void setOnCheckHasGoodsListener(OnCheckHasGoodsListener onCheckHasGoodsListener) {
        this.onCheckHasGoodsListener = onCheckHasGoodsListener;
    }
    public void setOnGoodsCheckedChangeListener(OnGoodsCheckedChangeListener onGoodsCheckedChangeListener) {
        this.onGoodsCheckedChangeListener = onGoodsCheckedChangeListener;
    }

    public void setOnAllCheckedBoxNeedChangeListener(OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener) {
        this.onAllCheckedBoxNeedChangeListener = onAllCheckedBoxNeedChangeListener;
    }

    public ShoppCartAdapter(Context context, List<Map<String, Object>> parentMapList, List<List<Map<String, Object>>> childMapList_list) {
        this.parentMapList = parentMapList;
        this.childMapList_list = childMapList_list;
        this.context = context;
    }
    //获取当前父item的数据数量
    @Override
    public int getGroupCount() {
        return parentMapList.size();
    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childMapList_list.get(groupPosition).size();
    }

    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return parentMapList.get(groupPosition);
    }

    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childMapList_list.get(groupPosition).get(childPosition);
    }

    //得到父item的ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        //return false;
        return true;
    }
    //设置父item组件
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shoppcart1, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tv_title_parent = (TextView) convertView
                    .findViewById(R.id.company_shoppcart1_tv);
            groupViewHolder.id_tv_delete = (TextView) convertView
                    .findViewById(R.id.delete_shoppcart1_tv);
            groupViewHolder.id_cb_select_parent = (CheckBox) convertView
                    .findViewById(R.id.groupshopp_cb);
            groupViewHolder.topDivider = (View) convertView
                    .findViewById(R.id.shoppcart1_view);

            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        StoreBean storeBean = (StoreBean) parentMapList.get(groupPosition).get("parentName");
        final String parentName = storeBean.getName();
        groupViewHolder.tv_title_parent.setText(parentName);
        //覆盖原有收起展开事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "店铺：" + parentName, Toast.LENGTH_SHORT).show();
            }
        });


        groupViewHolder.id_cb_select_parent.setChecked(storeBean.isChecked());
        final boolean nowBeanChecked = storeBean.isChecked();
        groupViewHolder.id_cb_select_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupOneParentAllChildChecked(!nowBeanChecked, groupPosition);
                //控制总checkedbox 接口
                onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
            }
        });
        groupViewHolder.id_tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeGoods(groupPosition);
            }
        });
        if (groupPosition == 0) {
            groupViewHolder.topDivider.setVisibility(View.GONE);
        } else {
            groupViewHolder.topDivider.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    //设置子item的组件
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shoppcart2, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.id_cb_select_child = (CheckBox) convertView
                    .findViewById(R.id.childshopp_cb);
            //常规下：
            childViewHolder.tv_items_child_desc = (TextView) convertView
                    .findViewById(R.id.tradename_shoppcar2_tv);
            childViewHolder.id_tv_price = (TextView) convertView
                    .findViewById(R.id.tradeprice_shoppcar2_tv);
            childViewHolder.id_tv_color = (TextView) convertView
                    .findViewById(R.id.tradecolor_shoppcar2_tv);


            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }


        final GoodsBean goodsBean = (GoodsBean) childMapList_list.get(groupPosition).get(childPosition).get("childName");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "商品：" + goodsBean.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        childViewHolder.id_tv_price.setText(String.format(context.getString(R.string.price), goodsBean.getPrice()+""));
        childViewHolder.id_cb_select_child.setChecked(goodsBean.isChecked());
        childViewHolder.id_cb_select_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean nowBeanChecked = goodsBean.isChecked();
                //更新数据
                goodsBean.setIsChecked(!nowBeanChecked);

                boolean isOneParentAllChildIsChecked = dealOneParentAllChildIsChecked(groupPosition);
                StoreBean storeBean = (StoreBean) parentMapList.get(groupPosition).get("parentName");
                storeBean.setIsChecked(isOneParentAllChildIsChecked);


                notifyDataSetChanged();
                //控制总checkedbox 接口
                onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
                dealPrice();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // return false;
        return true;
    }
    //供全选按钮调用
    public void setupAllChecked(boolean isChecked) {

        for (int i = 0; i < parentMapList.size(); i++) {
            StoreBean storeBean = (StoreBean) parentMapList.get(i).get("parentName");
            storeBean.setIsChecked(isChecked);

            List<Map<String, Object>> childMapList = childMapList_list.get(i);
            for (int j = 0; j < childMapList.size(); j++) {
                GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                goodsBean.setIsChecked(isChecked);
            }
        }
        notifyDataSetChanged();
        dealPrice();
    }

    private void setupOneParentAllChildChecked(boolean isChecked, int groupPosition) {
        StoreBean storeBean = (StoreBean) parentMapList.get(groupPosition).get("parentName");
        storeBean.setIsChecked(isChecked);

        List<Map<String, Object>> childMapList = childMapList_list.get(groupPosition);
        for (int j = 0; j < childMapList.size(); j++) {
            GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
            goodsBean.setIsChecked(isChecked);
        }
        notifyDataSetChanged();
        dealPrice();
    }

    public boolean dealOneParentAllChildIsChecked(int groupPosition) {
        // StoreBean storeBean= (StoreBean) parentMapList.get(groupPosition).get("parentName");
        List<Map<String, Object>> childMapList = childMapList_list.get(groupPosition);
        for (int j = 0; j < childMapList.size(); j++) {
            GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
            if (!goodsBean.isChecked()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }

    public boolean dealAllParentIsChecked() {
        for (int i = 0; i < parentMapList.size(); i++) {
            StoreBean storeBean = (StoreBean) parentMapList.get(i).get("parentName");
            if (!storeBean.isChecked()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }

    public void dealPrice() {
        // showList();
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < parentMapList.size(); i++) {
            //StoreBean storeBean= (StoreBean) parentMapList.get(i).get("parentName");

            List<Map<String, Object>> childMapList = childMapList_list.get(i);
            for (int j = 0; j < childMapList.size(); j++) {
                GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                int count = goodsBean.getCount();
                double price = goodsBean.getPrice();
                if (goodsBean.isChecked()) {
                    totalCount++;//单品多数量只记1
                    totalPrice += price * count;
                }

            }
        }
        //计算回调
        onGoodsCheckedChangeListener.onGoodsCheckedChange(totalCount, totalPrice);
    }
    //移除数据
    public void removeGoods(int groupPosition) {
        List<Map<String, Object>> childMapList = childMapList_list.get(groupPosition);
        for (int j = childMapList.size()-1; j >=0; j--) {//倒过来遍历  remove
            GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
            if (goodsBean.isChecked()) {
                childMapList.remove(j);
            }
        }
//通过子项
        if (childMapList!=null&&childMapList.size()>0){

        }else {
            parentMapList.remove(groupPosition);
            childMapList_list.remove(groupPosition);//！！！！因为parentMapList和childMapList_list是pos关联的  得保持一致
        }
        // GoodsBean goodsBean = (GoodsBean) childMapList.get(childPosition).get("childName");
/*
        for (int i = parentMapList.size()-1; i>=0; i--) {//倒过来遍历  remove
            StoreBean storeBean= (StoreBean) parentMapList.get(i).get("parentName");
            if (storeBean.isChecked()){
                parentMapList.remove(i);
                childMapList_list.remove(i);
            }else {
                List<Map<String, Object>> childMapList = childMapList_list.get(i);
                for (int j = childMapList.size()-1; j >=0; j--) {//倒过来遍历  remove
                    GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                    if (goodsBean.isChecked()) {
                        childMapList.remove(j);
                    }
                }
            }

        }*/
        if (parentMapList != null && parentMapList.size() > 0) {
            onCheckHasGoodsListener.onCheckHasGoods(true);//
        } else {
            onCheckHasGoodsListener.onCheckHasGoods(false);//
        }
        notifyDataSetChanged();//
        dealPrice();//重新计算
    }

    private void resetViewAfterDelete() {
        for (int i = 0; i < parentMapList.size(); i++) {
            StoreBean storeBean = (StoreBean) parentMapList.get(i).get("parentName");
            storeBean.setIsChecked(false);
            storeBean.setIsEditing(false);
            List<Map<String, Object>> childMapList = childMapList_list.get(i);

            for (int j = 0; j < childMapList.size(); j++) {
                GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
                goodsBean.setIsChecked(false);
                goodsBean.setIsEditing(false);
            }
        }
    }

    void showList(String tempStr) {
        for (int i = 0; i < parentMapList.size(); i++) {
            StoreBean storeBean = (StoreBean) parentMapList.get(i).get("parentName");
            List<Map<String, Object>> childMapList = childMapList_list.get(i);
            for (int j = 0; j < childMapList.size(); j++) {
                GoodsBean goodsBean = (GoodsBean) childMapList.get(j).get("childName");
            }
        }
    }

    public interface OnAllCheckedBoxNeedChangeListener {
        void onCheckedBoxNeedChange(boolean allParentIsChecked);
    }

    public interface OnEditingTvChangeListener {
        void onEditingTvChange(boolean allIsEditing);
    }

    public interface OnGoodsCheckedChangeListener {
        void onGoodsCheckedChange(int totalCount, double totalPrice);
    }

    public interface OnCheckHasGoodsListener {
        void onCheckHasGoods(boolean isHasGoods);
    }
    class GroupViewHolder {
        TextView tv_title_parent;
        TextView id_tv_delete;
        CheckBox id_cb_select_parent;
        View topDivider;
    }

    class ChildViewHolder {
        TextView tv_items_child;
        CheckBox id_cb_select_child;

        TextView tv_items_child_desc;
        TextView id_tv_price;
        TextView id_tv_color;
    }
}
