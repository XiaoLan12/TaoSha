package com.yizhisha.taosha.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class SquareIndexLayout extends RelativeLayout {
    public SquareIndexLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);  
    }  
  
    public SquareIndexLayout(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }  
  
    
    public SquareIndexLayout(Context context) {
        super(context);  
    }  
  
    @SuppressWarnings("unused")  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // For simple implementation, or internal size is always 0.  
        // We depend on the container to specify the layout size of  
        // our view. We can't really know what it is since we will be  
        // adding and removing different arbitrary views and do not  
        // want the layout to change as this happens.  
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));  
  
        // Children are just made to fill our space.  
        int childWidthSize = getMeasuredWidth();  
        int childHeightSize = getMeasuredHeight();
//        <span style="color: rgb(153, 153, 153); font-family: Monaco, MonacoRegular, 'Courier New', monospace;  line-height: 15px; white-space: pre; background-color: rgb(248, 248, 255);">
//</span>  
          widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        heightMeasureSpec=widthMeasureSpec*3/8;
//        heightMeasureSpec= new Double(widthMeasureSpec*(0.3)).intValue();
//        Log.e("III",widthMeasureSpec+"---"+heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }  
}  
