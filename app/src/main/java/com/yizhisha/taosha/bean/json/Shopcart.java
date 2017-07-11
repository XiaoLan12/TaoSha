package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/7/10.
 */

public class Shopcart {
    private String id;

    private String uid;

    private int mzw_uid;

    private int gid;

    private String title;

    private String pname;

    private float price;

    private String litpic;

    private int amount;

    private String detail;

    private int addtime;

    private String company;

    private float totalprice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getMzw_uid() {
        return mzw_uid;
    }

    public void setMzw_uid(int mzw_uid) {
        this.mzw_uid = mzw_uid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getAddtime() {
        return addtime;
    }

    public void setAddtime(int addtime) {
        this.addtime = addtime;
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

    @Override
    public String toString() {
        return "Shopcart{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", mzw_uid='" + mzw_uid + '\'' +
                ", gid='" + gid + '\'' +
                ", title='" + title + '\'' +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", litpic='" + litpic + '\'' +
                ", amount=" + amount +
                ", detail='" + detail + '\'' +
                ", addtime='" + addtime + '\'' +
                ", company='" + company + '\'' +
                ", totalprice=" + totalprice +
                '}';
    }
}
