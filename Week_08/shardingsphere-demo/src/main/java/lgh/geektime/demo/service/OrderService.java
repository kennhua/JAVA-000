package lgh.geektime.demo.service;

import lgh.geektime.demo.entity.Order;

import java.util.List;

/**
 * @author liugenghua
 **/
public interface OrderService {

    Order findOrder(Long id);

    int insert(Order order);

    List<Order> findAll();
}
