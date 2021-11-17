package ru.scur.orderapp.util;

import ru.scur.orderapp.dto.GoodsOrderDTO;

import java.util.ArrayList;
import java.util.List;

public class GoodsOrderDTOListUtil {
    public static List<GoodsOrderDTO> getGoodsOrderDtoList(){
        List<GoodsOrderDTO> goodsOrderDTOList = new ArrayList<>();
        goodsOrderDTOList.add(GoodsOrderDTOUtil.getGoodsOrderDTO());
        return goodsOrderDTOList;
    }
}
