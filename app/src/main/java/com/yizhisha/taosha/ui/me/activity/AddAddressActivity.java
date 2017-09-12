package com.yizhisha.taosha.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;
import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.json.AddressListBean;
import com.yizhisha.taosha.bean.json.RequestStatusBean;
import com.yizhisha.taosha.ui.me.contract.AddAddressContract;
import com.yizhisha.taosha.ui.me.presenter.AddAddressPresenter;
import com.yizhisha.taosha.utils.CheckUtils;
import com.yizhisha.taosha.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity<AddAddressPresenter> implements AddAddressContract.View{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.consignee_address_et)
    EditText mEtConsignee;
    @Bind(R.id.phone_address_et)
    EditText mEtPhone;
    @Bind(R.id.detailaddress_address_et)
    EditText mEtDetailAddress;
    @Bind(R.id.area_address_tv)
    TextView mTvArea;
    @Bind(R.id.setnormal_addaddress_cb)
    CheckBox mCbNormal;

    private int isNormal;

    private int type=0;

    private int addressId=0;

    private CityPickerView mCityPickerView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initToolBar() {
        Bundle bundle=getIntent().getExtras();
        type=bundle.getInt("TYPE");
        if(type==1){
            toolbar.setTitle("修改地址");
            addressId=bundle.getInt("ID");
            load(addressId);
        }
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(AddAddressActivity.this);
            }
        });

    }
    @Override
    protected void initView() {
        mCbNormal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isNormal=1;
                }else{
                    isNormal=0;
                }
            }
        });
        mCityPickerView = new CityPickerView(this);
    }
    private void load(int id){
        Map<String,String> map=new HashMap<>();
        map.put("uid",String.valueOf(AppConstant.UID));
        map.put("id",String.valueOf(id));
        mPresenter.loadOneAddress(map);
    }
    @Override
    public void addAddressSuccess(RequestStatusBean data) {
        setResult(2);
        ActivityManager.getActivityMar().finishActivity(this);
    }

    @Override
    public void loadOneAddressSuccess(AddressListBean.Address address) {

        mEtConsignee.setText(address.getLinkman());
        mEtPhone.setText(address.getMobile());
        mEtDetailAddress.setText(address.getAddress());
        mTvArea.setText(address.getArea_app());
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
    public boolean checkInput(String consignee,String phone,String detailaddress) {
        // 账号为空时提示
        if (consignee == null || consignee.trim().equals("")) {
            ToastUtil.showCenterShortToast("请填写收件人");
            return false;
        }
        if (phone == null || phone.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入手机号码");
            return false;
        }

        if (!CheckUtils.isMobileNO(phone)) {
            ToastUtil.showCenterShortToast("请输入正确的手机号码");
            return false;
        }

        if (detailaddress == null || detailaddress.trim().equals("")) {
            ToastUtil.showCenterShortToast("请输入详细的收货地址");
            return false;
        }

        return true;
    }
    public void selectCity(){
        // 设置点击外部是否消失
//        mCityPickerView.setCancelable(true);
        // 设置滚轮字体大小
//        mCityPickerView.setTextSize(18f);
        // 设置标题
//        mCityPickerView.setTitle("我是标题");
        // 设置取消文字
//        mCityPickerView.setCancelText("我是取消文字");
        // 设置取消文字颜色
//        mCityPickerView.setCancelTextColor(Color.GRAY);
        // 设置取消文字大小
//        mCityPickerView.setCancelTextSize(14f);
        // 设置确定文字
//        mCityPickerView.setSubmitText("我是确定文字");
        // 设置确定文字颜色
//        mCityPickerView.setSubmitTextColor(Color.BLACK);
        // 设置确定文字大小
//        mCityPickerView.setSubmitTextSize(14f);
        // 设置头部背景
//        mCityPickerView.setHeadBackgroundColor(Color.RED);
        mCityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener(){
            @Override
            public void onCitySelect(String str) {
                // 一起获取
                mTvArea.setText(str);
            }
        });
        mCityPickerView.show();
    }
    @OnClick({R.id.sava_address_tv,R.id.addaddress_ll3})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.sava_address_tv:
                if(!checkInput(mEtConsignee.getText().toString(),mEtPhone.getText().toString(),mEtDetailAddress.getText().toString())){
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("uid",String.valueOf(AppConstant.UID));
                if(type==1){
                map.put("id",String.valueOf(addressId));
                }
                map.put("index",String.valueOf(isNormal));
                map.put("linkman",mEtConsignee.getText().toString());
                map.put("area_app",mTvArea.getText().toString());
                map.put("mobile",mEtPhone.getText().toString());
                map.put("address",mEtDetailAddress.getText().toString());
                mPresenter.addAddress(map);
                break;
            case R.id.addaddress_ll3:
                selectCity();
                break;
        }
    }
}
