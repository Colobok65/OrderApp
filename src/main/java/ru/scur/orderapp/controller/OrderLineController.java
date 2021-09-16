package ru.scur.orderapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.scur.orderapp.dto.OrderLineDTO;
import ru.scur.orderapp.service.OrderLineService;

import java.util.List;

@RestController
@RequestMapping("/order_app/line")
@CrossOrigin
public class OrderLineController {
    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @PostMapping
    public OrderLineDTO createOrderLine(@RequestBody OrderLineDTO orderLineDTO){
        return orderLineService.createOrderLine(orderLineDTO);
    }

    @GetMapping
    public List<OrderLineDTO> getAllOrderLines(){
        return orderLineService.getAllOrderLines();
    }

    @GetMapping("/goods/{goods_id}")
    public List<OrderLineDTO> getLineByGoodsId(@PathVariable("goods_id") Long goodsId) {
        return orderLineService.getLineByGoodsId(goodsId);
    }

    @GetMapping("/{id}")
    public OrderLineDTO getOrderLineById(@PathVariable("id") Long id){
        return orderLineService.getOrderLineById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderLineById(@PathVariable("id") Long id){
        orderLineService.deleteOrderLineById(id);
    }

    @PutMapping("/{id}")
    public OrderLineDTO editOrder(@PathVariable("id") Long id, @RequestBody OrderLineDTO orderLineDTO){
        return orderLineService.editOrderLine(id, orderLineDTO);
    }

    @GetMapping("/order/{order_id}")
    public List<OrderLineDTO> findAllLinesByOrderId(@PathVariable("order_id") Long goodsOrderId) {
        return orderLineService.getLineByOrderId(goodsOrderId);
    }
}
