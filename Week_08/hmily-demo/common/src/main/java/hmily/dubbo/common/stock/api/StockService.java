package hmily.dubbo.common.stock.api;

import hmily.dubbo.common.stock.dto.StockDTO;
import org.dromara.hmily.annotation.Hmily;

/**
 * @author liugenghua
 **/
public interface StockService {

    @Hmily
    Boolean decrease(StockDTO stockDTO);
}
