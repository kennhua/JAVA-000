package hmily.dubbo.stock.mapper;

import hmily.dubbo.common.stock.dto.StockDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author liugenghua
 **/
@Mapper
public interface StockMapper {

    @Update("update t_stock set total = total - #{count},`lock` = `lock` + #{count} " +
            " where product_id =#{productId} and total > 0  ")
    int decrease(StockDTO stockDTO);

    @Update("update t_stock set `lock` = `lock` - #{count} " +
            " where product_id =#{productId} and total > 0  ")
    int confirm(StockDTO stockDTO);

    @Update("update t_stock set total = total + #{count},`lock` = `lock` - #{count} " +
            " where product_id =#{productId} and total > 0  ")
    int cancel(StockDTO stockDTO);
}
