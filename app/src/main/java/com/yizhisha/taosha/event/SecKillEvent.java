package com.yizhisha.taosha.event;

/**
 * Created by lan on 2017/8/23.
 */

public class SecKillEvent {
    private int state;
    public SecKillEvent(int state){
        this.state=state;
    }

    public int getState() {
        return state;
    }
}
