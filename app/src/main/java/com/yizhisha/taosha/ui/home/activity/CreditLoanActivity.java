package com.yizhisha.taosha.ui.home.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.PopupListBean;
import com.yizhisha.taosha.common.popupwindow.ListPopupwindow;
import com.yizhisha.taosha.ui.home.fragment.CreditLoanFragment;
import com.yizhisha.taosha.ui.home.fragment.LiuShuiDaiFragment;
import com.yizhisha.taosha.ui.home.fragment.ShengYiDaiFragment;
import com.yizhisha.taosha.ui.home.fragment.ShuiYinDaiFragment;
import com.yizhisha.taosha.ui.login.fragment.LoginFragment;
import com.yizhisha.taosha.utils.DensityUtil;
import com.yizhisha.taosha.utils.RescourseUtil;

import java.util.ArrayList;

import butterknife.Bind;

public class CreditLoanActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.toolbar_rightbutton)
    TextView rightIv;
    private ListPopupwindow mPopup;
    private Fragment currentFragment;
    private CreditLoanFragment creditLoanFragment;
    private ShuiYinDaiFragment shuiYinDaiFragment;
    private ShengYiDaiFragment shengYiDaiFragment;
    private LiuShuiDaiFragment liuShuiDaiFragment;

    private FragmentManager fragmentManager;

    private final String[] title = new String[]{"立即申请", "税银贷", "流水贷", "生意贷"};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_credit_loan;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_Activity(CreditLoanActivity.this);

            }
        });
        toolbar.showRightButton();
        toolbar.setRightButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopup == null) {
                    initPopup();
                }
                if (!mPopup.isShowing()) {
                    mPopup.showringht(rightIv);
                }
            }
        });
    }
    @Override
    protected void initView() {
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        creditLoanFragment=new CreditLoanFragment();
        switchFragment(creditLoanFragment,"creditLoanFragment");
        currentFragment=creditLoanFragment;
    }
    /**
     * hide和show切换Fragment
     */
    private void switchFragment(Fragment targetFragment, String tag) {
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            if(currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction
                    .add(R.id.fragment, targetFragment,tag).
                    addToBackStack(tag);

            transaction.commit();
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }
    private void initPopup() {
        ArrayList<PopupListBean> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            PopupListBean popupListBean = new PopupListBean();
            popupListBean.setTitle(title[i]);
            list.add(popupListBean);
        }
        // 实例化标题栏弹窗
        mPopup = new ListPopupwindow(this, DensityUtil.getScreenWidth(this)/2,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setItemOnClickListener(new ListPopupwindow.OnItemOnClickListener() {
            @Override
            public void onItemClick(PopupListBean item, int position) {
             switch (position){
                 case 0:
                     if(creditLoanFragment==null){
                         creditLoanFragment=new CreditLoanFragment();
                     }
                     switchFragment(creditLoanFragment,"creditLoanFragment");
                     break;
                 case 1:
                     if(shuiYinDaiFragment==null){
                         shuiYinDaiFragment=new ShuiYinDaiFragment();
                     }
                     switchFragment(shuiYinDaiFragment,"shuiYinDaiFragment");
                     break;
                 case 2:
                     if(liuShuiDaiFragment==null){
                         liuShuiDaiFragment=new LiuShuiDaiFragment();
                     }
                     switchFragment(liuShuiDaiFragment,"liuShuiDaiFragment");
                     break;
                 case 3:
                     if(shengYiDaiFragment==null){
                         shengYiDaiFragment=new ShengYiDaiFragment();
                     }
                     switchFragment(shengYiDaiFragment,"shengYiDaiFragment");
                     break;
             }
            }
        });
        // 给标题栏弹窗添加子类
        mPopup.addAction(list);
        mPopup.setItemSelected(-1);
        mPopup.setItemTextColor(R.color.blue);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断当前按键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 获取当前回退栈中的Fragment个数
            int backStackEntryCount = fragmentManager.getBackStackEntryCount();
            // 判断当前回退栈中的fragment个数,
            if (backStackEntryCount > 0) {
                // 获取当前退到了哪一个Fragment上,重新获取当前的Fragment回退栈中的个数
                FragmentManager.BackStackEntry backStack = fragmentManager
                        .getBackStackEntryAt(fragmentManager
                                .getBackStackEntryCount() - 1);
                // 获取当前栈顶的Fragment的标记值
                String tag = backStack.getName();
                if("creditLoanFragment".equals(tag)){
                    finish_Activity(this);
                }else {
                    // 立即回退一步
                    fragmentManager.popBackStackImmediate("creditLoanFragment", 0);
                }

            } else {
                //回退栈中只剩一个时,退出应用
                finish_Activity(this);
            }
        }
        return true;
    }
}
