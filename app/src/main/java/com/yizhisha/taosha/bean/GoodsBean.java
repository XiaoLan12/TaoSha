package com.yizhisha.taosha.bean;

/**
 * Created by louisgeek on 2016/4/27.
 */
public class GoodsBean {

    public static final  int STATUS_INVALID=0;
    public static final  int STATUS_VALID=1;
    //===============================================
    private String gid;

    private String title;

    private String pname;

    private float price;

    private String litpic;

    private int amount;

    private String detail;

    private String addtime;

    /** 状态 */
    private int status;
    /** 是否被选中 */
    private boolean isChecked;
    /** 是否是编辑状态 */
    private boolean isEditing;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
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



    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }



    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
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

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
