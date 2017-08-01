package com.yizhisha.taosha.event;

/**
 * Created by lan on 2017/8/1.
 */

public class ChangeUserInfoEvent{
    private int type;
    private String value;
    public ChangeUserInfoEvent(int type,String value){
        this.type=type;
        this.value=value;
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
