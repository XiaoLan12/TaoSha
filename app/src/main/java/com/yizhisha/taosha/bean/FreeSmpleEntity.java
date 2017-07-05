package com.yizhisha.taosha.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by lan on 2017/7/5.
 */

public class FreeSmpleEntity implements CustomTabEntity {
    private String title;
    private int type;

    public FreeSmpleEntity(String title, int type) {
        this.title = title;
        this.type = type;
    }

    @Override
    public String getTabTitle() {
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
