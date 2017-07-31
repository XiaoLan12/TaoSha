package com.yizhisha.taosha.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.bean.json.CommentBean;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.MultiImageView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lan on 2017/7/31.
 */

public class CommentYarnAdapter extends BaseMultiItemQuickAdapter<CommentBean,BaseViewHolder>{

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
                GlideUtil.getInstance().LoadContextCircleBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getAvatar(),imageView);
                helper.setText(R.id.name_comment_tv,item.getMobile());
                helper.setText(R.id.time_comment_tv,item.getComment_addtime());
                helper.setText(R.id.detail_comment_tv,item.getComment_detail());
                break;
            case CommentBean.IMGS_TYPE:
                ImageView imageView1=helper.getView(R.id.head_comment_img_iv);
                GlideUtil.getInstance().LoadContextCircleBitmap(mContext, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+item.getAvatar(),imageView1);
                helper.setText(R.id.name_comment_img_tv,item.getMobile());
                helper.setText(R.id.time_comment_img_tv,item.getComment_addtime());
                if(item.getComment_detail()==null||item.getComment_detail().length()==0){
                    helper.setVisible(R.id.detail_comment_img_tv,false);
                }else{
                    helper.setVisible(R.id.detail_comment_img_tv,true);
                    helper.setText(R.id.detail_comment_img_tv,item.getComment_detail());
                }
                MultiImageView multiImageView = helper.getView(R.id.cilrcleimgMv_img);
                final List<String> photos = item.getComment_photos();
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
