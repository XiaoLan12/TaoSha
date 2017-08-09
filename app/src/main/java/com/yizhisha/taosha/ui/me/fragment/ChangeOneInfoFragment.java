package com.yizhisha.taosha.ui.me.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.ChangeUserInfoBody;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.event.ChangeUserInfoEvent;
import com.yizhisha.taosha.ui.ClipHeaderActivity;
import com.yizhisha.taosha.ui.me.activity.ChangeUserNameActivity;
import com.yizhisha.taosha.ui.me.contract.ChangeOneInfoContract;
import com.yizhisha.taosha.ui.me.presenter.ChangeOneInfoPresenter;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.ImageUtils;
import com.yizhisha.taosha.utils.LogUtil;
import com.yizhisha.taosha.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;
import static android.net.UrlQuerySanitizer.IllegalCharacterValueSanitizer.LT_OK;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ChangeOneInfoFragment extends BaseFragment<ChangeOneInfoPresenter> implements ChangeOneInfoContract.View{
    @Bind(R.id.head_personal_iv)
    ImageView mIvHead;
    @Bind(R.id.username_oneinfo_tv)
    TextView mTvUserName;
    @Bind(R.id.realname_oneinfo_tv)
    TextView mTvRealName;
    @Bind(R.id.sex_oneinfo_tv)
    TextView mTvSex;
    @Bind(R.id.e_mail_oneinfo_tv)
    TextView mTvEmail;
    @Bind(R.id.companyname_oneinfo_tv)
    TextView mTvCompanyName;
    private Subscription subscription;
    //调用相机相册
    private static final int RESULT_CAPTURE = 100;
    private static final int RESULT_PICK = 101;
    private static final int CROP_PHOTO = 102;

    private File tempFile;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_changeoneinfo;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }
    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/ziyiApp/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }
    /**判断文件是否存在
     * @param dirPath
     * @return
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
    @Override
    protected void initView() {
        mPresenter.loadPersonalData(AppConstant.UID);
        changeEvent();
    }
    private void load(){
        Map<String,String> map=new HashMap<>();
        map.put("uid", String.valueOf(AppConstant.UID));
        map.put("linkman",mTvRealName.getText().toString().trim());
        map.put("sex",mTvSex.getText().toString().trim());
        map.put("email",mTvEmail.getText().toString().trim());
        map.put("username",mTvUserName.getText().toString().trim());
        mPresenter.changeUserInfo(map);
    }
    @Override
    public void loadPersonalDataSuccess(PersonalDataBean personalDataBean) {
        if(personalDataBean==null){
            return;
        }
        if(AppConstant.infoBean!=null){
            String url="http://www.taoshamall.com/data/attached/avatar/100x100/";
            GlideUtil.getInstance().LoadContextCircleBitmap(activity,url+AppConstant.infoBean.getAvatar(),mIvHead);
        }
        mTvUserName.setText(personalDataBean.getUsername());
        mTvRealName.setText(personalDataBean.getLinkman());
        mTvSex.setText(personalDataBean.getSex());
        mTvEmail.setText(personalDataBean.getEmail());
        mTvCompanyName.setText(personalDataBean.getCompany());
    }
    @Override
    public void changeSuccess(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }

    @Override
    public void changeHeadSuccess(String msg) {
        GlideUtil.getInstance().LoadContextCircleBitmap(activity,AppConstant.INDEX_RECOMMEND_TYPE_IMG_URL+msg,mIvHead);
        ToastUtil.showbottomShortToast("头像修改成功");
    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }
    private void changeEvent(){
        subscription= RxBus.$().toObservable(ChangeUserInfoEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ChangeUserInfoEvent>() {
                    @Override
                    public void call(ChangeUserInfoEvent event) {
                        switch (event.getType()){
                            case 1:
                                mTvUserName.setText(event.getValue());
                                break;
                            case 2:
                                mTvRealName.setText(event.getValue());
                                break;
                            case 3:
                                mTvEmail.setText(event.getValue());
                                break;
                        }
                    }
                });
    }
    //打开照相机
    private void startCamera() {
        if (!tempFile.getParentFile().exists())tempFile.getParentFile().mkdirs();
        Uri imageUri = FileProvider.getUriForFile(activity, "com.taosha.takephoto.fileprovider", tempFile);//通过FileProvider创建一个content类型的Uri
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        if (intent.resolveActivity(activity.getPackageManager()) != null) {//判断是否有相机应用
            startActivityForResult(intent,RESULT_CAPTURE);
        }else{
            ToastUtil.showShortToast("没有找到相机");
        }
    }

    //打开相册
    private void startAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), RESULT_PICK);
    }

    /**
     * 打开截图界面
     *
     * @param uri 原图的Uri
     */
    public void starCropPhoto(Uri uri) {

        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(activity, ClipHeaderActivity.class);
        intent.setData(uri);
        intent.putExtra("side_length", 200);//裁剪图片宽高
        startActivityForResult(intent, CROP_PHOTO);
    }

    private void setPicToView(Intent picdata) {
        Uri uri = picdata.getData();

        if (uri == null) {
            return;
        }
        String path = ImageUtils.getRealFilePathFromUri(activity.getApplicationContext(), uri);

        uploadPic(path);
    }

    //上传头像
    private void uploadPic(String path) {
        if (path != null) {
            File file = new File(path);
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("Filedata", file.getName(), requestFile);
            mPresenter.changeHeadSuccess(body);
        }
    }
    @OnClick({R.id.changeusernamee_rl,R.id.realname_rl,R.id.e_mail_rl,R.id.sex_rl
            ,R.id.save_userinfo_btn,R.id.myhead_info_rl})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.changeusernamee_rl:
                Bundle bundle=new Bundle();
                bundle.putInt("TYPE",1);
                startActivity(ChangeUserNameActivity.class,bundle);
                break;
            case R.id.realname_rl:
                Bundle bundle1=new Bundle();
                bundle1.putInt("TYPE",2);
                startActivity(ChangeUserNameActivity.class,bundle1);
                break;
            case R.id.e_mail_rl:
                Bundle bundle2=new Bundle();
                bundle2.putInt("TYPE",3);
                startActivity(ChangeUserNameActivity.class,bundle2);
                break;
            case R.id.sex_rl:
                final List<String> sexDatas=new ArrayList<>();
                sexDatas.add("男");
                sexDatas.add("女");
                NormalSelectionDialog dialog=new NormalSelectionDialog.Builder(activity)
                        .setItemHeight(45)
                        .setItemTextColor(R.color.blue)
                        .setItemTextSize(14)
                        .setItemWidth(0.7f)
                        .setCancleButtonText("取消")
                        .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                            @Override
                            public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                    mTvSex.setText(sexDatas.get(position));
                            }
                        }).setTouchOutside(true)
                        .build();
                dialog.setData(sexDatas);
                dialog.show();
                break;
            case R.id.save_userinfo_btn:
                load();
                break;
            case R.id.myhead_info_rl:
                List<String> headData=new ArrayList<>();
                headData.add("相机");
                headData.add("相册");
                NormalSelectionDialog chaHeaddialog=new NormalSelectionDialog.Builder(activity)
                        .setItemHeight(45)
                        .setItemTextColor(R.color.blue)
                        .setItemTextSize(14)
                        .setItemWidth(0.7f)
                        .setCancleButtonText("取消")
                        .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                            @Override
                            public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                                switch (position){
                                    case 0:
                                        performCodeWithPermission("软件更新需要您提供浏览存储的权限", new BaseActivity.PermissionCallback() {
                                            @Override
                                            public void hasPermission() {
                                                startCamera();
                                            }
                                            @Override
                                            public void noPermission() {
                                            }
                                        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                                        break;
                                    case 1:
                                        performCodeWithPermission("软件更新需要您提供浏览存储的权限", new BaseActivity.PermissionCallback() {
                                            @Override
                                            public void hasPermission() {
                                                startAlbum();
                                            }
                                            @Override
                                            public void noPermission() {
                                            }
                                        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                                        break;
                                }

                            }
                        }).setTouchOutside(true)
                        .build();

                chaHeaddialog.setData(headData);
                chaHeaddialog.show();
                break;

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case RESULT_CAPTURE:
                if (resultCode == RESULT_OK) {
                    starCropPhoto(Uri.fromFile(tempFile));
                }
                break;
            case RESULT_PICK:
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();

                    starCropPhoto(uri);
                }

                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {

                    if (intent != null) {
                        setPicToView(intent);
                    }
                }
                break;

            default:
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (subscription != null&&!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
