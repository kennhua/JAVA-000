package com.kennhua.dubbo.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liugenghua
 **/
@Getter
@AllArgsConstructor
public enum RecordStatusEnum {

    NOT_EXCHANGE(1, "未交易"),

    EXCHANGING(2, "交易中"),

    EXCHANGE_FAIL(3, "交易失败"),

    EXCHANGE_SUCCESS(4, "交易成功");

    private final int code;

    private final String desc;
}
