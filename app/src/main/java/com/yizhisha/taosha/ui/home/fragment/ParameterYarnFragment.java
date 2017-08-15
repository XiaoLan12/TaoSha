package com.yizhisha.taosha.ui.home.fragment;

import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/7/2.
 */

public class ParameterYarnFragment extends BaseFragment{
    @Bind(R.id.tv_product_code)
    TextView tv_product_code;
    @Bind(R.id.tv_ingredient)
    TextView tv_ingredient;
    @Bind(R.id.tv_session_name)
    TextView tv_session_name;
    @Bind(R.id.tv_needle_name)
    TextView tv_needle_name;
    @Bind(R.id.tv_yam)
    TextView tv_yam;
    @Bind(R.id.tv_pname)
    TextView tv_pname;
    @Bind(R.id.tv_brand)
    TextView tv_brand;

    private boolean isLoad=false;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_parameter_yarn;
    }
    @Override
    public void setUserVisibleHint(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {
            if (AppConstant.productDetailBean != null & isLoad == false) {
                isLoad = true;
                tv_product_code.setText(AppConstant.productDetailBean.getGoods().getCode());
                tv_ingredient.setText(AppConstant.productDetailBean.getGoods().getId());
                tv_session_name.setText(AppConstant.productDetailBean.getGoods().getSession_name());
                tv_needle_name.setText(AppConstant.productDetailBean.getGoods().getNeedle_name());
                tv_yam.setText(AppConstant.productDetailBean.getGoods().getYam());
                tv_pname.setText(AppConstant.productDetailBean.getGoods().getPname());
                tv_brand.setText(AppConstant.productDetailBean.getGoods().getBrand());

            }
        }
    }
    @Override
    protected void initView() {

    }
}
