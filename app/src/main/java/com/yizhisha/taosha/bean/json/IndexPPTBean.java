package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class IndexPPTBean {

    private List<IndexImgBean> ppt;

    public List<IndexImgBean> getPpt() {
        return ppt;
    }

    public void setPpt(List<IndexImgBean> ppt) {
        this.ppt = ppt;
    }

    @Override
    public String toString() {
        return "IndexPPTBean{" +
                "ppt=" + ppt +
                '}';
    }
}
