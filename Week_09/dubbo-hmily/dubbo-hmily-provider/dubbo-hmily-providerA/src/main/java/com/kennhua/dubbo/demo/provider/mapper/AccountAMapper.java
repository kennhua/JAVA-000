package com.kennhua.dubbo.demo.provider.mapper;

import com.kennhua.dubbo.demo.entity.RecordDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author liugenghua
 **/
@Mapper
public interface AccountAMapper {

    @Update("update t_account_rmb set rmb = rmb - #{rmb},freeze=freeze + #{rmb} where user_id =#{userId} ")
    int decrease(RecordDTO recordDTO);

    @Update("update t_account_rmb set freeze=freeze - #{rmb} where user_id =#{userId} ")
    int confirmDecrease(RecordDTO recordDTO);

    @Update("update t_account_rmb set rmb = rmb + #{rmb},freeze=freeze - #{rmb} where user_id =#{userId} ")
    int cancelDecrease(RecordDTO recordDTO);

    @Update("update t_account_usd set usd = usd + #{usd},freeze=freeze + #{usd} where user_id =#{otherId} ")
    int increase(RecordDTO recordDTO);

    @Update("update t_account_usd set freeze=freeze - #{usd} where user_id =#{otherId} ")
    int confirmIncrease(RecordDTO recordDTO);

    @Update("update t_account_usd set usd = usd - #{usd},freeze=freeze - #{usd} where user_id =#{otherId} ")
    int cancelIncrease(RecordDTO recordDTO);
}
