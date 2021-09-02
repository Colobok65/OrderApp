package ru.scur.orderapp.converter;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.dto.OrderLineDTO;
import ru.scur.orderapp.model.OrderLine;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineConverter {

    public OrderLineDTO toOrderLineDTO(OrderLine orderLine){
        return new OrderLineDTO(
                orderLine.getId(),
                orderLine.getGoodsOrder().getId(),
                orderLine.getGoods().getId(),
                orderLine.getGoods().getName(),
                orderLine.getCountNumber()
        );
    }

    public List<OrderLineDTO> toOrderLineDTOList(List<OrderLine> list){
        return list.stream().map(this::toOrderLineDTO).collect(Collectors.toList());
    }
}
