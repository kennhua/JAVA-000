package hmily.dubbo.common.account.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liugenghua
 **/
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = -81849676368907419L;

    private Long id;

    private String userId;

    private BigDecimal total;

    private BigDecimal freeze;

    private Date createTime;

    private Date updateTime;
}