package com.kennhua.dubbo.demo.entity;

import com.kennhua.dubbo.demo.enums.RecordStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.hmily.common.utils.IdWorkerUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liugenghua
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record implements Serializable {

    private Long id;

    private BigDecimal rmb;

    private BigDecimal usd;

    private Long userId;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public static Record buildRecord(RecordDTO recordDTO) {
        Record record = new Record();
        record.setId(IdWorkerUtils.getInstance().createUUID());
        record.setRmb(recordDTO.getRmb());
        record.setUsd(recordDTO.getUsd());
        record.setUserId(recordDTO.getUserId());
        record.setStatus(RecordStatusEnum.NOT_EXCHANGE.getCode());
        return record;
    }
}
