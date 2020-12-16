package com.kennhua.dubbo.demo.record.service.impl;

import com.kennhua.dubbo.demo.api.AccountAService;
import com.kennhua.dubbo.demo.api.AccountBService;
import com.kennhua.dubbo.demo.entity.Record;
import com.kennhua.dubbo.demo.entity.RecordDTO;
import com.kennhua.dubbo.demo.enums.RecordStatusEnum;
import com.kennhua.dubbo.demo.record.mapper.RecordMapper;
import com.kennhua.dubbo.demo.record.service.ExchangeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liugenghua
 **/
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private RecordMapper recordMapper;

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:9091")
    private AccountAService accountAService;

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:9092")
    private AccountBService accountBService;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void rmbToUsd(RecordDTO recordDTO) {
        // 修改交易记录状态为交易中
        updateRecordStatus(Record.buildRecord(recordDTO), RecordStatusEnum.EXCHANGING);
        // 扣除账户余额
        accountAService.decrease(recordDTO);
        // 增加账户余额
        accountBService.increase(recordDTO);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void usdToRmb(RecordDTO recordDTO) {
        // 修改交易记录状态为交易中
        updateRecordStatus(Record.buildRecord(recordDTO), RecordStatusEnum.EXCHANGING);
        // 扣除账户余额
        accountBService.decrease(recordDTO);
        // 增加账户余额
        accountAService.increase(recordDTO);
    }

    private void updateRecordStatus(Record record, RecordStatusEnum statusEnum) {
        record.setStatus(statusEnum.getCode());
        recordMapper.update(record);
    }

    /**
     * 交易成功
     *
     * @param recordDTO
     */
    public void confirm(RecordDTO recordDTO) {
        updateRecordStatus(Record.buildRecord(recordDTO), RecordStatusEnum.EXCHANGE_SUCCESS);
    }

    /**
     * 交易失败
     *
     * @param recordDTO
     */
    public void cancel(RecordDTO recordDTO) {
        updateRecordStatus(Record.buildRecord(recordDTO), RecordStatusEnum.EXCHANGE_FAIL);
    }
}
