package datasource.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Order {
    private Long id;

    private Long userId;

    private String userName;

    private Long orderNumber;

    private String receiver;

    private String receiverPhone;

    private String receiverAddress;

    private BigDecimal orderMount;

    private Byte state;

    private Date createTime;

    private Date updateTime;

}