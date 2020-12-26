package ru.scur.orderapp.util;

import org.springframework.stereotype.Component;
import ru.scur.orderapp.dto.GoodsOrderDTO;

import java.util.ArrayList;
import java.util.Date;


public class GoodsOrderDTOUtil {

    public static GoodsOrderDTO getGoodsOrderDTO(){
        return new GoodsOrderDTO(10L, "client", new Date(), "address", new ArrayList<>());
    }
}
