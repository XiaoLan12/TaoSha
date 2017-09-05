package com.yizhisha.taosha.common.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yizhisha.taosha.R;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class LoginWithWeiPopuwindow extends PopupWindow {
    private View mContentView;
    private Activity mActivity;


    private TextView tv_login_with_wei,tv_register,tv_cancle;

    private OnContinueClickListener onContinueClickListener;
    private OnBackIndexClickListener onBackIndexClickListerner;
    public LoginWithWeiPopuwindow(Activity activity){
        mActivity=activity;
        // 获得屏幕的宽度和高度
        WindowManager wm = (WindowManager) activity
                .getSystemService(Context.WINDOW_SERVICE);
       int mScreenWidth = wm.getDefaultDisplay().getWidth();


        this.tv_login_with_wei = tv_login_with_wei;
        this.tv_register=tv_register;
        setWidth((int) (mScreenWidth*0.7));
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        mContentView = LayoutInflater.from(activity).inflate(R.layout.popup_login_with_wei, null);
        setContentView(mContentView);
        setFocusable(true);

        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        setOutsideTouchable(false);
        setTouchable(true);
        tv_login_with_wei= (TextView) mContentView.findViewById(R.id.tv_login_with_wei);
        tv_register= (TextView) mContentView.findViewById(R.id.tv_register);
        tv_cancle=(TextView) mContentView.findViewById(R.id.tv_cancle);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                lighton();
            }
        });
        tv_login_with_wei.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(onBackIndexClickListerner!=null){
                    onBackIndexClickListerner.onBackIndexClickListener();
                }

                dismiss();

            }
        });
        tv_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(onContinueClickListener!=null){
                    onContinueClickListener.onContinueClickListener();
                }

                dismiss();

            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


      /*  mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });*/
    }

    private void lighton() {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 1.0f;
        mActivity.getWindow().setAttributes(lp);
    }

    private void lightoff() {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 0.3f;
        mActivity.getWindow().setAttributes(lp);
    }
    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        lightoff();
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        lightoff();
        super.showAtLocation(parent, gravity, x, y);
    }

    public interface OnContinueClickListener{
        public void onContinueClickListener();
    }

    public void setOnContinueClickListener(OnContinueClickListener onContinueClickListener) {
        this.onContinueClickListener = onContinueClickListener;
    }

    public interface  OnBackIndexClickListener{
        public void onBackIndexClickListener();
    }
    public void setOnBackIndexClickListener(OnBackIndexClickListener onBackIndexClickListener) {
        this.onBackIndexClickListerner = onBackIndexClickListener;
    }
}