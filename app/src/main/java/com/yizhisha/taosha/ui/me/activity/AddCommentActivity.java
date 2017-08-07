package com.yizhisha.taosha.ui.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ChoosePhotoListAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.StarView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddCommentActivity extends BaseActivity implements StarView.OnStarItemClickListener{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recycleview)
    RecyclerView mRecyclerView;
    @Bind(R.id.pic_addcomment_iv)
    ImageView picAddcommentIv;
    @Bind(R.id.title_addcomment_tv)
    TextView titleAddcommentTv;
    @Bind(R.id.content_addcomment_et)
    EditText contentAddcommentEt;
    @Bind(R.id.startview_quality_sv)
    StarView startviewQualitySv;
    @Bind(R.id.startview_logistics_sv)
    StarView startviewLogisticsSv;
    @Bind(R.id.startview_serve_sv)
    StarView startviewServeSv;

    private ChoosePhotoListAdapter mAdapter;
    private ArrayList<String> path = new ArrayList<String>();

    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;

    private Goods goods;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_comment;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            goods= (Goods) bundle.getSerializable("SHOPIFNO");
            GlideUtil.getInstance().LoadContextCircleBitmap(this, AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+goods.getLitpic(),picAddcommentIv);
            titleAddcommentTv.setText(goods.getTitle());
        }
        initAdapter();

    }

    private void initAdapter() {
        path.add("selectpic");
        mAdapter = new ChoosePhotoListAdapter(path);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
              /*  Bundle bundle=new Bundle();
                bundle.putSerializable("list", (Serializable) path);
                bundle.putInt("number", i);
                start_Activity(ShowImageActivity.class,bundle);*/
                String imgs = path.get(position);
                if ("selectpic".equals(imgs)) {
                    if (path.contains("selectpic")) {
                        path.remove("selectpic");
                    }
                    PhotoPickerIntent intent = new PhotoPickerIntent(AddCommentActivity.this);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(6); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(path); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                } else {
                   /* PhotoPreviewIntent intent = new PhotoPreviewIntent(MainActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);*/
                }
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, final int i) {
                switch (view.getId()) {
                    case R.id.delete_img_iv:
                        path.remove(i);
                        if (!path.contains("selectpic") && path.size() < 6) {
                            path.add("selectpic");
                        }
                        mAdapter.setNewData(path);
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:

                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    path.clear();
                    path.addAll(list);
                    if (!path.contains("selectpic") && path.size() < 6) {
                        path.add("selectpic");
                    }
                    mAdapter.setNewData(path);
                    //loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    //loadAdpater(ListExtra);
                    break;
            }
        } else {
            if (!path.contains("selectpic") && path.size() < 6) {
                path.add("selectpic");
            }
            mAdapter.setNewData(path);
        }
    }

    @Override
    public void onItemClick(View view, int pos) {
        switch (view.getId()){
            case R.id.startview_quality_sv:
                ToastUtil.showCenterShortToast("多少个"+pos);
                break;
        }
    }
}
