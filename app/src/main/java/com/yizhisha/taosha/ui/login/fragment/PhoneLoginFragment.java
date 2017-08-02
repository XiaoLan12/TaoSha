package com.yizhisha.taosha.ui.login.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.login.contract.PhoneLoginContract;
import com.yizhisha.taosha.ui.login.presenter.PhoneLoginPresenter;
import com.yizhisha.taosha.utils.CheckUtils;
import com.yizhisha.taosha.utils.CountDownTimerUtil;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lan on 2017/6/27.
 */

public class PhoneLoginFragment extends BaseFragment<PhoneLoginPresenter> implements
        PhoneLoginContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.getcode_phonelogin_tv)
    TextView mTvGetCode;
    @Bind(R.id.account_phonelogin_et)
    ClearEditText accountPhoneloginEt;
    @Bind(R.id.code_phonelogin_et)
    ClearEditText codePhoneloginEt;
    @Bind(R.id.sure_phonelogin_btn)
    Button surePhoneloginBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_phonelogin;
    }
    @Override
    protected void initView() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @OnClick({R.id.getcode_phonelogin_tv,R.id.sure_phonelogin_btn})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.getcode_phonelogin_tv:
                String phone=accountPhoneloginEt.getText().toString().trim();
                if (phone == null || phone.trim().equals("")) {
                    ToastUtil.showCenterShortToast("请输入你的手机号码");
                    return;
                }
                if (!CheckUtils.isMobileNO(phone)) {
                    ToastUtil.showCenterShortToast("请输入正确的手机号码");
                    return;
                }
                CountDownTimerUtil mCountDownTimerUtils = new CountDownTimerUtil(mTvGetCode, 60000, 1000);
                mCountDownTimerUtils.start();
                Map<String,String> map=new HashMap<>();
                map.put("mobile",phone);
                mPresenter.getCode(map);
                break;
            case R.id.sure_phonelogin_btn:
                String loginPhone=accountPhoneloginEt.getText().toString();
                String code=mTvGetCode.getText().toString();
                if(!checkInput(loginPhone,code)){
                    return;
                }
                Map<String,String> mapLogin=new HashMap<>();
                mapLogin.put("mobile",loginPhone);
                mapLogin.put("mobilecode",code);
                mPresenter.phoneLogin(mapLogin);
                break;
        }
    }
    @Override
    public void loginSuccess(RequestStatusBean info) {
        ToastUtil.showbottomShortToast(info.getInfo());
    }
    @Override
    public void getCodeSuccess(String info) {
        ToastUtil.showbottomShortToast(info);
    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }
    /**
     * 检查输入
     *
     * @return
     */
    public boolean checkInput(String accout,String code) {
        // 账号为空时提示
        if (accout == null || accout.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入手机号码");
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
        return true;
    }
}
