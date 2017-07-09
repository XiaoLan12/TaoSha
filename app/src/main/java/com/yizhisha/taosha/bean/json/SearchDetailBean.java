package com.yizhisha.taosha.bean.json;

/**
 * Created by Administrator on 2017/7/9.
 */

public class SearchDetailBean {
    private String id;
    private String title;
    private String litpic;
    private String price;
    private String price_real;
    private String ingredient;
    private String needle_name;
    private String yam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_real() {
        return price_real;
    }

    public void setPrice_real(String price_real) {
        this.price_real = price_real;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getNeedle_name() {
        return needle_name;
    }

    public void setNeedle_name(String needle_name) {
        this.needle_name = needle_name;
    }

    public String getYam() {
        return yam;
    }

    public void setYam(String yam) {
        this.yam = yam;
    }

    @Override
    public String toString() {
        return "SearchDetailBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", litpic='" + litpic + '\'' +
                ", price='" + price + '\'' +
                ", price_real='" + price_real + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", needle_name='" + needle_name + '\'' +
                ", yam='" + yam + '\'' +
                '}';
    }
}
