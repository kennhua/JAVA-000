package hmily.dubbo.order.controller;

import hmily.dubbo.common.order.dto.OrderDTO;
import hmily.dubbo.common.order.entity.Order;
import hmily.dubbo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liugenghua
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/orderPay")
    public String orderPay(@RequestBody OrderDTO orderDTO) {
        return orderService.orderPay(orderDTO);
    }
}
