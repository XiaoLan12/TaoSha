package com.yizhisha.taosha.bean.json;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yizhisha.taosha.adapter.MyOrderAdapter;

import java.util.List;

/**
 * Created by lan on 2017/7/6.
 */

public class Order implements MultiItemEntity {

        private int id;

        private int mzw_uid;

        private String orderno;

        private int status;

        private int payment;

        private float totalprice;

        private float goods_price;

        private int express_type;

        private float express_price;

        private float express_daofu;

        private float paystatus;

        private float payorderno;

        private String linkman;

        private String mobile;

        private String address;

        private float uid;

        private String addtime;

        private int paytime;

        private String shiptime;

        private int loanstatus;

        private float deposit_amount;

        private String company;

        private List<Goods> goods;

        private String mobile_company;
        private int commentstatus;

    public int getCommentstatus() {
        return commentstatus;
    }

    public void setCommentstatus(int commentstatus) {
        this.commentstatus = commentstatus;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public float getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(float totalprice) {
            this.totalprice = totalprice;
        }

        public float getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(float goods_price) {
            this.goods_price = goods_price;
        }

        public int getExpress_type() {
            return express_type;
        }

        public void setExpress_type(int express_type) {
            this.express_type = express_type;
        }

        public float getExpress_price() {
            return express_price;
        }

        public void setExpress_price(float express_price) {
            this.express_price = express_price;
        }

        public float getExpress_daofu() {
            return express_daofu;
        }

        public void setExpress_daofu(float express_daofu) {
            this.express_daofu = express_daofu;
        }

        public float getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(float paystatus) {
            this.paystatus = paystatus;
        }

        public float getPayorderno() {
            return payorderno;
        }

        public void setPayorderno(float payorderno) {
            this.payorderno = payorderno;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public float getUid() {
            return uid;
        }

        public void setUid(float uid) {
            this.uid = uid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getPaytime() {
            return paytime;
        }

        public void setPaytime(int paytime) {
            this.paytime = paytime;
        }

        public String getShiptime() {
            return shiptime;
        }

        public void setShiptime(String shiptime) {
            this.shiptime = shiptime;
        }

        public int getLoanstatus() {
            return loanstatus;
        }

        public void setLoanstatus(int loanstatus) {
            this.loanstatus = loanstatus;
        }

        public float getDeposit_amount() {
            return deposit_amount;
        }

        public void setDeposit_amount(float deposit_amount) {
            this.deposit_amount = deposit_amount;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public List<Goods> getGoods() {
            return goods;
        }

        public void setGoods(List<Goods> goods) {
            this.goods = goods;
        }

        public String getMobile_company() {
            return mobile_company;
        }

        public void setMobile_company(String mobile_company) {
            this.mobile_company = mobile_company;
        }

    public int getMzw_uid() {
        return mzw_uid;
    }

    public void setMzw_uid(int mzw_uid) {
        this.mzw_uid = mzw_uid;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
