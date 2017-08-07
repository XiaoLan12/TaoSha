/*
 * Copyright (C) 2014 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.yizhisha.taosha.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Desction:
 * Author:pengjianbo
 * Date:15/12/1 下午8:42
 */
public class ChoosePhotoListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ChoosePhotoListAdapter(ArrayList<String> data) {
        super(R.layout.adapter_photo_list_item,data);
    }
    @Override
    protected void convert(BaseViewHolder holder, String photoInfo) {
        ImageView ivPhoto = holder.getView(R.id.iv_photo);
        holder.addOnClickListener(R.id.delete_img_iv);
        if(photoInfo.equals("selectpic")){
            ivPhoto.setImageResource(R.drawable.icon_add_pic);
            holder.setVisible(R.id.delete_img_iv,false);
        }else {
            holder.setVisible(R.id.delete_img_iv,true);
            GlideUtil.getInstance().LoadContextBitmap(mContext, photoInfo, ivPhoto, GlideUtil.LOAD_BITMAP);
        }
    }

}
