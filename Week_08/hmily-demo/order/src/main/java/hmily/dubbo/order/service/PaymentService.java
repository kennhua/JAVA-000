package hmily.dubbo.order.service;

import hmily.dubbo.common.order.entity.Order;

/**
 * @author liugenghua
 **/
public interface PaymentService {

    void makePayment(Order order);
}
