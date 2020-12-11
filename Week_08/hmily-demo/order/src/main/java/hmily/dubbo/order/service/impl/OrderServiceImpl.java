package hmily.dubbo.order.service.impl;

import hmily.dubbo.common.order.dto.OrderDTO;
import hmily.dubbo.common.order.entity.Order;
import hmily.dubbo.order.enums.OrderStatusEnum;
import hmily.dubbo.order.mapper.OrderMapper;
import hmily.dubbo.order.service.OrderService;
import hmily.dubbo.order.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.dromara.hmily.common.utils.IdWorkerUtils;

import java.util.List;

/**
 * @author liugenghua
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private PaymentService paymentService;

    public List<Order> findAll(){
        return orderMapper.findAll();
    }

    @Override
    public String orderPay(OrderDTO orderDTO) {
        Order order = buildOrder(orderDTO);
        //每次请求先插入一条状态为未支付的订单信息
        orderMapper.insert(order);
        //修改订单信息，扣除金额、库存，最后确认订单信息
        paymentService.makePayment(order);
        return "success";
    }

    private Order buildOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(IdWorkerUtils.getInstance().createUUID());
        order.setAmount(orderDTO.getAmount());
        order.setCount(orderDTO.getCount());
        order.setProductId("123456");
        order.setUserId("1");
        order.setStatus(OrderStatusEnum.NOT_PAY.getCode());
        return order;
    }
}
