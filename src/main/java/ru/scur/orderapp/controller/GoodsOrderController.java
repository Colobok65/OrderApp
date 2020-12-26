package ru.scur.orderapp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scur.orderapp.dto.GoodsOrderDTO;
import ru.scur.orderapp.service.GoodsOrderService;

import java.util.List;

@RestController
@RequestMapping("/order_app/order")
public class GoodsOrderController {

    private final GoodsOrderService goodsOrderService;

    public GoodsOrderController(GoodsOrderService orderService) {
        this.goodsOrderService = orderService;
    }

    @PostMapping
    public GoodsOrderDTO createOrder(@RequestBody GoodsOrderDTO goodsOrderDTO){
        return goodsOrderService.createOrder(goodsOrderDTO);
    }

    @GetMapping
    public List<GoodsOrderDTO> getAllOrders(){
        return goodsOrderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public GoodsOrderDTO getOrderById(@PathVariable("id") Long id){
        return goodsOrderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable("id") Long id){
        goodsOrderService.deleteOrderById(id);
    }

    @PutMapping("/{id}")
    public GoodsOrderDTO editOrder(@PathVariable("id") Long id, @RequestBody GoodsOrderDTO goodsOrderDTO){
        return goodsOrderService.editOrder(id, goodsOrderDTO);
    }
}
