package ru.scur.orderapp.service;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.converter.OrderLineConverter;
import ru.scur.orderapp.repository.GoodsRepository;
import ru.scur.orderapp.repository.GoodsOrderRepository;
import ru.scur.orderapp.dto.OrderLineDTO;
import ru.scur.orderapp.exception.ThereIsNoSuchGoodsException;
import ru.scur.orderapp.exception.ThereIsNoSuchGoodsOrderException;
import ru.scur.orderapp.exception.ThereIsNoSuchOrderLineException;
import ru.scur.orderapp.model.OrderLine;
import ru.scur.orderapp.repository.OrderLineRepository;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineConverter orderLineConverter;
    private final GoodsOrderRepository goodsOrderRepository;
    private final GoodsRepository goodsRepository;

    public OrderLineService(OrderLineRepository orderLineRepository, OrderLineConverter orderLineConverter, GoodsOrderRepository goodsOrderRepository, GoodsRepository goodsRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderLineConverter = orderLineConverter;
        this.goodsOrderRepository = goodsOrderRepository;
        this.goodsRepository = goodsRepository;
    }

    public OrderLineDTO createOrderLine(OrderLineDTO orderLineDTO){
        OrderLine orderLine = new OrderLine();
        orderLine.setGoodsOrder(goodsOrderRepository.getOne(orderLineDTO.getOrderId()));
        orderLine.setGoods(goodsRepository.getOne(orderLineDTO.getGoodsId()));
        orderLine.setCountNumber(orderLineDTO.getCountNumber());
        return orderLineConverter.toOrderLineDTO(orderLineRepository.save(orderLine));
    }

    public List<OrderLineDTO> getAllOrderLines() {
        return orderLineConverter.toOrderLineDTOList(orderLineRepository.findAll());
    }

    public OrderLine getOrderLine(Long id) {
        return orderLineRepository.findById(id).orElseThrow(ThereIsNoSuchOrderLineException::new);
    }

    public OrderLineDTO getOrderLineById(Long id) {
        return orderLineConverter.toOrderLineDTO(getOrderLine(id));
    }

    public void deleteOrderLineById(Long id) {
        orderLineRepository.deleteById(id);
    }

    public OrderLineDTO editOrderLine(Long id, OrderLineDTO orderLineDTO) {
        OrderLine orderLine = getOrderLine(id);
        if (orderLine == null) throw new ThereIsNoSuchOrderLineException();
        orderLine.setGoodsOrder(goodsOrderRepository.findById(orderLineDTO.getOrderId()).orElseThrow(ThereIsNoSuchGoodsOrderException::new));
        orderLine.setGoods(goodsRepository.findById(orderLineDTO.getGoodsId()).orElseThrow(ThereIsNoSuchGoodsException::new));
        orderLine.setCountNumber(orderLineDTO.getCountNumber());
        return orderLineConverter.toOrderLineDTO(orderLineRepository.save(orderLine));
    }

    public List<OrderLineDTO> getLineByOrderId(Long orderId) {
        return orderLineConverter.toOrderLineDTOList(orderLineRepository.findOrderLineByGoodsOrderId(orderId));
    }

    public List<OrderLineDTO> getLineByGoodsId(Long goodsId) {
        return orderLineConverter.toOrderLineDTOList(orderLineRepository.findOrderLineByGoods_Id(goodsId));
    }
}
