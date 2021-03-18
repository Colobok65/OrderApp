package ru.scur.orderapp.service;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.dao.GoodsDAO;
import ru.scur.orderapp.converter.GoodsConverter;
import ru.scur.orderapp.dto.GoodsDTO;
import ru.scur.orderapp.exception.ThereIsNoSuchGoodsException;
import ru.scur.orderapp.model.Goods;

import java.util.List;

@Service
public class GoodsService {

    private final GoodsDAO goodsDAO;
    private final GoodsConverter goodsConverter;

    public GoodsService(GoodsDAO goodsDAO, GoodsConverter goodsConverter) {
        this.goodsDAO = goodsDAO;
        this.goodsConverter = goodsConverter;
    }


    public GoodsDTO createGoods(GoodsDTO goodsDTO){
        Goods goods = new Goods();
        goods.setName(goodsDTO.getName());
        goods.setPrice(goodsDTO.getPrice());
        return goodsConverter.toGoodsDTO(goodsDAO.save(goods));
    }

    public Goods getGoods(Long id){
        return goodsDAO.findById(id).orElseThrow(ThereIsNoSuchGoodsException::new);
    }

    public GoodsDTO getGoodsById(Long id){
        return goodsConverter.toGoodsDTO(getGoods(id));
    }

    public GoodsDTO editGoods(Long id, GoodsDTO goodsDTO){
        Goods goods = getGoods(id);
        if (goods == null) throw new ThereIsNoSuchGoodsException();
        goods.setId(goodsDTO.getId());
        goods.setName(goodsDTO.getName());
        goods.setPrice(goodsDTO.getPrice());
        return goodsConverter.toGoodsDTO(goodsDAO.save(goods));
    }

    public void deleteGoodsById(Long id) {
        Goods goods = getGoods(id);
        if (goods == null) throw new ThereIsNoSuchGoodsException();
        goodsDAO.deleteById(id);
    }

    public List<GoodsDTO> getAllGoods() {
        return goodsConverter.toGoodsDTOList(goodsDAO.findAll());
    }
}
