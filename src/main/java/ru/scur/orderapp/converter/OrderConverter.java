package ru.scur.orderapp.converter;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.dto.OrderDTO;
import ru.scur.orderapp.model.GoodsOrder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderConverter {

    public OrderDTO toOrderDTO(GoodsOrder goodsOrder){
        return new OrderDTO(
                goodsOrder.getId(),
                goodsOrder.getClient(),
                goodsOrder.getDate(),
                goodsOrder.getAddress()
        );
    }

    public List<OrderDTO> toOrderDTOList(List<GoodsOrder> list){
        return list.stream().map(this::toOrderDTO).collect(Collectors.toList());
    }
}
