package com.kennhua.dubbo.demo.api;

import com.kennhua.dubbo.demo.entity.RecordDTO;

/**
 * @author liugenghua
 **/
public interface RecordService {

    void rmbToUsd(RecordDTO recordDTO);

    void usdToRmb(RecordDTO recordDTO);
}
