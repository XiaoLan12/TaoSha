package com.yizhisha.taosha.ui.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.bean.json.WechatBean;
import com.yizhisha.taosha.common.dialog.DialogInterface;
import com.yizhisha.taosha.common.dialog.NormalSelectionDialog;
import com.yizhisha.taosha.event.UserHeadEvent;
import com.yizhisha.taosha.event.WeChatEvent;
import com.yizhisha.taosha.ui.me.contract.SetContract;
import com.yizhisha.taosha.ui.me.presenter.SetPresenter;
import com.yizhisha.taosha.utils.GlideUtil;
import com.yizhisha.taosha.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SettinActivity extends BaseActivity<SetPresenter> implements SetContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.head_set_iv)
    ImageView headIv;
    @Bind(R.id.username_set_tv)
    TextView usernameTv;
    private static final String APP_SECRET ="74595b3e89da0f0816e5e0f3ab441462";
    private Subscription subscription;
    private IWXAPI api;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settin;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(SettinActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        if(AppConstant.infoBean!=null){
            GlideUtil.getInstance().LoadContextCircleBitmap(this,AppConstant.USERHEAD+AppConstant.infoBean.getAvatar(),headIv);
            usernameTv.setText(AppConstant.infoBean.getUsername());
        }
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        api = WXAPIFactory.createWXAPI(this, AppConstant.WEIXIN_APP_ID, false);
        // 将该app注册到微信
        api.registerApp(AppConstant.WEIXIN_APP_ID);
        event();
    }
    private void event(){
        subscription= RxBus.$().toObservable(Object.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object event) {
                        if(event instanceof WeChatEvent) {
                            WeChatEvent chatEvent= (WeChatEvent) event;
                            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                                    + AppConstant.WEIXIN_APP_ID
                                    + "&secret="
                                    + APP_SECRET
                                    + "&code="
                                    + chatEvent.getCode()
                                    + "&grant_type=authorization_code";
                            mPresenter.loadWeChatData(url);
                        }else if(event instanceof UserHeadEvent){
                            UserHeadEvent userHeadEvent= (UserHeadEvent) event;
                            GlideUtil.getInstance().LoadContextCircleBitmap(SettinActivity.this,userHeadEvent.getAvatar(),headIv);
                        }
                    }
                });
    }
    @OnClick({R.id.changepwd_rl, R.id.changephone_rl, R.id.changeoneinfo_rl,
            R.id.managedeladdress_rl, R.id.changeweixin_rl})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.changeoneinfo_rl:
                Bundle bundle = new Bundle();
                bundle.putInt("TARGET", 0);
                startActivity(SetInfoActivity.class, bundle);
                break;
            case R.id.changepwd_rl:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("TARGET", 1);
                startActivity(SetInfoActivity.class, bundle1);
                break;
            case R.id.changephone_rl:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("TARGET", 2);
                startActivity(SetInfoActivity.class, bundle2);
                break;
            case R.id.changeweixin_rl:
                final List<String> mDatas=new ArrayList<>();
                mDatas.add("绑定微信");
                mDatas.add("修改绑定的微信");
                NormalSelectionDialog dialog=new NormalSelectionDialog.Builder(this)
                        .setBoolTitle(true)
                        .setTitleText("微信登录绑定(修改)")
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
                                        if (!api.isWXAppInstalled()) {
                                            ToastUtil.showbottomShortToast("您还未安装微信客户端");
                                            return;
                                        }
                                        final SendAuth.Req req = new SendAuth.Req();
                                        req.scope = "snsapi_userinfo";
                                        req.state = "taosha_wx_login";
                                        api.sendReq(req);
                                        break;
                                    case 1:
                                        Bundle bundle3 = new Bundle();
                                        bundle3.putInt("TARGET", 3);
                                        startActivity(SetInfoActivity.class, bundle3);
                                        break;
                                }
                                dialog.dismiss();
                            }
                        }).setTouchOutside(true)
                        .build();
                dialog.setData(mDatas);
                dialog.show();

                break;
            case R.id.managedeladdress_rl:
                startActivity(MyAddressActivity.class);
                break;

        }
    }

    @Override
    public void loadWeChatData(WechatBean wechatBean) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("openid",wechatBean.getOpenid());
        mPresenter.bindWeChat(map);
    }

    @Override
    public void bindWeChat(String info) {
        ToastUtil.showShortToast(info);
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null&&!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
