package com.yizhisha.taosha.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lan on 2017/7/12.
 */

public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    /*时间戳转换成字符窜*/
        /*时间戳转换成字符窜*/
        public static String getDateToString(long time) {
            Date d = new Date(time);
            return sdf.format(d);
    }
    public static String subDateTime(Long startdate, Long endDate) {
        String over="活动已结束";
        if (startdate.equals(endDate)) {
            return over;
        }
            //前的时间
            Date fd = new Date(startdate*1000l);
            //后的时间
            Date td = new Date(endDate*1000l);
            //两时间差,精确到毫秒
            long diff = td.getTime() - fd.getTime();
            long day = diff / 86400000;                         //以天数为单位取整
            long hour = diff % 86400000 / 3600000;               //以小时为单位取整
            long min = diff % 86400000 % 3600000 / 60000;       //以分钟为单位取整
            long seconds = diff % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整
            //天时分秒
            StringBuilder str = new StringBuilder();
                str.append(hour).append(":");
                str.append(min).append(":");
                str.append(seconds);
            return str.toString();
    }
}
