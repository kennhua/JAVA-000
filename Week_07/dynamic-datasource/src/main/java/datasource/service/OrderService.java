package datasource.service;

import datasource.entity.Order;

/**
 * @author liugenghua
 * @date：2020/11/30
 * @Description TODO
 * @Version:1.0
 **/
public interface OrderService {

    Order findOrder(Long id);

    int insert(Order order);
}
