package com.yizhisha.taosha.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lan on 2017/7/4.
 */

public class AddressListBean implements  Serializable{
    private String status;

    private String info;
    private List<Address> address ;

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public class Address implements Serializable {
        private int id;

        private int uid;

        private String linkman;

        private String mobile;

        private String address;

        private String area_app;

        private String index;

        private String area_pid;

        private String area_sid;

        private String area_pname;

        private String area_sname;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getLinkman() {
            return this.linkman;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return this.address;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getIndex() {
            return this.index;
        }

        public String getArea_app() {
            return area_app;
        }

        public void setArea_app(String area_app) {
            this.area_app = area_app;
        }

        public String getArea_sid() {
            return area_sid;
        }

        public void setArea_sid(String area_sid) {
            this.area_sid = area_sid;
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
    }
    @Override
    public String toString() {
        return "AddressListBean{" +
                "address=" + address +
                '}';
    }
}
