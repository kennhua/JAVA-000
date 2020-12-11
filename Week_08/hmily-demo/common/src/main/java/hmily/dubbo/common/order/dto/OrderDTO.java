package hmily.dubbo.common.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liugenghua
 **/
@Data
public class OrderDTO implements Serializable {

    private BigDecimal amount;

    private Integer count;
}
