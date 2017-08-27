package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/8/14.
 */

public class FootpringBean {
    private List<FootprintListBean> history ;
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
    public List<FootprintListBean> getHistory() {
        return history;
    }

    public void setHistory(List<FootprintListBean> history) {
        this.history = history;
    }
}
