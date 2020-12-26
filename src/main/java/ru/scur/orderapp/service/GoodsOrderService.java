package ru.scur.orderapp.service;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.dao.GoodsOrderDAO;
import ru.scur.orderapp.converter.GoodsOrderConverter;
import ru.scur.orderapp.dto.GoodsOrderDTO;
import ru.scur.orderapp.exception.ThereIsNoSuchGoodsOrderException;
import ru.scur.orderapp.model.GoodsOrder;

import java.util.List;

@Service
public class GoodsOrderService {

    private final GoodsOrderDAO goodsOrderDAO;
    private final GoodsOrderConverter goodsOrderConverter;


    public GoodsOrderService(GoodsOrderDAO goodsOrderDAO, GoodsOrderConverter goodsOrderConverter) {
        this.goodsOrderDAO = goodsOrderDAO;
        this.goodsOrderConverter = goodsOrderConverter;
    }

    public GoodsOrderDTO createOrder(GoodsOrderDTO goodsOrderDTO){
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setClient(goodsOrderDTO.getClient());
        goodsOrder.setDate(goodsOrderDTO.getDate());
        goodsOrder.setAddress(goodsOrderDTO.getAddress());
        return goodsOrderConverter.toOrderDTO(goodsOrderDAO.save(goodsOrder));
    }

    public GoodsOrder getOrder(Long id){
        return goodsOrderDAO.findById(id).orElseThrow(ThereIsNoSuchGoodsOrderException::new);
    }

    public GoodsOrderDTO getOrderById(Long id){
        return goodsOrderConverter.toOrderDTO(getOrder(id));
    }

    public GoodsOrderDTO editOrder(Long id, GoodsOrderDTO goodsOrderDTO){
        GoodsOrder goodsOrder = getOrder(id);
        if (goodsOrder == null) throw new ThereIsNoSuchGoodsOrderException();
        goodsOrder.setClient(goodsOrderDTO.getClient());
        goodsOrder.setDate(goodsOrderDTO.getDate());
        goodsOrder.setAddress(goodsOrderDTO.getAddress());
        return goodsOrderConverter.toOrderDTO(goodsOrderDAO.save(goodsOrder));
    }

    public void deleteOrderById(Long id) {
        GoodsOrder goodsOrder = getOrder(id);
        if (goodsOrder == null) throw new ThereIsNoSuchGoodsOrderException();
        goodsOrderDAO.deleteById(id);
    }

    public List<GoodsOrderDTO> getAllOrders() {
        return goodsOrderConverter.toOrderDTOList(goodsOrderDAO.findAll());
    }
}
