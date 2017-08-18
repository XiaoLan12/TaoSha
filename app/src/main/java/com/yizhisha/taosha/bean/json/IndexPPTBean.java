package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class IndexPPTBean {

    private List<IndexImgBean> ppt;

    private List<IndexADSBean> ads;


    public List<IndexImgBean> getPpt() {
        return ppt;
    }

    public void setPpt(List<IndexImgBean> ppt) {
        this.ppt = ppt;
    }

    public List<IndexADSBean> getAds() {
        return ads;
    }

    public void setAds(List<IndexADSBean> ads) {
        this.ads = ads;
    }

    @Override
    public String toString() {
        return "IndexPPTBean{" +
                "ppt=" + ppt +
                '}';
    }
}
