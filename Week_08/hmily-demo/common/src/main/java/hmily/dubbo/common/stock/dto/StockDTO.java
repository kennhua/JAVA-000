package hmily.dubbo.common.stock.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liugenghua
 **/
@Data
public class StockDTO implements Serializable {

    private static final long serialVersionUID = 8229355519336565493L;

    private String productId;

    private Integer count;
}
