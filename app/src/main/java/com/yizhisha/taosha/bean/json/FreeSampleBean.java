package com.yizhisha.taosha.bean.json;

import java.util.List;

/**
 * Created by lan on 2017/7/5.
 */

public class FreeSampleBean {

    private List<Active> active ;
    private String status;

    private String info;
    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public void setActive(List<Active> active){
        this.active = active;
    }
    public List<Active> getActive(){
        return this.active;
    }
    public class Active {
        private int id;

        private int uid;

        private int gid;

        private String title;

        private String pname;

        private String litpic;

        private int mzw_uid;

        private int is_ship;

        private int address_id;

        private String area_pname;

        private String area_sname;

        private String address;

        private String area_app;

        private String company;

        private String linkman;

        private String mobile;

        private String addtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public int getMzw_uid() {
            return mzw_uid;
        }

        public void setMzw_uid(int mzw_uid) {
            this.mzw_uid = mzw_uid;
        }

        public int getIs_ship() {
            return is_ship;
        }

        public void setIs_ship(int is_ship) {
            this.is_ship = is_ship;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getArea_pname() {
            return area_pname;
        }

        public void setArea_pname(String area_pname) {
            this.area_pname = area_pname;
        }

        public String getArea_sname() {
            return area_sname;
        }

        public void setArea_sname(String area_sname) {
            this.area_sname = area_sname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea_app() {
            return area_app;
        }

        public void setArea_app(String area_app) {
            this.area_app = area_app;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
