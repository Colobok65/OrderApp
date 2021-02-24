package ru.scur.orderapp.service;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.converter.OrderLineConverter;
import ru.scur.orderapp.dao.OrderLineDAO;
import ru.scur.orderapp.dto.OrderLineDTO;
import ru.scur.orderapp.exception.ThereIsNoSuchOrderLineException;
import ru.scur.orderapp.model.OrderLine;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineDAO orderLineDAO;
    private final OrderLineConverter orderLineConverter;

    public OrderLineService(OrderLineDAO orderLineDAO, OrderLineConverter orderLineConverter) {
        this.orderLineDAO = orderLineDAO;
        this.orderLineConverter = orderLineConverter;
    }

    public List<OrderLineDTO> getAllOrderLines() {
        return orderLineConverter.toOrderLineDTOList(orderLineDAO.findAll());
    }

    public OrderLine getOrderLine(Long id) {
        return orderLineDAO.findById(id).orElseThrow(ThereIsNoSuchOrderLineException::new);
    }


    public OrderLineDTO getOrderLineById(Long id) {
        return orderLineConverter.toOrderLineDTO(getOrderLine(id));
    }

    public void deleteOrderLineById(Long id) {
        orderLineDAO.deleteById(id);
    }

    public OrderLineDTO editOrderLine(Long id, OrderLineDTO orderLineDTO) {
        OrderLine orderLine = getOrderLine(id);
        if (orderLine == null) throw new ThereIsNoSuchOrderLineException();
        orderLine.setGoodsOrder(orderLine.getGoodsOrder());
        orderLine.setGoods(orderLine.getGoods());
        orderLine.setCount(orderLine.getCount());
        return orderLineConverter.toOrderLineDTO(orderLineDAO.save(orderLine));
    }
}
