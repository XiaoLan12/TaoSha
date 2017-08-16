package com.yizhisha.taosha.ui.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ChoosePhotoListAdapter;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.LoadingDialog;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.ui.me.contract.AddCommentContract;
import com.yizhisha.taosha.ui.me.presenter.AddCommentPresenter;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.ImageUtils;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.StarView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddCommentActivity extends BaseActivity<AddCommentPresenter>
        implements AddCommentContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.recycleview)
    RecyclerView mRecyclerView;
    @Bind(R.id.content_addcomment_et)
    EditText contentAddcommentEt;
    @Bind(R.id.textnum_addcomment_tv)
    TextView textNumTv;
    @Bind(R.id.startview_quality_sv)
    StarView mQualitySv;
    @Bind(R.id.startview_logistics_sv)
    StarView mLogisticsSv;
    @Bind(R.id.startview_serve_sv)
    StarView mServeSv;
    @Bind(R.id.quality_ll)
    LinearLayout qualityLl;
    @Bind(R.id.serve_ll)
    LinearLayout serveLl;
    @Bind(R.id.logistics_ll)
    LinearLayout logisticsLl;
    private LoadingDialog mLoadingDialog;

    private ChoosePhotoListAdapter mAdapter;
    private ArrayList<String> path = new ArrayList<String>();
    private ArrayList<String> loadPath = new ArrayList<String>();

    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    //1表示评价，2表示追评
    private int type;
    private int orderId;
    private int mzwuId;

    private int qualityNum;
    private int logisticsNum;
    private int serveNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_comment;
    }

    @Override
    protected void initToolBar() {
        Bundle bundle=getIntent().getExtras();
        orderId=bundle.getInt("ORDERID",0);
        mzwuId=bundle.getInt("MZWUIID",0);
        type=bundle.getInt("TYPE",1);
        if(type==2){
            toolbar.setTitle("发表追评");
        }
        toolbar.setRightButtonText("发布");
        toolbar.setRightButtonTextColor(R.color.red1);
        toolbar.showRightButton();
        toolbar.setRightButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contentAddcommentEt.getText().equals("")){
                    ToastUtil.showbottomShortToast("请填写商品的评论内容");
                    return;
                }
                showLoading();
                if (path.contains("selectpic")) {
                    path.remove("selectpic");
                }
                if(path.size()>0) {
                    loadPath.clear();
                    uploadPic();
                }else{
                    if(type==1) {
                        uploadComment("");
                    }else{
                        uploadAddComment("");
                    }
                }
            }
        });
    }

    @Override
    protected void initView() {

        initAdapter();
        mQualitySv.setmStarItemClickListener(new StarView.OnStarItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                qualityNum=pos+1;
            }
        });
        //判断当前是发表评论还是追评
        if(type==1) {
            mLogisticsSv.setmStarItemClickListener(new StarView.OnStarItemClickListener() {
                @Override
                public void onItemClick(View view, int pos) {
                    logisticsNum = pos + 1;
                }
            });
            mServeSv.setmStarItemClickListener(new StarView.OnStarItemClickListener() {
                @Override
                public void onItemClick(View view, int pos) {
                    serveNum = pos + 1;
                }
            });

        }else{
            logisticsLl.setVisibility(View.GONE);
            serveLl.setVisibility(View.GONE);
            qualityLl.setVisibility(View.GONE);
        }
        contentAddcommentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textNumTv.setText(count + "/300");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                    intent.setMaxTotal(5); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(path); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                } else {
                    if (path.contains("selectpic")) {
                        path.remove("selectpic");
                    }
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(AddCommentActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(path);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, final int i) {
                switch (view.getId()) {
                    case R.id.delete_img_iv:
                        path.remove(i);
                        if (!path.contains("selectpic") && path.size() < 5) {
                            path.add("selectpic");
                        }
                        mAdapter.setNewData(path);
                        break;
                }
            }
        });
    }
    private void uploadComment(String photo){
        Map<String,String> body=new HashMap<>();
        body.put("uid",String.valueOf(AppConstant.UID));
        body.put("mzw_uid",String.valueOf(mzwuId));
        body.put("order_id",String.valueOf(orderId));
        body.put("quality",String.valueOf(qualityNum));
        body.put("logistics",String.valueOf(logisticsNum));
        body.put("service",String.valueOf(serveNum));
        body.put("detail",contentAddcommentEt.getText().toString());
        body.put("photo",photo);
        mPresenter.addComment(body);
    }
    private void uploadAddComment(String photo){
        Map<String,String> body=new HashMap<>();
        body.put("oid",String.valueOf(orderId));
        body.put("detail",contentAddcommentEt.getText().toString());
        body.put("photo",photo);
        mPresenter.addAddComment(body);
    }
    private void uploadPic(){
        if (path.contains("selectpic")) {
            path.remove("selectpic");
        }
        for(int i=0;i<path.size();i++){
            try {
                String pic= ImageUtils.bitmapToPath(path.get(i));
                File file = new File(pic);
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("CommentPic", file.getName(), requestFile);
                mPresenter.addCommentPic(body);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                    if (!path.contains("selectpic") && path.size() < 5) {
                        path.add("selectpic");
                    }
                    mAdapter.setNewData(path);
                    //loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    path.clear();
                    path.addAll(ListExtra);
                    if (!path.contains("selectpic") && path.size() < 5) {
                        path.add("selectpic");
                    }
                    mAdapter.setNewData(path);
                    break;
            }
        } else {
            if (!path.contains("selectpic") && path.size() < 5) {
                path.add("selectpic");
            }
            mAdapter.setNewData(path);
        }
    }
    @Override
    public void addCommentSuccess(String data) {
        new NormalAlertDialog.Builder(this)
                .setBoolTitle(false)
                .setContentText(data)
                .setSingleModel(true)
                .setSingleText("确认")
                .setSingleTextColor(R.color.blue)
                .setWidth(0.75f)
                .setHeight(0.33f)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                            finish_Activity(AddCommentActivity.this);
                            dialog.dismiss();
                    }
                }).build().show();
    }

    @Override
    public void addAddCommentSuccess(String data) {
        new NormalAlertDialog.Builder(this)
                .setBoolTitle(false)
                .setContentText(data)
                .setSingleModel(true)
                .setSingleText("确认")
                .setSingleTextColor(R.color.blue)
                .setWidth(0.75f)
                .setHeight(0.33f)
                .setSingleListener(new DialogInterface.OnSingleClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickSingleButton(NormalAlertDialog dialog, View view) {
                        finish_Activity(AddCommentActivity.this);
                        dialog.dismiss();
                    }
                }).build().show();
    }

    @Override
    public void addCommentPicSuccess(String img) {
        loadPath.add(img);
        if(loadPath.size()==path.size()){
            StringBuilder str=new StringBuilder();
            for(int i=0;i<loadPath.size();i++){
                str.append(loadPath.get(i)).append(",");
            }
            if(type==1) {
                uploadComment(str.substring(0,str.length()-1).toString());
            }else{
                uploadAddComment(str.substring(0,str.length()-1).toString());
            }

        }
    }

    @Override
    public void showLoading() {
        mLoadingDialog=new LoadingDialog(this,"请稍后...",false);
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.cancelDialog();
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }
}
