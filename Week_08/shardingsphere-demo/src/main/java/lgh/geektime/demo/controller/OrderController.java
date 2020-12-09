package lgh.geektime.demo.controller;

import lgh.geektime.demo.entity.Order;
import lgh.geektime.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liugenghua
 **/
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    public int insert(@RequestBody Order order){
        return orderService.insert(order);
    }


    @GetMapping("/{id}")
    public Order findOrder(@PathVariable Long id) {
        return orderService.findOrder(id);
    }

    @GetMapping("/findAll")
    public List<Order> findAll() {
        return orderService.findAll();
    }
}
