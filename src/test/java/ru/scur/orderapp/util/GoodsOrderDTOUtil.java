package ru.scur.orderapp.util;

import ru.scur.orderapp.dto.GoodsOrderDTO;

import java.time.LocalDateTime;
import java.util.Collections;

public class GoodsOrderDTOUtil {

    public static GoodsOrderDTO getGoodsOrderDTO(){
        return new GoodsOrderDTO(
                1L,
                "username",
                LocalDateTime.now(),
                "address",
                1L,
                Collections.emptyList());
    }
}
