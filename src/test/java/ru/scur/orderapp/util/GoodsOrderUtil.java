package ru.scur.orderapp.util;

import ru.scur.orderapp.model.GoodsOrder;

import java.util.Collections;

public class GoodsOrderUtil {

    public static GoodsOrder getGoodsOrder(){
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setId(1L);
        goodsOrder.setClient("client");
        goodsOrder.setDate(null);
        goodsOrder.setAddress(null);
        goodsOrder.setOrderLines(Collections.emptyList());
        return goodsOrder;
    }
}
