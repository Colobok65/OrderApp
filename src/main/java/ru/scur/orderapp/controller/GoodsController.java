package ru.scur.orderapp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scur.orderapp.dto.GoodsDTO;
import ru.scur.orderapp.service.GoodsService;

import java.util.List;

@RestController
@RequestMapping("/order_app/goods")
public class GoodsController {

    private final GoodsService goodsService;


    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping
    public GoodsDTO createGoods(@RequestBody GoodsDTO goodsDTO){
        return goodsService.createGoods(goodsDTO);
    }

    @GetMapping
    public List<GoodsDTO> getAllGoods(){
        return goodsService.getAllGoods();
    }

    @GetMapping("/{id}")
    public GoodsDTO getOrderById(@PathVariable("id") Long id){
        return goodsService.getGoodsById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGoodsById(@PathVariable("id") Long id){
        goodsService.deleteGoodsById(id);
    }

    @PutMapping("/{id}")
    public GoodsDTO editOrder(@PathVariable("id") Long id, @RequestBody GoodsDTO goodsDTO){
        return goodsService.editGoods(id, goodsDTO);
    }
}
