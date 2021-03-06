package com.yizhisha.taosha.bean.json;

/**
 * Created by lan on 2017/7/12.
 */

public class SeckillActBean {
    private int id;

    private String title;

    private float price;

    private float market_price;

    private long starttime;

    private long endtime;

    private String litpic;

    private String company;
    private long nowtime;
    private String counttime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getMarket_price() {
        return market_price;
    }

    public void setMarket_price(float market_price) {
        this.market_price = market_price;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public long getNowtime() {
        return nowtime;
    }

    public void setNowtime(long nowtime) {
        this.nowtime = nowtime;
    }

    public String getCounttime() {
        return counttime;
    }

    public void setCounttime(String counttime) {
        this.counttime = counttime;
    }

    @Override
    public String toString() {
        return "SeckillActBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", market_price=" + market_price +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", litpic='" + litpic + '\'' +
                ", company='" + company + '\'' +
                ", nowtime=" + nowtime +
                ", counttime='" + counttime + '\'' +
                '}';
    }
}
