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

    public GoodsOrderDTO createGoodsOrder(GoodsOrderDTO clientName){
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setClientName(clientName.getClientName());
        goodsOrder.setDate(clientName.getDate());
        goodsOrder.setAddress(clientName.getAddress());
        return goodsOrderConverter.toOrderDTO(goodsOrderDAO.save(goodsOrder));
    }

    public GoodsOrder getGoodsOrder(Long id){
        return goodsOrderDAO.findById(id).orElseThrow(ThereIsNoSuchGoodsOrderException::new);
    }

    public GoodsOrderDTO getGoodsOrderById(Long id){
        return goodsOrderConverter.toOrderDTO(getGoodsOrder(id));
    }

    public GoodsOrderDTO editGoodsOrder(Long id, GoodsOrderDTO goodsOrderDTO){
        GoodsOrder goodsOrder = getGoodsOrder(id);
        if (goodsOrder == null) throw new ThereIsNoSuchGoodsOrderException();
        goodsOrder.setClientName(goodsOrderDTO.getClientName());
        goodsOrder.setDate(goodsOrderDTO.getDate());
        goodsOrder.setAddress(goodsOrderDTO.getAddress());
        return goodsOrderConverter.toOrderDTO(goodsOrderDAO.save(goodsOrder));
    }

    public void deleteGoodsOrderById(Long id) {
        goodsOrderDAO.deleteById(id);
    }

    public List<GoodsOrderDTO> getAllGoodsOrders() {
        return goodsOrderConverter.toOrderDTOList(goodsOrderDAO.findAll());
    }
}
