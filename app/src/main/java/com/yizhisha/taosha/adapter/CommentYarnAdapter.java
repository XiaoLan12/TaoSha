package com.yizhisha.taosha.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.CommentBean;
import com.yizhisha.taosha.ui.ShowImageActivity;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.MultiImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/7/31.
 */

public class CommentYarnAdapter extends BaseMultiItemQuickAdapter<CommentBean,BaseViewHolder>{
    private final String AVATARURL="http://www.taoshamall.com/data/attached/avatar/";

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
                GlideUtil.getInstance().LoadContextCircleBitmap(mContext,AVATARURL+item.getAvatar(),imageView,
                        R.drawable.icon_head_normal,R.drawable.icon_head_normal);
                if(item.getMobile()!=null&&!item.getMobile().equals("")){
                    helper.setText(R.id.name_comment_tv,item.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                }
                helper.setText(R.id.time_comment_tv, DateUtil.getDateToString1(item.getComment_addtime()*1000));
                helper.setText(R.id.detail_comment_tv,item.getComment_detail());
                if(item.getComment_detail_add()!=null&&!item.getComment_detail_add().equals("")){
                    helper.setVisible(R.id.addcomment_ll,true);
                    helper.setVisible(R.id.time_addcomment_ll,true);
                    helper.setText(R.id.detail_addcomment_tv,item.getComment_detail_add());
                    helper.setText(R.id.time_addcomment_tv,DateUtil.getDateToString1(item.getComment_addtime_add()*1000));
                }else{
                    helper.setVisible(R.id.addcomment_ll,false);
                    helper.setVisible(R.id.time_addcomment_ll,false);

                }
                if(item.getComment_redetail()!=null&&!item.getComment_redetail().equals("")){
                    helper.setVisible(R.id.business_reply_ll,true);
                    helper.setText(R.id.business_reply_content_tv,item.getComment_redetail());
                    helper.setText(R.id.business_reply_time_tv, DateUtil.getDateToString1(item.getComment_retime()*1000));
                }else{
                    helper.setVisible(R.id.business_reply_ll,false);
                }
                break;
            case CommentBean.IMGS_TYPE:
                ImageView imageView1=helper.getView(R.id.head_comment_img_iv);
                GlideUtil.getInstance().LoadContextCircleBitmap(mContext,AVATARURL+item.getAvatar(),imageView1,R.drawable.icon_head_normal,R.drawable.icon_head_normal);
                if(item.getMobile()!=null&&!item.getMobile().equals("")){
                    helper.setText(R.id.name_comment_img_tv,item.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                }
                helper.setText(R.id.time_comment_img_tv,DateUtil.getDateToString1(item.getComment_addtime()*1000));
                helper.setText(R.id.detail_comment_img_tv,item.getComment_detail());
                if(item.getComment_detail()==null||item.getComment_detail().length()==0){
                    helper.setVisible(R.id.detail_comment_img_tv,false);
                }else{
                    helper.setVisible(R.id.detail_comment_img_tv,true);
                    helper.setText(R.id.detail_comment_img_tv,item.getComment_detail());
                }
                if(item.getComment_detail_add()!=null&&!item.getComment_detail_add().equals("")){
                    helper.setVisible(R.id.addcomment_img_ll,true);
                    helper.setVisible(R.id.time_addcomment_img_ll,true);
                    helper.setText(R.id.detail_addcomment_img_tv,item.getComment_detail_add());
                    helper.setText(R.id.time_addcomment_img_tv,DateUtil.getDateToString1(item.getComment_addtime_add()*1000));
                }else{
                    helper.setVisible(R.id.addcomment_img_ll,false);
                    helper.setVisible(R.id.time_addcomment_img_ll,false);
                }
                if(item.getComment_redetail()!=null&&!item.getComment_redetail().equals("")){
                    helper.setVisible(R.id.business_reply_img_ll,true);
                    helper.setText(R.id.business_reply_content_img_tv,item.getComment_redetail());
                    helper.setText(R.id.business_reply_time_img_tv, DateUtil.getDateToString1(item.getComment_retime()*1000));
                }else{
                    helper.setVisible(R.id.business_reply_img_ll,false);
                }
                MultiImageView multiImageView = helper.getView(R.id.cilrcleimgMv_img);
                final List<String> photos =new ArrayList<>(9);
                if (item.getcommentPhotos() != null && item.getcommentPhotos().size() > 0) {
                   for(int i=0;i<item.getcommentPhotos().size();i++){
                       if(i<=8){
                           photos.add(item.getcommentPhotos().get(i));
                       }
                   }
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
                break;
        }
    }
}
