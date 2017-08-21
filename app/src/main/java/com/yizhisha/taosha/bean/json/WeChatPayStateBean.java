package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/8/21.
 */

public class WeChatPayStateBean {
    private String return_code;

    private String return_msg;

    private String appid;

    private String mch_id;

    private String nonce_str;

    private String sign;

    private String result_code;

    private String out_trade_no;

    private String trade_state;

    private String trade_state_desc;

    public String getReturn_code() {
        return return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }
}
