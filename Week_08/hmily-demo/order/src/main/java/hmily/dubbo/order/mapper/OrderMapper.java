package hmily.dubbo.order.mapper;

import hmily.dubbo.common.order.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

/**
 * @author liugenghua
 **/
@Mapper
public interface OrderMapper {

    @Select("select * from t_order")
    List<Order> findAll();

    @Insert("insert into t_order (id,status,product_id,amount,count,user_id) " +
            " values ( #{id},#{status},#{productId},#{amount},#{count},#{userId})")
    int insert(Order order);

    @Update("update t_order set status = #{status}  where id = #{id}")
    int update(Order order);
}
