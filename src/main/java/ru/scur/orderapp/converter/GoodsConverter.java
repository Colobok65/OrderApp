package ru.scur.orderapp.converter;

import org.springframework.stereotype.Service;
import ru.scur.orderapp.dto.GoodsDTO;
import ru.scur.orderapp.model.Goods;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsConverter {

    public GoodsDTO toGoodsDTO(Goods goods){
        return new GoodsDTO(
                goods.getId(),
                goods.getName(),
                goods.getPrice()
        );
    }

    public List<GoodsDTO> toGoodsDTOList(List<Goods> list){
        return list.stream().map(this::toGoodsDTO).collect(Collectors.toList());
    }
}
