package com.yizhisha.taosha.bean.json;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lan on 2017/7/4.
 */

public class AddressListBean implements  Serializable{
    private List<Address> address ;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public class Address implements Serializable{
        private int id;

        private int uid;

        private String linkman;

        private String mobile;

        private String address;

        private String index;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setUid(int uid){
            this.uid = uid;
        }
        public int getUid(){
            return this.uid;
        }
        public void setLinkman(String linkman){
            this.linkman = linkman;
        }
        public String getLinkman(){
            return this.linkman;
        }
        public void setMobile(String mobile){
            this.mobile = mobile;
        }
        public String getMobile(){
            return this.mobile;
        }
        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return this.address;
        }
        public void setIndex(String index){
            this.index = index;
        }
        public String getIndex(){
            return this.index;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", linkman='" + linkman + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", address='" + address + '\'' +
                    ", index='" + index + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AddressListBean{" +
                "address=" + address +
                '}';
    }
}
