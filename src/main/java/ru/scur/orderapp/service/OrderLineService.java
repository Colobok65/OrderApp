package ru.scur.orderapp.service;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.converter.OrderLineConverter;
import ru.scur.orderapp.dao.GoodsDAO;
import ru.scur.orderapp.dao.GoodsOrderDAO;
import ru.scur.orderapp.dao.OrderLineDAO;
import ru.scur.orderapp.dto.OrderLineDTO;
import ru.scur.orderapp.exception.ThereIsNoSuchGoodsException;
import ru.scur.orderapp.exception.ThereIsNoSuchGoodsOrderException;
import ru.scur.orderapp.exception.ThereIsNoSuchOrderLineException;
import ru.scur.orderapp.exception.ThisProductIsAlreadyInTheOrderLineException;
import ru.scur.orderapp.model.OrderLine;

import java.util.List;
import java.util.Optional;

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
        if(ifGoodsContainsInThisLine(orderLineDTO.getOrderId(), orderLineDTO.getGoodsId())){
            throw new ThisProductIsAlreadyInTheOrderLineException("This product is already in the products list, you can change quantity of goods");
        }
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
        orderLine.setGoodsOrder(goodsOrderDAO.findById(orderLineDTO.getOrderId()).orElseThrow(ThereIsNoSuchGoodsOrderException::new));
        orderLine.setGoods(goodsDAO.findById(orderLineDTO.getGoodsId()).orElseThrow(ThereIsNoSuchGoodsException::new));
        orderLine.setCountNumber(orderLineDTO.getCountNumber());
        return orderLineConverter.toOrderLineDTO(orderLineDAO.save(orderLine));
    }

    public List<OrderLineDTO> getLineByOrderId(Long orderId) {
        return orderLineConverter.toOrderLineDTOList(orderLineDAO.findOrderLineByGoodsOrderId(orderId));
    }

    public boolean ifGoodsContainsInThisLine(Long orderId, Long goodsId) {
        Optional<OrderLine> line = orderLineDAO.findOrderLineByGoodsOrderId(orderId)
                .stream()
                .filter(a -> a.getGoods().getId().equals(goodsId))
                .findAny();

        return line.isPresent();
    }
}
