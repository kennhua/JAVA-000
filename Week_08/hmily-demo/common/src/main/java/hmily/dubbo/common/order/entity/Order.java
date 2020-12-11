package hmily.dubbo.common.order.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liugenghua
 **/
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -8551347266419380333L;

    private Long id;

    private Date createTime;

    private Integer status;

    private String productId;

    private BigDecimal amount;

    private Integer count;

    private String userId;
}
