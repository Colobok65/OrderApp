package ru.scur.orderapp.service;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.converter.OrderLineConverter;
import ru.scur.orderapp.dao.GoodsDAO;
import ru.scur.orderapp.dao.GoodsOrderDAO;
import ru.scur.orderapp.dao.OrderLineDAO;
import ru.scur.orderapp.dto.OrderLineDTO;
import ru.scur.orderapp.exception.ThereIsNoSuchOrderLineException;
import ru.scur.orderapp.model.OrderLine;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineDAO orderLineDAO;
    private final OrderLineConverter orderLineConverter;
    private final GoodsOrderDAO goodsOrderDAO;
    private final GoodsDAO goodsDAO;

    public OrderLineService(OrderLineDAO orderLineDAO, OrderLineConverter orderLineConverter, GoodsOrderDAO goodsOrderDAO, GoodsDAO goodsDAO) {
        this.orderLineDAO = orderLineDAO;
        this.orderLineConverter = orderLineConverter;
        this.goodsOrderDAO = goodsOrderDAO;
        this.goodsDAO = goodsDAO;
    }

    public OrderLineDTO createOrderLine(OrderLineDTO orderLineDTO){
        OrderLine orderLine = new OrderLine();
        orderLine.setGoodsOrder(goodsOrderDAO.getOne(orderLineDTO.getOrderId()));
        orderLine.setGoods(goodsDAO.getOne(orderLineDTO.getGoodsId()));
        orderLine.setCountNumber(orderLineDTO.getCountNumber());
        return orderLineConverter.toOrderLineDTO(orderLineDAO.save(orderLine));
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
        orderLine.setCountNumber(orderLine.getCountNumber());
        return orderLineConverter.toOrderLineDTO(orderLineDAO.save(orderLine));
    }

    public List<OrderLineDTO> getLineByOrderId(Long orderId) {
        return orderLineConverter.toOrderLineDTOList(orderLineDAO.findOrderLineByGoodsOrderId(orderId));
    }

    public Float findGoodsPriceByGoodsId(Long goodsId) {
        return orderLineDAO.findGoodsPrice(goodsId);
    }
}
