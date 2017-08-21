package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/8/21.
 */

public class PayReqBean {
    private String appid;

    private String partnerid;

    private String prepayid;

    private String noncestr;

    private String timestamp;


    private String sign;

    public String getAppid() {
        return appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }



    public String getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return "PayReqBean{" +
                "appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
