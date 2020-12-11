package hmily.dubbo.common.account.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liugenghua
 **/
@Data
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 7223470850578998427L;

    private String userId;

    private BigDecimal amount;
}
