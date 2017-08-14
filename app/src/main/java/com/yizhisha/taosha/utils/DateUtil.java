package com.yizhisha.taosha.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lan on 2017/7/12.
 */

public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        /*时间戳转换成字符窜*/
        public static String getDateToString(long time) {
            Date d = new Date(time);
            return sdf.format(d);
        }
              /*时间戳转换成字符窜*/
    public static String getDateToString1(long time) {
        Date d = new Date(time);
        return sdf1.format(d);
    }
    public static long getDateTimeDiff(Long startdate, Long endDate) {

        //前的时间
        Date fd = new Date(startdate*1000l);
        //后的时间
        Date td = new Date(endDate*1000l);
        //两时间差,精确到毫秒
        long diff = td.getTime() - fd.getTime();


        return diff;
    }
    public static String subDateTime(Long startdate, Long endDate) {

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

    /**
     * @param subdate 服务器时间
     *                获得服务器和当前时间的差值
     * @return
     */
    public static long DateTimeDiff(Long subdate) {

        //前的时间
        Date curDate = new Date();
        //后的时间
        Date subDate = new Date(subdate);
        //两时间差,精确到毫秒
        long diff = subDate.getTime() - curDate.getTime();
        return diff;
    }

    /**
     * @param diffdate 时间差
     *                 计算本地时间与差值的和，得到北京时间
     * @return
     */
    public static long DateTimeSum(Long diffdate) {

        Date curDate = new Date();// 获取当前时间
        //后的时间
        Date subDate = new Date(diffdate);
        //两时间差,精确到毫秒
        long sum = curDate.getTime() + subDate.getTime();
        return sum;
    }
    public static int getTimeintervalDay(long time1,long time2){
        //前的时间
        Date date1 = new Date(time1*1000);
        //后的时间
        Date date2 = new Date(time2*1000);
        int days = (int)((date1.getTime() - date2.getTime())/(1000 * 60 * 60 * 24));
        return days;
    }
}
