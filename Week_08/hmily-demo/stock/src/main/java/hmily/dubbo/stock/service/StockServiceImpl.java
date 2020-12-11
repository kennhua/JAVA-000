package hmily.dubbo.stock.service;

import hmily.dubbo.common.stock.dto.StockDTO;
import hmily.dubbo.stock.mapper.StockMapper;
import hmily.dubbo.common.stock.api.StockService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liugenghua
 **/
@Service("stockService")
public class StockServiceImpl implements StockService {

    @Autowired(required = false)
    private StockMapper stockMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean decrease(StockDTO stockDTO) {
        return stockMapper.decrease(stockDTO) > 0;
    }

    public Boolean confirm(StockDTO stockDTO) {
        return stockMapper.confirm(stockDTO) > 0;
    }

    public Boolean cancel(StockDTO stockDTO) {
        return stockMapper.cancel(stockDTO) > 0;
    }
}
