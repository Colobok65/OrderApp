package ru.scur.orderapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.scur.orderapp.dto.GoodsDTO;
import ru.scur.orderapp.service.GoodsService;

import java.util.List;

@RestController
@RequestMapping("/order_app/goods")
@CrossOrigin
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
    public GoodsDTO getGoodsById(@PathVariable("id") Long id){
        return goodsService.getGoodsById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGoodsById(@PathVariable("id") Long id){
        goodsService.deleteGoodsById(id);
    }

    @PutMapping("/{id}")
    public GoodsDTO editGoods(@PathVariable("id") Long id, @RequestBody GoodsDTO goodsDTO){
        return goodsService.editGoods(id, goodsDTO);
    }
}
