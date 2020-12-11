package hmily.dubbo.order.service.impl;

import hmily.dubbo.common.account.api.AccountService;
import hmily.dubbo.common.account.dto.AccountDTO;
import hmily.dubbo.common.order.entity.Order;
import hmily.dubbo.common.stock.api.StockService;
import hmily.dubbo.common.stock.dto.StockDTO;
import hmily.dubbo.order.enums.OrderStatusEnum;
import hmily.dubbo.order.mapper.OrderMapper;
import hmily.dubbo.order.service.PaymentService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liugenghua
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private StockService stockService;

    @Autowired(required = false)
    private AccountService accountService;

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void makePayment(Order order) {
        //修改订单状态为支付中
        updateOrderStatus(order, OrderStatusEnum.PAYING);
        //扣除用户金额
        accountService.payment(buildAccountDTO(order));
        //扣减库存
        stockService.decrease(buildStockDTO(order));
    }

    private AccountDTO buildAccountDTO(Order order) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getAmount());
        accountDTO.setUserId(order.getUserId());
        return accountDTO;
    }

    private StockDTO buildStockDTO(Order order) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setCount(order.getCount());
        stockDTO.setProductId(order.getProductId());
        return stockDTO;
    }

    private void updateOrderStatus(Order order, OrderStatusEnum orderStatusEnum) {
        order.setStatus(orderStatusEnum.getCode());
        orderMapper.update(order);
    }

    public void confirmOrderStatus(Order order){
        updateOrderStatus(order, OrderStatusEnum.PAY_SUCCESS);
    }

    public void cancelOrderStatus(Order order){
        updateOrderStatus(order, OrderStatusEnum.PAY_FAIL);
    }
}
