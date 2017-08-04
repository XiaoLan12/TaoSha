package com.yizhisha.taosha.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.CommentBean;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.LogUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.MultiImageView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lan on 2017/7/31.
 */

public class CommentYarnAdapter extends BaseMultiItemQuickAdapter<CommentBean,BaseViewHolder>{
    private final String URL="http://www.taoshamall.com/data/attached/avatar/";
    public CommentYarnAdapter(List<CommentBean> data) {
        super(data);
        addItemType(CommentBean.TEXT_TYPE, R.layout.item_text_commnet);
        addItemType(CommentBean.IMGS_TYPE, R.layout.item_img_commnet);
    }
    @Override
    protected void convert(BaseViewHolder helper, CommentBean item) {
        switch (helper.getItemViewType()){
            case CommentBean.TEXT_TYPE:
                ImageView imageView=helper.getView(R.id.head_comment_iv);
                GlideUtil.getInstance().LoadContextCircleBitmap(mContext,URL+item.getAvatar(),imageView);
                helper.setText(R.id.name_comment_tv,item.getMobile());
                helper.setText(R.id.time_comment_tv, DateUtil.getDateToString1(item.getComment_addtime_add()*1000));
                helper.setText(R.id.detail_comment_tv,item.getComment_detail());
                helper.setText(R.id.detail_shop_tv,item.getDetail());
                if(item.getComment_detail_add()!=null||!item.getComment_detail_add().equals("")){
                    helper.setVisible(R.id.addcomment_ll,true);
                    helper.setText(R.id.detail_addcomment_tv,item.getComment_detail_add());

                    int time=DateUtil.getTimeintervalDay(item.getComment_addtime_add(),item.getComment_addtime());
                    if(time>0){
                        helper.setText(R.id.time_addcomment_img_tv,"用户"+time+"后追加评论");
                    }else{
                        helper.setText(R.id.time_addcomment_img_tv,"用户当天追加评论");
                    }
                }else{
                    helper.setVisible(R.id.addcomment_ll,false);
                }
                break;
            case CommentBean.IMGS_TYPE:
                ImageView imageView1=helper.getView(R.id.head_comment_img_iv);
                GlideUtil.getInstance().LoadContextCircleBitmap(mContext,URL+item.getAvatar(),imageView1);
                helper.setText(R.id.name_comment_img_tv,item.getMobile());
                helper.setText(R.id.time_comment_img_tv,DateUtil.getDateToString1(item.getComment_addtime_add()*1000));
                helper.setText(R.id.detail_comment_img_tv,item.getComment_detail());
                helper.setText(R.id.detail_shop_img_tv,item.getDetail());
                if(item.getComment_detail()==null||item.getComment_detail().length()==0){
                    helper.setVisible(R.id.detail_comment_img_tv,false);
                }else{
                    helper.setVisible(R.id.detail_comment_img_tv,true);
                    helper.setText(R.id.detail_comment_img_tv,item.getComment_detail());
                }
                if(item.getComment_detail_add()!=null||!item.getComment_detail_add().equals("")){
                    helper.setVisible(R.id.addcomment_img_ll,true);
                    helper.setText(R.id.detail_addcomment_img_tv,item.getComment_detail_add());
                    int time=DateUtil.getTimeintervalDay(item.getComment_addtime_add(),item.getComment_addtime());
                    if(time>0){
                        helper.setText(R.id.time_addcomment_img_tv,"用户"+time+"后追加评论");
                    }else{
                        helper.setText(R.id.time_addcomment_img_tv,"用户当天追加评论");
                    }

                }else{
                    helper.setVisible(R.id.addcomment_ll,false);
                }
                MultiImageView multiImageView = helper.getView(R.id.cilrcleimgMv_img);
                final List<String> photos = item.getcommentPhotos();
                if (photos != null && photos.size() > 0) {
                    multiImageView.setList(photos);
                    multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            ToastUtil.showbottomLongToast("点击了图片");

                        }
                    });
                }
                break;
        }
    }
}
