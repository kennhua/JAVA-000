package com.kennhua.dubbo.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liugenghua
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUsd implements Serializable {

    private Long id;

    private BigDecimal usd;

    private Long userId;

    private BigDecimal freeze;

    private Date createTime;

    private Date updateTime;
}
