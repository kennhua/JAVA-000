package hmily.dubbo.account.mapper;

import hmily.dubbo.common.account.dto.AccountDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author liugenghua
 **/
@Mapper
public interface AccountMapper {

    @Update("update t_account set total = total - #{amount},freeze= freeze + #{amount}" +
            " where user_id =#{userId}  and  total > 0  ")
    int update(AccountDTO accountDTO);

    @Update("update t_account set freeze= freeze - #{amount}" +
            " where user_id =#{userId}  and  total > 0  ")
    int confirm(AccountDTO accountDTO);

    @Update("update t_account set total = total + #{amount},freeze= freeze - #{amount}" +
            " where user_id =#{userId}  and  total > 0  ")
    int cancel(AccountDTO accountDTO);
}
