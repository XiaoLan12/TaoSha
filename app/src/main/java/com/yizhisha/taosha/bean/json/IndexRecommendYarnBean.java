package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/7/8.
 */

public class IndexRecommendYarnBean {
    private List<IndexDeatailYarnBean> mafangsha;
    private List<IndexDeatailYarnBean> maofangsha;
    private List<IndexDeatailYarnBean>  mianfangsha;
    private List<IndexDeatailYarnBean> hunfangsha;
    private List<IndexDeatailYarnBean> huashisha;
    private List<IndexDeatailYarnBean> huaxiansha;

    public List<IndexDeatailYarnBean> getMafangsha() {
        return mafangsha;
    }

    public void setMafangsha(List<IndexDeatailYarnBean> mafangsha) {
        this.mafangsha = mafangsha;
    }

    public List<IndexDeatailYarnBean> getMaofangsha() {
        return maofangsha;
    }

    public void setMaofangsha(List<IndexDeatailYarnBean> maofangsha) {
        this.maofangsha = maofangsha;
    }

    public List<IndexDeatailYarnBean> getMianfangsha() {
        return mianfangsha;
    }

    public void setMianfangsha(List<IndexDeatailYarnBean> mianfangsha) {
        this.mianfangsha = mianfangsha;
    }

    public List<IndexDeatailYarnBean> getHunfangsha() {
        return hunfangsha;
    }

    public void setHunfangsha(List<IndexDeatailYarnBean> hunfangsha) {
        this.hunfangsha = hunfangsha;
    }

    public List<IndexDeatailYarnBean> getHuashisha() {
        return huashisha;
    }

    public void setHuashisha(List<IndexDeatailYarnBean> huashisha) {
        this.huashisha = huashisha;
    }

    public List<IndexDeatailYarnBean> getHuaxiansha() {
        return huaxiansha;
    }

    public void setHuaxiansha(List<IndexDeatailYarnBean> huaxiansha) {
        this.huaxiansha = huaxiansha;
    }

    @Override
    public String toString() {
        return "IndexRecommendYarnBean{" +
                "mafangsha=" + mafangsha +
                ", maofangsha=" + maofangsha +
                ", mianfangsha=" + mianfangsha +
                ", hunfangsha=" + hunfangsha +
                ", huashisha=" + huashisha +
                ", huaxiansha=" + huaxiansha +
                '}';
    }
}
