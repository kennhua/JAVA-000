package lgh.geektime.demo.service.impl;

import lgh.geektime.demo.dao.OrderMapper;
import lgh.geektime.demo.entity.Order;
import lgh.geektime.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liugenghua
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    public Order findOrder(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}
