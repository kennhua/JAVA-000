package datasource.service.impl;

import datasource.aop.ReadOnly;
import datasource.dao.OrderMapper;
import datasource.entity.Order;
import datasource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liugenghua
 * @date：2020/11/30
 * @Description @ReadOnly注解表示从从库读数据
 * @Version:1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int insert(Order order) {
        return orderMapper.insertSelective(order);
    }

    @ReadOnly
    @Override
    public Order findOrder(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}
