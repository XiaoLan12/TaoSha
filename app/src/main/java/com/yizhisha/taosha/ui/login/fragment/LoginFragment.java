package com.yizhisha.taosha.ui.login.fragment;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WechatBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalAlertDialog;
import com.yizhisha.taosha.event.LoginEvent;
import com.yizhisha.taosha.event.WeChatEvent;
import com.yizhisha.taosha.ui.login.activity.RegisterActivity;
import com.yizhisha.taosha.ui.login.contract.LoginContract;
import com.yizhisha.taosha.ui.login.presenter.LoginPresenter;
import com.yizhisha.taosha.utils.CheckUtils;
import com.yizhisha.taosha.utils.SharedPreferencesUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by lan on 2017/6/27.
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.account_login_et)
    ClearEditText mEtAccount;
    @Bind(R.id.pwd_login_et)
    ClearEditText mEtPwd;
    @Bind(R.id.isShow_pwd_iv)
    ImageView mIvIsShowPwd;
    private boolean isHidden = true;
    public  switchFragmentListener switchFragmentListener;
    private IWXAPI api;
    private static final String APP_SECRET ="74595b3e89da0f0816e5e0f3ab441462";
    private Subscription subscription;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //对传递进来的Activity进行接口转换
        if(getActivity() instanceof switchFragmentListener){
            switchFragmentListener = ((switchFragmentListener)getActivity());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(getActivity());
            }
        });


        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        api = WXAPIFactory.createWXAPI(getActivity(), AppConstant.WEIXIN_APP_ID, false);
        // 将该app注册到微信
        api.registerApp(AppConstant.WEIXIN_APP_ID);
        event();
    }

    @Override
    public void loginSuccess(RequestStatusBean bean) {
        SharedPreferencesUtil.putValue(activity,"ISLOGIN",true);
        AppConstant.isLogin=true;
        AppConstant.UID=bean.getUid();
        SharedPreferencesUtil.putValue(activity,"UID",AppConstant.UID);
        RxBus.$().postEvent(new LoginEvent());
        activity.finish();
    }

    @Override
    public void weChatLoginSuccess(RequestStatusBean bean) {
        SharedPreferencesUtil.putValue(activity,"ISLOGIN",true);
        SharedPreferencesUtil.putValue(activity,"MOBILE",bean.getMobile());
        AppConstant.isLogin=true;
        AppConstant.UID=bean.getUid();
        SharedPreferencesUtil.putValue(activity,"UID",AppConstant.UID);
        RxBus.$().postEvent(new LoginEvent());
        activity.finish();
    }

    @Override
    public void registerSuccess(String info) {

    }
    @Override
    public void findPwdSuccess(String info) {

    }
    @Override
    public void getCodeSuccess(String info) {

    }
    @Override
    public void loadWeChatData(WechatBean wechatBean) {
        Map<String,String> map=new HashMap<>();
        map.put("openid",wechatBean.getOpenid());
        mPresenter.weChatLogin(map);
    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }

    @Override
    public void weChatLogin(String info) {
        new NormalAlertDialog.Builder(activity)
                .setBoolTitle(false)
                .setContentText("通过手机注册以后才能绑定微信哟，马上注册！")
                .setContentTextSize(18)
                .setLeftText("取消")
                .setRightText("前往")
                .setWidth(0.75f)
                .setHeight(0.33f)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                    @Override
                    public void clickLeftButton(NormalAlertDialog dialog, View view) {
                        dialog.dismiss();

                    }
                    @Override
                    public void clickRightButton(NormalAlertDialog dialog, View view) {
                        startActivity(RegisterActivity.class);
                        dialog.dismiss();

                    }
                }).build().show();
    }

    public interface switchFragmentListener{
        void switchFragment(int index);
    }
    /**
     * 检查输入
     *
     * @return
     */
    public boolean checkInput(String accout,String pwd) {
        // 账号为空时提示
        if (accout == null || accout.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入账号");
            return false;
        }
        if (!CheckUtils.isMobileNO(accout)) {
            ToastUtil.showCenterShortToast("请输入正确的手机号码");
            return false;
        }
        if (pwd == null || pwd.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入密码");
            return false;
        }
        return true;
    }
    private void event(){
        subscription= RxBus.$().toObservable(WeChatEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeChatEvent>() {
                    @Override
                    public void call(WeChatEvent event) {
                        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                                + AppConstant.WEIXIN_APP_ID
                                + "&secret="
                                + APP_SECRET
                                + "&code="
                                + event.getCode()
                                + "&grant_type=authorization_code";
                       mPresenter.loadWeChatData(url);
                    }
                });
    }
    @OnClick({R.id.findpwd_tv,R.id.register_tv,R.id.phone_login_tv,R.id.login_btn,
    R.id.isShow_pwd_iv,R.id.weixin_login_tv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.findpwd_tv:
                switchFragmentListener.switchFragment(1);
                break;
            case R.id.register_tv:
                startActivity(RegisterActivity.class);
                break;
            case R.id.phone_login_tv:
                switchFragmentListener.switchFragment(2);
                break;
            case R.id.login_btn:
                if(!checkInput(mEtAccount.getText().toString(),mEtPwd.getText().toString())){
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("mobile",mEtAccount.getText().toString().trim());
                map.put("password",mEtPwd.getText().toString().trim());
                mPresenter.login(map);

                break;
            case R.id.isShow_pwd_iv:
                if (isHidden) {
                    mIvIsShowPwd.setImageResource(R.drawable.icon_view);
                    mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mIvIsShowPwd.setImageResource(R.drawable.icon_close);
                    mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden = !isHidden;
                break;
            case R.id.weixin_login_tv:
                if (!api.isWXAppInstalled()) {
                    ToastUtil.showbottomShortToast("您还未安装微信客户端");
                    return;
                }
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "taosha_wx_login";
                api.sendReq(req);
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
