package ru.scur.orderapp.service;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.converter.GoodsOrderConverter;
import ru.scur.orderapp.dto.GoodsOrderDTO;
import ru.scur.orderapp.exception.ThereIsNoSuchGoodsOrderException;
import ru.scur.orderapp.model.GoodsOrder;
import ru.scur.orderapp.repository.GoodsOrderRepository;

import java.util.List;

@Service
public class GoodsOrderService {

    private final GoodsOrderRepository goodsOrderRepository;
    private final GoodsOrderConverter goodsOrderConverter;


    public GoodsOrderService(GoodsOrderRepository goodsOrderRepository, GoodsOrderConverter goodsOrderConverter) {
        this.goodsOrderRepository = goodsOrderRepository;
        this.goodsOrderConverter = goodsOrderConverter;
    }

    public GoodsOrderDTO createGoodsOrder(GoodsOrderDTO goodsOrderDTO){
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setClient(goodsOrderDTO.getClient());
        goodsOrder.setAddress(goodsOrderDTO.getAddress());
        return goodsOrderConverter.toOrderDTO(goodsOrderRepository.save(goodsOrder));
    }

    public GoodsOrder getGoodsOrder(Long id){
        return goodsOrderRepository.findById(id).orElseThrow(ThereIsNoSuchGoodsOrderException::new);
    }

    public GoodsOrderDTO getGoodsOrderById(Long id){
        return goodsOrderConverter.toOrderDTO(getGoodsOrder(id));
    }

    public GoodsOrderDTO editGoodsOrder(Long id, GoodsOrderDTO goodsOrderDTO){
        GoodsOrder goodsOrder = getGoodsOrder(id);
        if (goodsOrder == null) throw new ThereIsNoSuchGoodsOrderException();
        goodsOrder.setClient(goodsOrderDTO.getClient());
        goodsOrder.setAddress(goodsOrderDTO.getAddress());
        return goodsOrderConverter.toOrderDTO(goodsOrderRepository.save(goodsOrder));
    }

    public void deleteGoodsOrderById(Long id) {
        goodsOrderRepository.deleteById(id);
    }

    public List<GoodsOrderDTO> getAllGoodsOrders() {
        return goodsOrderConverter.toOrderDTOList(goodsOrderRepository.findAll());
    }
}
