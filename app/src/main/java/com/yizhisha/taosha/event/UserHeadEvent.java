package com.yizhisha.taosha.event;

/**
 * Created by lan on 2017/8/16.
 */

public class UserHeadEvent {
    private String avatar;

    public UserHeadEvent(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
