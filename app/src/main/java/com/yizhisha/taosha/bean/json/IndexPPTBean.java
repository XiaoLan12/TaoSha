package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class IndexPPTBean {

    private List<IndexImgBean> ppt;

    private List<IndexADSBean> ads;

    private List<IndexImgBean> meiri;
    private List<IndexImgBean> nayang;
    private List<IndexImgBean> banmao;

    private List<IndexImgBean> mafang;
    private List<IndexImgBean> maofang;
    private List<IndexImgBean> mianfang;
    private List<IndexImgBean> hunfang;
    private List<IndexImgBean> hauxian;
    private List<IndexImgBean> huashi;
    private List<IndexImgBean> remen;

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

    public List<IndexImgBean> getMafang() {
        return mafang;
    }

    public void setMafang(List<IndexImgBean> mafang) {
        this.mafang = mafang;
    }

    public List<IndexImgBean> getMaofang() {
        return maofang;
    }

    public void setMaofang(List<IndexImgBean> maofang) {
        this.maofang = maofang;
    }

    public List<IndexImgBean> getMianfang() {
        return mianfang;
    }

    public void setMianfang(List<IndexImgBean> mianfang) {
        this.mianfang = mianfang;
    }

    public List<IndexImgBean> getHunfang() {
        return hunfang;
    }

    public void setHunfang(List<IndexImgBean> hunfang) {
        this.hunfang = hunfang;
    }

    public List<IndexImgBean> getHauxian() {
        return hauxian;
    }

    public void setHauxian(List<IndexImgBean> hauxian) {
        this.hauxian = hauxian;
    }

    public List<IndexImgBean> getHuashi() {
        return huashi;
    }

    public void setHuashi(List<IndexImgBean> huashi) {
        this.huashi = huashi;
    }

    public List<IndexImgBean> getRemen() {
        return remen;
    }

    public void setRemen(List<IndexImgBean> remen) {
        this.remen = remen;
    }

    public List<IndexImgBean> getMeiri() {
        return meiri;
    }

    public void setMeiri(List<IndexImgBean> meiri) {
        this.meiri = meiri;
    }

    public List<IndexImgBean> getNayang() {
        return nayang;
    }

    public void setNayang(List<IndexImgBean> nayang) {
        this.nayang = nayang;
    }

    public List<IndexImgBean> getBanmao() {
        return banmao;
    }

    public void setBanmao(List<IndexImgBean> banmao) {
        this.banmao = banmao;
    }

    @Override
    public String toString() {
        return "IndexPPTBean{" +
                "ppt=" + ppt +
                ", ads=" + ads +
                ", meiri=" + meiri +
                ", nayang=" + nayang +
                ", banmao=" + banmao +
                ", mafang=" + mafang +
                ", maofang=" + maofang +
                ", mianfang=" + mianfang +
                ", hunfang=" + hunfang +
                ", hauxian=" + hauxian +
                ", huashi=" + huashi +
                ", remen=" + remen +
                '}';
    }
}
