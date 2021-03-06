package datasource.controller;

import datasource.entity.Order;
import datasource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liugenghua
 * @date：2020/11/30
 * @Description TODO
 * @Version:1.0
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
}
