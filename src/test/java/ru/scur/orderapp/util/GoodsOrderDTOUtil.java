package ru.scur.orderapp.util;

import ru.scur.orderapp.dto.GoodsOrderDTO;

import java.util.Collections;


public class GoodsOrderDTOUtil {

    public static GoodsOrderDTO getGoodsOrderDTO(){
        return new GoodsOrderDTO(1L, "client", null, null, Collections.emptyList());
    }
}
