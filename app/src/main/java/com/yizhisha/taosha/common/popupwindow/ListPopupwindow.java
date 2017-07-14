package com.yizhisha.taosha.common.popupwindow;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.bean.PopupListBean;
import com.yizhisha.taosha.utils.DensityUtil;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.RecyclerViewDriverLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/7/13.
 */

public class ListPopupwindow extends PopupWindow {
    private Context mContext;

    // 列表弹窗的间隔
    protected final int LIST_PADDING = 10;

    // 实例化一个矩形
    private Rect mRect = new Rect();

    // 坐标的位置（x、y）
    private final int[] mLocation = new int[2];

    // 屏幕的宽度和高度
    private int mScreenWidth, mScreenHeight;

    // 判断是否需要添加或更新列表子类项
    private boolean mIsDirty;

    // 位置不在中心
    private int popupGravity = Gravity.NO_GRAVITY;

    // 弹窗子类项选中时的监听
    private OnItemOnClickListener mItemOnClickListener;

    // 定义列表对象
    private RecyclerView mRecyclerView;

    private MyAdapter mAdapter;

    // 定义弹窗子类项列表
    private ArrayList<PopupListBean> mActionItemEntities = new ArrayList<PopupListBean>();



    public ListPopupwindow(Context context) {
        // 设置布局的参数
        this(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public ListPopupwindow(Context context, int width, int height) {
        this.mContext = context;

        // 设置可以获得焦点
        setFocusable(true);
        // 设置弹窗内可点击
        setTouchable(true);
        // 设置弹窗外可点击
        setOutsideTouchable(true);

        // 获得屏幕的宽度和高度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mScreenHeight = wm.getDefaultDisplay().getHeight();

        // 设置弹窗的宽度和高度
        setWidth(width);
        setHeight(height);

        setBackgroundDrawable(new BitmapDrawable());

        // 设置弹窗的布局界面
        setContentView(LayoutInflater.from(mContext).inflate(
                R.layout.popup_list, null));
        setAnimationStyle(R.style.normalDialogAnim);
        initUI();
    }

    /**
     * 初始化弹窗列表
     */
    private void initUI() {
        mRecyclerView = (RecyclerView) getContentView().findViewById(R.id.title_list);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                // 点击子类项后，弹窗消失
                dismiss();

                if (mItemOnClickListener != null)
                    mItemOnClickListener.onItemClick(mActionItemEntities.get(position),
                            position);
                mAdapter.setPosition(position);
            }
        });

    }
    public void setItemSelected(int position){
        mAdapter.position=position;
    }
    /**
     * 显示弹窗列表界面
     */
    public void show(View view) {
        // 判断是否需要添加或更新列表子类项


        // 显示弹窗的位置

        showAsDropDown(view, DensityUtil.dip2px(-22), DensityUtil.dip2px(12));
        //showAtLocation(view,Gravity.BOTTOM,10,10);
    }

    /**
     * 设置弹窗列表子项
     */
    private void populateActions() {
        mIsDirty = false;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new RecyclerViewDriverLine(mContext,RecyclerViewDriverLine.VERTICAL_LIST));
        mAdapter=new MyAdapter(mActionItemEntities);
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * 添加子类项
     */
    public void addAction(List<PopupListBean> action) {
        if (action != null) {
            mActionItemEntities.addAll(action);
            mIsDirty = true;
        }
        if (mIsDirty) {
            populateActions();
        }
    }

    /**
     * 清除子类项
     */
    public void cleanAction() {
        if (mActionItemEntities.isEmpty()) {
            mActionItemEntities.clear();
            mIsDirty = true;
        }
    }

    /**
     * 根据位置得到子类项
     */
    public PopupListBean getAction(int position) {
        if (position < 0 || position > mActionItemEntities.size())
            return null;
        return mActionItemEntities.get(position);
    }

    /**
     * 设置监听事件
     */
    public void setItemOnClickListener(
            OnItemOnClickListener onItemOnClickListener) {
        this.mItemOnClickListener = onItemOnClickListener;
    }

    /**
     * @author yangyu 功能描述：弹窗子类项按钮监听事件
     */
    public static interface OnItemOnClickListener {
        public void onItemClick(PopupListBean item, int position);
    }

    private class MyAdapter extends BaseQuickAdapter<PopupListBean,BaseViewHolder>{
        public int position;
        public MyAdapter(@Nullable List<PopupListBean> data) {
            super(R.layout.item_popup_list,data);
        }
        @Override
        protected void convert(BaseViewHolder helper, PopupListBean item) {
            helper.setText(R.id.txt_title,item.getTitle());
            if(position==helper.getAdapterPosition()){
                helper.setBackgroundRes(R.id.txt_title,R.color.common_bg);
                helper.setTextColor(R.id.txt_title, RescourseUtil.getColor(R.color.blue));
            }else{
                helper.setBackgroundRes(R.id.txt_title,R.color.white);
                helper.setTextColor(R.id.txt_title,RescourseUtil.getColor(R.color.common_h1));
            }
        }
        public void setPosition(int position){
            this.position=position;
            notifyDataSetChanged();
        }
    }
}
