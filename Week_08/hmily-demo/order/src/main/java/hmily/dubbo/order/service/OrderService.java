package hmily.dubbo.order.service;

import hmily.dubbo.common.order.dto.OrderDTO;
import hmily.dubbo.common.order.entity.Order;

import java.util.List;

/**
 * @author liugenghua
 **/
public interface OrderService {

    List<Order> findAll();

    String orderPay(OrderDTO orderDTO);
}
