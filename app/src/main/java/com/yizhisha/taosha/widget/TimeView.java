package com.yizhisha.taosha.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.utils.DateUtil;
import com.yizhisha.taosha.utils.DensityUtil;
import com.yizhisha.taosha.utils.LogUtil;
import com.yizhisha.taosha.utils.RescourseUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by lan on 2017/7/24.
 */

public class TimeView extends View {
    private String mTitle;
    private int mTitleColor;
    private int mTitleSize;

    private Rect mBound;
    private Paint mPaint;

    long startTime;//开始时间
    long endTime;//结束时间
    long subTime;//服务器时间
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            invalidate();
        }

        ;

    };

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
         /*
        获得自定义属性
         */
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TimeView, defStyleAttr, 0);
        int a = typedArray.getIndexCount();
        for (int i = 0; i < a; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.TimeView_titles:
                    mTitle = typedArray.getString(attr);
                    break;
                case R.styleable.TimeView_titleColors:
                    mTitleColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.TimeView_titleSizes:
                    mTitleSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
        /*
        获得绘制的区域
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
    }
    /**
     * 重写onMeasure
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleSize);
            mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            width = (int) (getPaddingLeft() + mBound.width() + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleSize);
            mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
            height = (int) (getPaddingTop() + mBound.height() + getPaddingBottom());
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Date curDate = new Date();// 获取当前时间
        long cur = curDate.getTime();// 获取当前时间
        mPaint.setColor(mTitleColor); // 这里设置 画笔颜色
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(RescourseUtil.getColor(R.color.red1));
        canvas.drawText(StartAndEndTimeDiff(cur+subTime), getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
        mHandler.sendEmptyMessageDelayed(1, 1000);
    }
    /**
     * 剩余毫秒值 转换成 剩余时间格式
     *
     * @param t
     * @return
     */
    public String time2time(long t) {
        if (t <= 0) {
            return "00时:00分:00秒:000";
        }

        long haomiao = t % 1000;

        long miao = t / 1000 % 60;
        long min = t / 1000 / 60 % 60;
        long h = t / 1000 / 60 / 60;

        return h + "时:" + min + "分:" + String.format("%02d", miao) + "秒:"
                + String.format("%03d", haomiao);

    }

    /**
     * @param subTime 北京时间
     * @return 计算活动开始时间与当前时间的差值
     */
    public String StartAndEndTimeDiff(long subTime) {
        if (startTime - subTime > 0) {
            return "活动未开始";
        } else if (endTime - subTime > 0) {
            return "活动进行中";
        } else {
            return "活动已结束";
        }
    }

    /**
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @param curtime 服务器时间
     * @throws ParseException
     */
    public void setData(long starttime,long endtime,long  curtime) throws ParseException {
        startTime=starttime;
        endTime=endtime;
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        long cur1 = curDate.getTime();// 获取当前时间
        subTime = curtime -cur1;//获得时间差
    }

}