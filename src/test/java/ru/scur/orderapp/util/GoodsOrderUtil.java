package ru.scur.orderapp.util;

import ru.scur.orderapp.model.GoodsOrder;

import java.time.LocalDateTime;
import java.util.Collections;

public class GoodsOrderUtil {

    public static GoodsOrder getGoodsOrder(){
        return new GoodsOrder(
                1L,
                LocalDateTime.now(),
                Collections.emptyList(),
                UserUtil.getUser());
    }
}
