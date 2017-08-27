package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/7/11.
 */

public class SeckillListBean {
    private List<SeckillBean> seckilling ;
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public List<SeckillBean> getSeckilling() {
        return seckilling;
    }

    public void setSeckilling(List<SeckillBean> seckilling) {
        this.seckilling = seckilling;
    }
}
