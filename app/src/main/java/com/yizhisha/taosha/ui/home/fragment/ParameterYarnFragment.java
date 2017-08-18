package com.yizhisha.taosha.ui.home.fragment;

import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.ProductDetailImgAdapter;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.bean.json.ProductDeatilItemBean;
import com.yizhisha.taosha.bean.json.SeckillProductBean;

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

    private SeckillProductBean.Goods goods=null;
    private ProductDeatilItemBean goods1=null;
    private int Type;

    public static ParameterYarnFragment getInstance(int type, SeckillProductBean.Goods goods, ProductDeatilItemBean goods1) {
        ParameterYarnFragment sf = new ParameterYarnFragment();
        sf.Type=type;
        if(type==1){
            sf.goods=goods;
        }else{
            sf.goods1=goods1;
        }
        return sf;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_parameter_yarn;
    }
    @Override
    protected void initView() {
        if(Type==1){
            tv_product_code.setText(goods.getCode());
            tv_ingredient.setText(goods.getColor());
            tv_needle_name.setText(goods.getNeedle_name());
            tv_yam.setText(goods.getYam());
            tv_pname.setText(goods.getPname());
            tv_brand.setText(goods.getBrand());
        }else{
            tv_product_code.setText(goods1.getCode());
            tv_ingredient.setText(goods1.getId()+"");
            tv_session_name.setText(goods1.getSession_name());
            tv_needle_name.setText(goods1.getNeedle_name());
            tv_yam.setText(goods1.getYam());
            tv_pname.setText(goods1.getPname());
            tv_brand.setText(goods1.getBrand());
        }
    }
}
