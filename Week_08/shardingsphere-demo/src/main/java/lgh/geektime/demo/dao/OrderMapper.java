package lgh.geektime.demo.dao;

import lgh.geektime.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liugenghua
 **/
@Mapper
public interface OrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> findAll();
}
