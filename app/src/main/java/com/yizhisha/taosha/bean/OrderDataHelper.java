package com.yizhisha.taosha.bean;

import com.yizhisha.taosha.bean.json.Goods;
import com.yizhisha.taosha.bean.json.Order;
import com.yizhisha.taosha.bean.json.OrderFootBean;
import com.yizhisha.taosha.bean.json.OrderHeadBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/11/8.
 */

public class OrderDataHelper {

    /**
     * List<Object>有三种数据类型：
     * 1、GoodsOrderInfo 表示每个小订单的头部信息（订单号、订单状态、店铺名称）
     * 2、OrderGoodsItem 表示小订单中的商品
     * 3、OrderPayInfo 表示大订单的支付信息（金额、订单状态）
     * @param resultList
     * @return
     */
    public static ArrayList<Object> getDataAfterHandle(List<Order> resultList) {
        ArrayList<Object> dataList = new ArrayList<Object>();

        //遍历每一张大订单
        for (Order order : resultList) {
            //大订单支付的金额核定单状态
            OrderHeadBean orderHeadBean = new OrderHeadBean();
            orderHeadBean.setStatus(order.getStatus());
            orderHeadBean.setCompany(order.getCompany());
            dataList.add(orderHeadBean);


            List<Goods> goodses=order.getGoods();
            //遍历每个大订单里面的小订单
            for (Goods orderGoodsItem : goodses) {
                orderGoodsItem.setOrderno(order.getOrderno());
                dataList.add(orderGoodsItem);
            }
            OrderFootBean orderFootBean=new OrderFootBean();
            orderFootBean.setTotalprice(order.getTotalprice());
            orderFootBean.setStatus(order.getStatus());
            orderFootBean.setAmount(goodses.size());
            orderFootBean.setOrderno(order.getOrderno());
            dataList.add(orderFootBean);
        }
        return dataList;
    }

}
