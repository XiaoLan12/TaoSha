package com.yizhisha.taosha.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.CommentBean;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.MyCommentHeadBean;
import com.yizhisha.taosha.bean.json.MyCommentListBean;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.bean.json.OrderHeadBean;
import com.yizhisha.taosha.ui.ShowImageActivity;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.MultiImageView;

import java.io.Serializable;
import java.util.List;

import rx.Observable;

/**
 * Created by lan on 2017/6/26.
 */

public class HaveRatingAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private final String AVATARURL="http://www.taoshamall.com/data/attached/avatar/";
    public static final int ITEM_HEADER = 1;
    public static final int ITEM_CONTENT= 2;
    public static final int ITEM_FOOTER= 3;
    public HaveRatingAdapter() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<Object>() {
            @Override
            protected int getItemType(Object order) {
                if(order instanceof MyCommentHeadBean) {
                    return ITEM_HEADER;
                }else if(order instanceof MyCommentListBean.Goods){
                    return ITEM_CONTENT;
                }else if(order instanceof MyCommentListBean.MyComment){
                    return ITEM_FOOTER;
                }
                return ITEM_CONTENT;
            }
        });
        getMultiTypeDelegate().registerItemType(ITEM_HEADER, R.layout.item_head_mycomment).
                registerItemType(ITEM_CONTENT, R.layout.item_middle_myorder).
                registerItemType(ITEM_FOOTER, R.layout.item_bottom_mycommnet);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        switch (helper.getItemViewType()){
            case ITEM_HEADER:
                MyCommentHeadBean headBean= (MyCommentHeadBean) item;
                helper.setText(R.id.orderno_mycomment_tv,headBean.getOrderno());
                helper.setText(R.id.time_mycomment_tv,DateUtil.getDateToString1(headBean.getComment_addtime()*1000));
                break;
            case ITEM_CONTENT:
                MyCommentListBean.Goods goods= (MyCommentListBean.Goods) item;
                helper.setText(R.id.tradename_myorder_tv,goods.getTitle());
                helper.setText(R.id.tradecolor_myorder_tv,goods.getRemark());
                helper.setText(R.id.tradeprice_myorder_tv,"￥"+goods.getTotalprice()+"元");
                helper.setText(R.id.tradecolor_myorder_tv,goods.getDetail());
                GlideUtil.getInstance().LoadContextBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+goods.getLitpic(),
                        (ImageView) helper.getView(R.id.tradehead_myorder_iv),GlideUtil.LOAD_BITMAP);
                break;
            case ITEM_FOOTER:
                MyCommentListBean.MyComment myComment= (MyCommentListBean.MyComment) item;
                helper.setText(R.id.detail_comment_mycomment_tv,myComment.getComment_detail());
                if(myComment.getComment_detail()==null||myComment.getComment_detail().length()==0){
                    helper.setVisible(R.id.detail_comment_mycomment_tv,false);
                }else{
                    helper.setVisible(R.id.detail_comment_mycomment_tv,true);
                    helper.setText(R.id.detail_comment_mycomment_tv,myComment.getComment_detail());
                }
                if(myComment.getComment_detail_add()!=null&&!myComment.getComment_detail_add().equals("")){
                    helper.setVisible(R.id.addcomment_mycomment_ll,true);
                    helper.setText(R.id.detail_addcomment_mycomment_tv,myComment.getComment_detail_add());
                   /* int time=DateUtil.getTimeintervalDay(myComment.getComment_addtime_add(),myComment.getComment_addtime());
                    if(time>0){
                        helper.setText(R.id.time_addcomment_mycomment_tv,"用户"+time+"后追加评论");
                    }else{
                        helper.setText(R.id.time_addcomment_mycomment_tv,"用户当天追加评论");
                    }*/

                }else{
                    helper.setVisible(R.id.addcomment_mycomment_ll,false);
                }
                if(myComment.getComment_redetail()!=null&&!myComment.getComment_redetail().equals("")){
                    helper.setVisible(R.id.business_reply_mycomment_ll,true);
                    helper.setText(R.id.business_reply_content_mycomment_tv,myComment.getComment_redetail());
                    helper.setText(R.id.business_reply_time_mycomment_tv, DateUtil.getDateToString1(myComment.getComment_retime()*1000));
                }else{
                    helper.setVisible(R.id.business_reply_mycomment_ll,false);
                }
                if(myComment.getComment_photos()!=null&&!myComment.getComment_photos().equals("")){
                    helper.setVisible(R.id.cilrcleimgMv_mycomment,true);
                    MultiImageView multiImageView = helper.getView(R.id.cilrcleimgMv_mycomment);
                    final List<String> photos = myComment.getCommentPhotos();
                    if (photos != null && photos.size() > 0) {
                        multiImageView.setList(photos);
                        multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(mContext, ShowImageActivity.class);
                                intent.putExtra("list", (Serializable) photos);
                                intent.putExtra("number", position);
                                mContext.startActivity(intent);
                            }
                        });
                    }
                }else{
                    helper.setVisible(R.id.cilrcleimgMv_mycomment,false);
                }

                break;
        }
    }
}
