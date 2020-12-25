package ru.scur.orderapp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.scur.orderapp.dto.OrderDTO;
import ru.scur.orderapp.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order_app/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }

    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable("id") Long id){
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable("id") Long id){
        orderService.deleteOrderById(id);
    }

    @PutMapping("/{id}")
    public OrderDTO editOrder(@PathVariable("id") Long id, @RequestBody OrderDTO orderDTO){
        return orderService.editOrder(id, orderDTO);
    }
}
