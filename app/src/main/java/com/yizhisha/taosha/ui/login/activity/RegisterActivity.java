package com.yizhisha.taosha.ui.login.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.bean.json.WechatBean;
import com.yizhisha.taosha.bean.json.WechatInfoBean;
import com.yizhisha.taosha.event.LoginEvent;
import com.yizhisha.taosha.ui.login.contract.LoginContract;
import com.yizhisha.taosha.ui.login.presenter.LoginPresenter;
import com.yizhisha.taosha.utils.CheckUtils;
import com.yizhisha.taosha.utils.CountDownTimerUtil;
import com.yizhisha.taosha.utils.SharedPreferencesUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.getcode_register_tv)
    TextView mTvGetCode;
    @Bind(R.id.account_register_et)
    ClearEditText mEtAccount;
    @Bind(R.id.code_register_et)
    EditText mEtCode;
    @Bind(R.id.setpwd_register_et)
    ClearEditText mEtSetPwd;
    @Bind(R.id.againpwd_register_et)
    ClearEditText mEtAgainPwd;
    @Bind(R.id.company_register_et)
    ClearEditText mEtCompany;
    @Bind(R.id.terms_cb)
    CheckBox mCbTerm;

    @Bind(R.id.setpwd_register_iv)
    ImageView mIvIsShowSetPwd;
    @Bind(R.id.againpwd_register_iv)
    ImageView mIvIsShowAgainPwd;
    private boolean isHidden1 = true;
    private boolean isHidden2 = true;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish_Activity(RegisterActivity.this);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    public void loginSuccess(RequestStatusBean bean) {
        SharedPreferencesUtil.putValue(this,"ISLOGIN",true);
        AppConstant.isLogin=true;
        AppConstant.UID=bean.getUid();
        SharedPreferencesUtil.putValue(this,"UID",AppConstant.UID);
        RxBus.$().postEvent(new LoginEvent());
        finish_Activity(this);
    }

    @Override
    public void weChatLoginSuccess(RequestStatusBean bean) {

    }

    @Override
    public void registerSuccess(String info) {
        Map<String,String> map=new HashMap<>();
        map.put("mobile",mEtAccount.getText().toString().trim());
        map.put("password",mEtAgainPwd.getText().toString().trim());
        mPresenter.login(map);
    }
    @Override
    public void findPwdSuccess(String info) {

    }
    @Override
    public void getCodeSuccess(String info) {
        CountDownTimerUtil mCountDownTimerUtils = new CountDownTimerUtil(mTvGetCode, 60000, 1000);
        mCountDownTimerUtils.start();
        ToastUtil.showShortToast(info);
    }

    @Override
    public void loadWeChatData(WechatBean wechatBean) {

    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }

    @Override
    public void weChatLogin(String info) {
        ToastUtil.showbottomShortToast(info);
    }

    @Override
    public void bindWeChatSuccess(String info) {

    }

    @Override
    public void loadWeChatInfo(WechatInfoBean bean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 检查输入
     *
     * @return
     */
    public boolean checkInput(String accout,String code,String pwd,String againpwd,String company) {
        // 账号为空时提示
        if (accout == null || accout.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入你的手机号码");
            return false;
        }
        if (!CheckUtils.isMobileNO(accout)) {
            ToastUtil.showCenterShortToast("请输入正确的手机号码");
            return false;
        }
        if (code == null || code.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入验证码");
            return false;
        }
        if (pwd == null || pwd.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入密码");
            return false;
        }
        if (againpwd == null || againpwd.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入确认密码");
            return false;
        }
        if(!pwd.equals(againpwd)){
            ToastUtil.showCenterShortToast("输入的密码不一致");
            return false;
        }
        if (company == null || company.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入企业名称");
            return false;
        }
        if(!mCbTerm.isChecked()){
            ToastUtil.showCenterShortToast("请同意服务条款");
            return false;
        }

        return true;
    }
    @OnClick({R.id.getcode_register_tv,R.id.sure_findpwd_btn,R.id.setpwd_register_iv,
            R.id.againpwd_register_iv,R.id.login_register_tv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.getcode_register_tv:
                String phone=mEtAccount.getText().toString().trim();
                if (phone == null || phone.trim().equals("")) {
                    ToastUtil.showCenterShortToast("请输入你的手机号码");
                    return;
                }
                if (!CheckUtils.isMobileNO(phone)) {
                    ToastUtil.showCenterShortToast("请输入正确的手机号码");
                    return;
                }

                Map<String,String> map=new HashMap<>();
                map.put("mobile",mEtAccount.getText().toString().trim());

                mPresenter.getCode(map);
                break;
            case R.id.sure_findpwd_btn:
                if(!checkInput(mEtAccount.getText().toString(),mEtCode.getText().toString(),
                        mEtSetPwd.getText().toString(),mEtAgainPwd.getText().toString(),
                        mEtCompany.getText().toString())){
                    return;
                }
                Map<String,String> map1=new HashMap<>();

                map1.put("mobile",mEtAccount.getText().toString().trim());
                map1.put("mobilecode",mEtCode.getText().toString().trim());
                map1.put("password",mEtSetPwd.getText().toString().trim());
                map1.put("company",mEtCompany.getText().toString().trim());
                mPresenter.register(map1);
                break;
            case R.id.setpwd_register_iv:
                if (isHidden1) {
                    mIvIsShowSetPwd.setImageResource(R.drawable.icon_view);
                    mEtSetPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mIvIsShowSetPwd.setImageResource(R.drawable.icon_close);
                    mEtSetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden1 = !isHidden1;
                break;
            case R.id.againpwd_register_iv:
                if (isHidden2) {
                    mIvIsShowAgainPwd.setImageResource(R.drawable.icon_view);
                    mEtAgainPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mIvIsShowAgainPwd.setImageResource(R.drawable.icon_close);
                    mEtAgainPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                isHidden2 = !isHidden2;
                break;
            case R.id.login_register_tv:
                startActivity(LoginFragmentActivity.class);
                break;
        }
    }
}
