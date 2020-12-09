package lgh.geektime.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liugenghua
 *
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
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
