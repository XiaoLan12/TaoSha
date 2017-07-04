package com.yizhisha.taosha.ui.me.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
import com.yizhisha.taosha.ui.me.activity.ChangeUserNameActivity;
import com.yizhisha.taosha.ui.me.contract.ChangeOneInfoContract;
import com.yizhisha.taosha.ui.me.presenter.ChangeOneInfoPresenter;

import butterknife.Bind;
import butterknife.OnClick;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_changeoneinfo;
    }

    @Override
    protected void initView() {
        mPresenter.loadPersonalData(240);
    }
    @Override
    public void loadPersonalDataSuccess(PersonalDataBean personalDataBean) {
        if(personalDataBean==null){
            return;
        }
        mTvUserName.setText(personalDataBean.getUsername());
        mTvRealName.setText(personalDataBean.getLinkman());
        mTvSex.setText(personalDataBean.getSex());
        mTvEmail.setText(personalDataBean.getEmail());
        mTvCompanyName.setText(personalDataBean.getCompany());

    }
    @Override
    public void loadFail(String msg) {

    }
    @OnClick({R.id.changeusernamee_rl})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.changeusernamee_rl:
                startActivity(ChangeUserNameActivity.class);
                break;

        }
    }
}
