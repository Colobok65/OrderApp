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
                goodsOrder.getUser().getUsername(),
                goodsOrder.getDate(),
                goodsOrder.getUser().getAddress(),
                goodsOrder.getUser().getId(),
                orderLineConverter.toOrderLineDTOList(goodsOrder.getOrderLines())
        );
    }

    public List<GoodsOrderDTO> toOrderDTOList(List<GoodsOrder> list){
        return list.stream().map(this::toOrderDTO).collect(Collectors.toList());
    }
}
