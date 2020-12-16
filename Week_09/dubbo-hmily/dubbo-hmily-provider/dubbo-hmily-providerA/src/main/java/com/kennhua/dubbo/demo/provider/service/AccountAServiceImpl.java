package com.kennhua.dubbo.demo.provider.service;

import com.kennhua.dubbo.demo.api.AccountAService;
import com.kennhua.dubbo.demo.entity.RecordDTO;
import com.kennhua.dubbo.demo.provider.mapper.AccountAMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author liugenghua
 **/
@DubboService(version = "1.0.0")
public class AccountAServiceImpl implements AccountAService {

    @Autowired
    private AccountAMapper accountAMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirmDecrease", cancelMethod = "cancelDecrease")
    public boolean decrease(RecordDTO recordDTO) {
        if(null != recordDTO.getUsd()){
            recordDTO.setRmb(recordDTO.getUsd().multiply(new BigDecimal(7)));
        }
        return accountAMapper.decrease(recordDTO) > 0;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmIncrease", cancelMethod = "cancelIncrease")
    public boolean increase(RecordDTO recordDTO) {
        if(null != recordDTO.getRmb()){
            recordDTO.setUsd(recordDTO.getRmb().divide(new BigDecimal(7)));
        }
        return accountAMapper.increase(recordDTO) > 0;
    }

    public boolean confirmDecrease(RecordDTO recordDTO) {
        if(null != recordDTO.getUsd()){
            recordDTO.setRmb(recordDTO.getUsd().multiply(new BigDecimal(7)));
        }
        return accountAMapper.confirmDecrease(recordDTO) > 0;
    }

    public boolean cancelDecrease(RecordDTO recordDTO) {
        if(null != recordDTO.getUsd()){
            recordDTO.setRmb(recordDTO.getUsd().multiply(new BigDecimal(7)));
        }
        return accountAMapper.cancelDecrease(recordDTO) > 0;
    }

    public boolean confirmIncrease(RecordDTO recordDTO) {
        if(null != recordDTO.getRmb()){
            recordDTO.setUsd(recordDTO.getRmb().divide(new BigDecimal(7)));
        }
        return accountAMapper.confirmIncrease(recordDTO) > 0;
    }

    public boolean cancelIncrease(RecordDTO recordDTO) {
        if(null != recordDTO.getRmb()){
            recordDTO.setUsd(recordDTO.getRmb().divide(new BigDecimal(7)));
        }
        return accountAMapper.cancelIncrease(recordDTO) > 0;
    }
}
