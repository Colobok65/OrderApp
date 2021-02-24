package ru.scur.orderapp.converter;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.dto.GoodsOrderDTO;
import ru.scur.orderapp.model.GoodsOrder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsOrderConverter {
    private final OrderLineConverter orderLineConverter;

    public GoodsOrderConverter(OrderLineConverter orderLineConverter) {
        this.orderLineConverter = orderLineConverter;
    }

    public GoodsOrderDTO toOrderDTO(GoodsOrder goodsOrder){
        return new GoodsOrderDTO(
                goodsOrder.getId(),
                goodsOrder.getClientName(),
                goodsOrder.getDate(),
                goodsOrder.getAddress(),
                orderLineConverter.toOrderLineDTOList(goodsOrder.getOrderLines())
        );
    }

    public List<GoodsOrderDTO> toOrderDTOList(List<GoodsOrder> list){
        return list.stream().map(this::toOrderDTO).collect(Collectors.toList());
    }
}
