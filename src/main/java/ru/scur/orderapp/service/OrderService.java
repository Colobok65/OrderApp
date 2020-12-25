package ru.scur.orderapp.service;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.DAO.OrderDAO;
import ru.scur.orderapp.converter.OrderConverter;
import ru.scur.orderapp.dto.OrderDTO;
import ru.scur.orderapp.exception.ThereIsNoSuchOrderException;
import ru.scur.orderapp.model.GoodsOrder;

import java.util.List;

@Service
public class OrderService {

    private final OrderDAO orderDAO;
    private final OrderConverter orderConverter;


    public OrderService(OrderDAO orderDAO, OrderConverter orderConverter) {
        this.orderDAO = orderDAO;
        this.orderConverter = orderConverter;
    }

    public OrderDTO createOrder(OrderDTO orderDTO){
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setClient(orderDTO.getClient());
        goodsOrder.setDate(orderDTO.getDate());
        goodsOrder.setAddress(orderDTO.getAddress());
        return orderConverter.toOrderDTO(orderDAO.save(goodsOrder));
    }

    public GoodsOrder getOrder(Long id){
        return orderDAO.findById(id).orElseThrow(ThereIsNoSuchOrderException::new);
    }

    public OrderDTO getOrderById(Long id){
        return orderConverter.toOrderDTO(getOrder(id));
    }

    public OrderDTO editOrder(Long id, OrderDTO orderDTO){
        GoodsOrder goodsOrder = getOrder(id);
        if (goodsOrder == null) throw new ThereIsNoSuchOrderException();
        goodsOrder.setClient(orderDTO.getClient());
        goodsOrder.setDate(orderDTO.getDate());
        goodsOrder.setAddress(orderDTO.getAddress());
        return orderConverter.toOrderDTO(orderDAO.save(goodsOrder));
    }

    public void deleteOrderById(Long id) {
        GoodsOrder goodsOrder = getOrder(id);
        if (goodsOrder == null) throw new ThereIsNoSuchOrderException();
        orderDAO.deleteById(id);
    }

    public List<OrderDTO> getAllOrders() {
        return orderConverter.toOrderDTOList(orderDAO.findAll());
    }
}
