package com.yizhisha.taosha.ui.login.fragment;

import android.app.Activity;
import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.ui.login.contract.LoginContract;
import com.yizhisha.taosha.ui.login.presenter.LoginPresenter;
import com.yizhisha.taosha.utils.CheckUtils;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

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
    }

    @Override
    public void loginSuccess(String info) {

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
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
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
    @OnClick({R.id.findpwd_tv,R.id.register_tv,R.id.phone_login_tv,R.id.login_btn,
    R.id.isShow_pwd_iv})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.findpwd_tv:
                switchFragmentListener.switchFragment(1);
                break;
            case R.id.register_tv:
                switchFragmentListener.switchFragment(2);
                break;
            case R.id.phone_login_tv:
                switchFragmentListener.switchFragment(3);
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
        }
    }
}
