package com.kennhua.dubbo.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liugenghua
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO implements Serializable {

    private BigDecimal rmb;

    private BigDecimal usd;

    private BigDecimal freeze;

    private Long userId;

    private Long otherId;

}
