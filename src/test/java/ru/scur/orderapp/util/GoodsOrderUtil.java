package ru.scur.orderapp.util;

import org.springframework.stereotype.Component;
import ru.scur.orderapp.model.GoodsOrder;

import java.util.ArrayList;
import java.util.Date;

public class GoodsOrderUtil {

    public static GoodsOrder getGoodsOrder(){
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setId(10L);
        goodsOrder.setClient("client");
        goodsOrder.setDate(new Date());
        goodsOrder.setAddress("address");
        goodsOrder.setOrderLines(new ArrayList<>());
        return goodsOrder;
    }
}
