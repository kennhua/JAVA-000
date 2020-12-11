package hmily.dubbo.account.service;

import hmily.dubbo.account.mapper.AccountMapper;
import hmily.dubbo.common.account.api.AccountService;
import hmily.dubbo.common.account.dto.AccountDTO;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liugenghua
 **/
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean payment(AccountDTO accountDTO) {
        return accountMapper.update(accountDTO) > 0;
    }

    public Boolean confirm(AccountDTO accountDTO) {
        return accountMapper.confirm(accountDTO) > 0;
    }

    public Boolean cancel(AccountDTO accountDTO) {
        return accountMapper.cancel(accountDTO) > 0;
    }
}
