package datasource.service.impl;

import datasource.entity.Order;
import datasource.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liugenghua
 * @date：2020/12/1
 * @Description 订单服务实现类
 * @Version:1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DataSource datasource;

    public int insert(Order order) {
        String sql = "insert into tb_order (user_id,user_name,order_number,receiver,receiver_phone,receiver_address,order_mount) values (?,?,?,?,?,?,?)";
        try (Connection connection = datasource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setString(2, order.getUserName());
            preparedStatement.setLong(3, order.getOrderNumber());
            preparedStatement.setString(4, order.getReceiver());
            preparedStatement.setString(5, order.getReceiverPhone());
            preparedStatement.setString(6, order.getReceiverAddress());
            preparedStatement.setBigDecimal(7, order.getOrderMount());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Order findOrder(Long id) {
        Order order = new Order();
        String sql = "select user_name,order_number,order_mount from tb_order where id=?";
        try (Connection connection = datasource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                order.setUserName(resultSet.getString("user_name"));
                order.setOrderNumber(resultSet.getLong("order_number"));
                order.setOrderMount(resultSet.getBigDecimal("order_mount"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
