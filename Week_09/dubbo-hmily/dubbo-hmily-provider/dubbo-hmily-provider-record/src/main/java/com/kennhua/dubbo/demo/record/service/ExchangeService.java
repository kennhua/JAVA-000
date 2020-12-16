package com.kennhua.dubbo.demo.record.service;

import com.kennhua.dubbo.demo.entity.RecordDTO;

/**
 * @author liugenghua
 **/
public interface ExchangeService {

    void rmbToUsd(RecordDTO recordDTO);

    void usdToRmb(RecordDTO recordDTO);
}
