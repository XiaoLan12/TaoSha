package com.yizhisha.taosha.ui.me.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.json.UserHeadBean;
import com.yizhisha.taosha.bean.json.UserInfoBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.event.LoginEvent;
import com.yizhisha.taosha.event.UserHeadEvent;
import com.yizhisha.taosha.ui.ClipHeaderActivity;
import com.yizhisha.taosha.ui.login.activity.LoginFragmentActivity;
import com.yizhisha.taosha.ui.login.activity.RegisterActivity;
import com.yizhisha.taosha.ui.me.activity.AboutActivity;
import com.yizhisha.taosha.ui.me.activity.AccountCenterActivity;
import com.yizhisha.taosha.ui.me.activity.ContactUsActivity;
import com.yizhisha.taosha.ui.me.activity.FreeSampleActivity;
import com.yizhisha.taosha.ui.me.activity.MyAddressActivity;
import com.yizhisha.taosha.ui.me.activity.MyCollectActivity;
import com.yizhisha.taosha.ui.me.activity.MyFootprintActivity;
import com.yizhisha.taosha.ui.me.activity.MyOrderAcitvity;
import com.yizhisha.taosha.ui.me.activity.MyRatingActivity;
import com.yizhisha.taosha.ui.me.activity.SecKillOrderActivity;
import com.yizhisha.taosha.ui.me.activity.SettinActivity;
import com.yizhisha.taosha.ui.me.contract.MeContract;
import com.yizhisha.taosha.ui.me.presenter.MePresenter;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.ImageUtils;
import com.yizhisha.taosha.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import qiu.niorgai.StatusBarCompat;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lan on 2017/6/22.
 */
public class MeFragment extends BaseFragment<MePresenter> implements MeContract.View{
    @Bind(R.id.head_me_iv)
    ImageView mIvHead;
    @Bind(R.id.username_me_tv)
    TextView mTVUserName;
    private Subscription subscription;

    private NormalSelectionDialog dialog;

    //调用相机相册
    private static final int RESULT_CAPTURE = 100;
    private static final int RESULT_PICK = 101;
    private static final int CROP_PHOTO = 102;

    private File tempFile;

    private String headUrl="http://www.taoshamall.com/data/attached/avatar/150x150/";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarCompat.translucentStatusBar(getActivity());
//            StatusBarCompat.setStatusBarColor(activity, Color.WHITE,125);
        }
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
        if(AppConstant.isLogin==true){
            load();
        }else{
            mTVUserName.setText("毛织网感谢您的支持,请登录");
        }
        event();

    }
    private void load(){
        mPresenter.getUserInfo(AppConstant.UID);
    }
    @Override
    public void getUserInfoSuccess(UserInfoBean info) {
        if(info!=null){
            AppConstant.infoBean=info;
            GlideUtil.getInstance().LoadContextCircleBitmap(getActivity(),AppConstant.USERHEAD+info.getAvatar(),mIvHead,
                    R.drawable.icon_head_normal,R.drawable.icon_head_normal);
            mTVUserName.setText(info.getUsername());
        }
    }

    @Override
    public void changeHeadSuccess(UserHeadBean msg) {
        //mPresenter.loadPersonalData(AppConstant.UID);
        if(AppConstant.infoBean!=null) {
            AppConstant.infoBean.setAvatar(msg.getAvatar());
        }
        GlideUtil.getInstance().LoadContextCircleBitmap(activity,headUrl+msg.getAvatar(),mIvHead,
                R.drawable.icon_head_normal,R.drawable.icon_head_normal);
        ToastUtil.showbottomShortToast(msg.getInfo());
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }
    private void event(){
        subscription= RxBus.$().toObservable(Object.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object event) {
                        if (event instanceof UserHeadEvent) {
                            UserHeadEvent headEvent = (UserHeadEvent) event;
                            GlideUtil.getInstance().LoadContextCircleBitmap(activity, headEvent.getAvatar(), mIvHead,
                                    R.drawable.icon_head_normal, R.drawable.icon_head_normal);
                        }else if(event instanceof LoginEvent){
                            load();
                        }
                    }
                });
    }
    private void showLoginDialog(){
        final List<String> mDatas1=new ArrayList<>();
        mDatas1.add("登录");
        mDatas1.add("注册");
        dialog=new NormalSelectionDialog.Builder(activity)
                .setBoolTitle(true)
                .setTitleText("温馨提示\n尊敬的用户,您尚未登录,请选择登录或注册")
                .setTitleHeight(55)
                .setItemHeight(45)
                .setItemTextColor(R.color.blue)
                .setItemTextSize(14)
                .setItemWidth(0.95f)
                .setCancleButtonText("取消")
                .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                    @Override
                    public void onItemClick(NormalSelectionDialog dialog, View button, int position) {
                        switch (position){
                            case 0:
                                startActivity(LoginFragmentActivity.class);
                                break;
                            case 1:
                                startActivity(RegisterActivity.class);
                                break;
                        }
                        dialog.dismiss();
                    }
                }).setTouchOutside(true)
                .build();
        dialog.setData(mDatas1);
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
            RequestBody uidBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(AppConstant.UID));

            mPresenter.changeHeadSuccess(uidBody,body);
        }
    }
    @OnClick({R.id.set_me_iv,R.id.myorder_set_tv,R.id.mycollect_set_tv,R.id.myfootprint_set_tv,
            R.id.accountcenter_me_rl,R.id.freesample_me_rl,R.id.myrating_me_rl,R.id.contactus_rl
            ,R.id.abouttaosha_rl,R.id.seckillorder_me_rl,R.id.head_me_iv,R.id.managedeladdress_rl})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()){
            case R.id.set_me_iv:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivityForResult(SettinActivity.class,103);
                break;
            case R.id.myorder_set_tv:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyOrderAcitvity.class);
                break;
            case R.id.mycollect_set_tv:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyCollectActivity.class);
                break;
            case R.id.myfootprint_set_tv:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyFootprintActivity.class);
                break;
            case R.id.accountcenter_me_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(AccountCenterActivity.class);
                break;
            case R.id.freesample_me_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(FreeSampleActivity.class);
                break;
            case R.id.myrating_me_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(MyRatingActivity.class);
                break;
            case R.id.contactus_rl:

                startActivity(ContactUsActivity.class);
                break;
            case R.id.abouttaosha_rl:
                startActivity(AboutActivity.class);
                break;
            case R.id.seckillorder_me_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                startActivity(SecKillOrderActivity.class);
                break;
            case R.id.head_me_iv:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
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
                                        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA);
                                        dialog.dismiss();
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
                                        dialog.dismiss();
                                        break;
                                }

                            }
                        }).setTouchOutside(true)
                        .build();

                chaHeaddialog.setData(headData);
                chaHeaddialog.show();
                break;
            case R.id.managedeladdress_rl:
                if(AppConstant.isLogin==false){
                    if(dialog==null){
                        showLoginDialog();
                    }
                    dialog.show();
                    return;
                }
                Bundle bundle3=new Bundle();
                bundle3.putInt("TYPE",0);
                startActivity(MyAddressActivity.class,bundle3);
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
            case 103:
                if(AppConstant.infoBean==null){
                    mIvHead.setImageResource(R.drawable.icon_head_normal);
                    mTVUserName.setText("毛织网感谢您的支持,请登录");
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
