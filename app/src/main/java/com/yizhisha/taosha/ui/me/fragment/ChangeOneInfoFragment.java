package com.yizhisha.taosha.ui.me.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.json.PersonalDataBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.event.ChangeUserInfoEvent;
import com.yizhisha.taosha.ui.me.activity.ChangeUserNameActivity;
import com.yizhisha.taosha.ui.me.contract.ChangeOneInfoContract;
import com.yizhisha.taosha.ui.me.presenter.ChangeOneInfoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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
    private List<String> sexDatas;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_changeoneinfo;
    }

    @Override
    protected void initView() {
        mPresenter.loadPersonalData(AppConstant.UID);
        changeEvent();
        sexDatas=new ArrayList<>();
        sexDatas.add("男");
        sexDatas.add("女");
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
    @OnClick({R.id.changeusernamee_rl,R.id.realname_rl,R.id.e_mail_rl,R.id.sex_rl})
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
                NormalSelectionDialog dialog=new NormalSelectionDialog.Builder(activity)
                        .setItemHeight(45)
                        .setItemTextColor(R.color.blue)
                        .setItemTextSize(14)
                        .setItemWidth(0.7f)
                        .setCancleButtonText("取消")
                        .setOnItemListener(new DialogInterface.OnItemClickListener<NormalSelectionDialog>() {
                            @Override
                            public void onItemClick(NormalSelectionDialog dialog, View button, int position) {

                            }
                        }).setTouchOutside(true)
                        .build();
                dialog.setData(sexDatas);
                dialog.show();
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
