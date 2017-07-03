package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class IndexPPT{
    private String ppt;
    private List<IndexImg> img;

    public String getPpt() {
        return ppt;
    }

    public void setPpt(String ppt) {
        this.ppt = ppt;
    }

    public List<IndexImg> getImg() {
        return img;
    }

    public void setImg(List<IndexImg> img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "IndexPPT{" +
                "ppt='" + ppt + '\'' +
                ", img=" + img +
                '}';
    }
}
