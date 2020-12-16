package com.kennhua.dubbo.demo.record.service.impl;

import com.kennhua.dubbo.demo.api.RecordService;
import com.kennhua.dubbo.demo.entity.RecordDTO;
import com.kennhua.dubbo.demo.entity.Record;
import com.kennhua.dubbo.demo.record.mapper.RecordMapper;
import com.kennhua.dubbo.demo.record.service.ExchangeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liugenghua
 **/
@DubboService(version = "1.0.0")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ExchangeService exchangeService;

    @Override
    public void rmbToUsd(RecordDTO recordDTO) {
        // 保存一条交易记录
        saveRecord(Record.buildRecord(recordDTO));
        // 进入交易阶段
        exchangeService.rmbToUsd(recordDTO);
    }

    @Override
    public void usdToRmb(RecordDTO recordDTO) {
        // 保存一条交易记录
        saveRecord(Record.buildRecord(recordDTO));
        // 进入交易阶段
        exchangeService.usdToRmb(recordDTO);
    }

    private void saveRecord(Record record) {
        recordMapper.insert(record);
    }

}
