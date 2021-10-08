package ru.scur.orderapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.scur.orderapp.dto.GoodsOrderDTO;
import ru.scur.orderapp.service.GoodsOrderService;

import java.util.List;

@RestController
@RequestMapping("/order_app/order")
@CrossOrigin(origins = "http://localhost:4200")
public class GoodsOrderController {

    private final GoodsOrderService goodsOrderService;

    public GoodsOrderController(GoodsOrderService orderService) {
        this.goodsOrderService = orderService;
    }

    @PostMapping
    public GoodsOrderDTO createGoodsOrder(@RequestBody GoodsOrderDTO goodsOrderDTO){
        return goodsOrderService.createGoodsOrder(goodsOrderDTO);
    }

    @GetMapping
    public List<GoodsOrderDTO> getAllGoodsOrders(){
        return goodsOrderService.getAllGoodsOrders();
    }

    @GetMapping("/user/{user_id}")
    public List<GoodsOrderDTO> getAllOrdersByUserId(@PathVariable("user_id") Long userId) {
        return goodsOrderService.getAllGoodsOrdersByUserId(userId);
    }

    @GetMapping("/{id}")
    public GoodsOrderDTO getGoodsOrderById(@PathVariable("id") Long id){
        return goodsOrderService.getGoodsOrderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGoodsOrderById(@PathVariable("id") Long id){
        goodsOrderService.deleteGoodsOrderById(id);
    }

    @PutMapping("/{id}")
    public GoodsOrderDTO editGoodsOrder(@PathVariable("id") Long id, @RequestBody GoodsOrderDTO goodsOrderDTO){
        return goodsOrderService.editGoodsOrder(id, goodsOrderDTO);
    }
}
