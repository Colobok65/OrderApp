package ru.scur.orderapp.service;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.converter.GoodsConverter;
import ru.scur.orderapp.dto.GoodsDTO;
import ru.scur.orderapp.exception.ThereIsNoSuchGoodsException;
import ru.scur.orderapp.model.Goods;
import ru.scur.orderapp.repository.GoodsRepository;

import java.util.List;

@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final GoodsConverter goodsConverter;

    public GoodsService(GoodsRepository goodsRepository, GoodsConverter goodsConverter) {
        this.goodsRepository = goodsRepository;
        this.goodsConverter = goodsConverter;
    }


    public GoodsDTO createGoods(GoodsDTO goodsDTO){
        Goods goods = new Goods();
        goods.setName(goodsDTO.getName());
        goods.setPrice(goodsDTO.getPrice());
        return goodsConverter.toGoodsDTO(goodsRepository.save(goods));
    }

    public Goods getGoods(Long id){
        return goodsRepository.findById(id).orElseThrow(ThereIsNoSuchGoodsException::new);
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
        return goodsConverter.toGoodsDTO(goodsRepository.save(goods));
    }

    public void deleteGoodsById(Long id) {
        Goods goods = getGoods(id);
        if (goods == null) throw new ThereIsNoSuchGoodsException();
        goodsRepository.deleteById(id);
    }

    public List<GoodsDTO> getAllGoods() {
        return goodsConverter.toGoodsDTOList(goodsRepository.findAll());
    }
}
