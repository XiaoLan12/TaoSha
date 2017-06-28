package com.yizhisha.taosha.bean;

/**
 * Created by louisgeek on 2016/4/27.
 */
public class GoodsBean {

    public static final  int STATUS_INVALID=0;
    public static final  int STATUS_VALID=1;
    //===============================================
    private String id;
    private String name;
    /** 商品宣传图片 */
    private String imageLogo;
    /** 原价，市场价 */
    private double price;

    private int count;
    /** 状态 */
    private int status;



    /** 是否被选中 */
    private boolean isChecked;
    /** 是否是编辑状态 */
    private boolean isEditing;



    public GoodsBean(String id,String name,String imageLogo,double price, int count, int status, boolean isChecked, boolean isEditing) {
        this.id = id;
        this.name = name;
        this.imageLogo = imageLogo;
        this.price = price;
        this.count = count;
        this.status = status;
        this.isChecked = isChecked;
        this.isEditing = isEditing;
    }

    public GoodsBean(){

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }
}
