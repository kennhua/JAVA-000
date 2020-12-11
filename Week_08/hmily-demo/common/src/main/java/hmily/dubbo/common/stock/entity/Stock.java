package hmily.dubbo.common.stock.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liugenghua
 **/
@Data
public class Stock implements Serializable {

    private static final long serialVersionUID = 6957734749389133832L;

    private Long id;

    private String productId;

    private Integer total;

    private Integer lock;

    private Date createTime;

    private Date updateTime;
}
