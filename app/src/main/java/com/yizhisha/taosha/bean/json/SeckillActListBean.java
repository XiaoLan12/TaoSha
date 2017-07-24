package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/7/12.
 */

public class SeckillActListBean {
    private List<SeckillActBean> seckilling ;

    private int nowtime;

    public int getNowtime() {
        return nowtime;
    }

    public void setNowtime(int nowtime) {
        this.nowtime = nowtime;
    }

    public List<SeckillActBean> getSeckilling() {
        return seckilling;
    }

    public void setSeckilling(List<SeckillActBean> seckilling) {
        this.seckilling = seckilling;
    }
}
