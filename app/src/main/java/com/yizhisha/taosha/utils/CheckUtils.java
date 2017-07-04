package com.yizhisha.taosha.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lan on 2017/4/25.
 */
public class CheckUtils {
    /**
     * 验证手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证密码:是否为数字、字母、下划线
     * @param pwd
     * @return
     */
    public static boolean isPwd(String pwd){
        Pattern p=Pattern.compile("^[A-Za-z0-9_]{6,20}$");
        Matcher m=p.matcher(pwd);
        return m.matches();
    }

    /**
     * 验证是否为字符串
     * @param str
     * @return
     */
    public static boolean isString(String str){
        Pattern p=Pattern.compile("^[\u4E00-\u9FA5A-Za-z0-9 ]+$");
        Matcher m=p.matcher(str);
        return m.matches();
    }
    /**
     * 验证是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }
    /*
     * 获取应用版本
     */
    public static float getVersionCode(Context context){

        int versionCode = 0;
        try {
            //获取包管理者
            PackageManager pm = context.getPackageManager();
            //获取packageInfo
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            //获取versionCode
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionCode;
    }
}
