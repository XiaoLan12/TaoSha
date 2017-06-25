package com.yizhisha.taosha.bean;

/**
 * Created by Administrator on 2017/6/25.
 */

public class HomeYarnTypeEntity {
    private int img;
    private String path;
    private String name;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HomeYarnTypeEntity{" +
                "img=" + img +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
