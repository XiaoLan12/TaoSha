package com.yizhisha.taosha.bean;

/**
 * Created by louisgeek on 2016/4/27.
 */
public class StoreBean {
    /** 店铺ID */
    private int mzw_uid;
    /** 店铺名称 */
    private String company;
    /**总价*/
    private float totalprice;

    private boolean isChecked;

    private int isEditing;

    public int getMzw_uid() {
        return mzw_uid;
    }

    public void setMzw_uid(int mzw_uid) {
        this.mzw_uid = mzw_uid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int isEditing() {
        return isEditing;
    }

    public void setEditing(int editing) {
        isEditing = editing;
    }

    @Override
    public String toString() {
        return "StoreBean{" +
                "mzw_uid=" + mzw_uid +
                ", company='" + company + '\'' +
                ", totalprice=" + totalprice +
                ", isChecked=" + isChecked +
                ", isEditing=" + isEditing +
                '}';
    }
}
