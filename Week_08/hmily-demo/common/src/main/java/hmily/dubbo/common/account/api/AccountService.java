package hmily.dubbo.common.account.api;

import hmily.dubbo.common.account.dto.AccountDTO;
import org.dromara.hmily.annotation.Hmily;

/**
 * @author liugenghua
 **/
public interface AccountService {

    @Hmily
    boolean payment(AccountDTO accountDTO);
}
