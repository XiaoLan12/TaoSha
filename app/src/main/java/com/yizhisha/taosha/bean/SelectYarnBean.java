package com.yizhisha.taosha.bean;

/**
 * Created by Administrator on 2017/8/6 0006.
 */

public class SelectYarnBean {
    private String color;
    private int num;
    private boolean isAdd;

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SelectYarnBean{" +
                "color='" + color + '\'' +
                ", num=" + num +
                '}';
    }


}
