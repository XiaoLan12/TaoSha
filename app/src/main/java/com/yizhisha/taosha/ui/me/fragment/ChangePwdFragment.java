package com.yizhisha.taosha.ui.me.fragment;

import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.ui.me.contract.ChangePwdContract;
import com.yizhisha.taosha.ui.me.presenter.ChangePwdPresenter;
import com.yizhisha.taosha.utils.CheckUtils;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ChangePwdFragment extends BaseFragment<ChangePwdPresenter> implements
        ChangePwdContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.old_pwd_et)
    ClearEditText mEtOldPwd;
    @Bind(R.id.new_pwd_et)
    ClearEditText mEtNewPwd;
    @Bind(R.id.again_pwd_et)
    ClearEditText mEtAgainPwd;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_changepwd;
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
    public void changePwdSuccess(String msg) {
        ToastUtil.showbottomShortToast(msg);
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
    public boolean checkInput(String oldpwd,String pwd,String againpwd) {

        if (oldpwd == null || oldpwd.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入原登录密码");
            return false;
        }
        if (pwd == null || pwd.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入新的登录密码");
            return false;
        }
        if (againpwd == null || againpwd.trim().equals("")) {
            ToastUtil.showCenterShortToast("请再次输入新的密码");
            return false;
        }
        if(!pwd.equals(againpwd)){
            ToastUtil.showCenterShortToast("输入的密码不一致");
            return false;
        }
        return true;
    }
    @OnClick({R.id.save_changepwd_btn})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.save_changepwd_btn:
                String oldPwd=mEtOldPwd.getText().toString().trim();
                String newPwd=mEtNewPwd.getText().toString().trim();
                String againPwd=mEtAgainPwd.getText().toString().trim();
                if(!checkInput(oldPwd,newPwd,againPwd)){
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("uid", String.valueOf(AppConstant.UID));
                map.put("password1",oldPwd);
                map.put("password2",againPwd);
                mPresenter.changePwd(map);
                break;
        }
    }



}
