package com.kennhua.dubbo.demo.consumer;

import com.kennhua.dubbo.demo.api.RecordService;
import com.kennhua.dubbo.demo.entity.RecordDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;

/**
 * @author liugenghua
 **/
@SpringBootApplication
public class ConsumerApplication {

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:23456")
    private RecordService recordService;

    public static void main(final String[] args) {
        SpringApplication.run(ConsumerApplication.class, args).close();

    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            RecordDTO recordDTO = new RecordDTO();
            recordDTO.setUserId(Long.valueOf(1));
            recordDTO.setOtherId(Long.valueOf(2));
            recordDTO.setRmb(new BigDecimal(7));
            recordService.rmbToUsd(recordDTO);

//            RecordDTO recordDTO2 = new RecordDTO();
//            recordDTO2.setUserId(Long.valueOf(1));
//            recordDTO2.setOtherId(Long.valueOf(2));
//            recordDTO2.setUsd(new BigDecimal(1));
//            recordService.rmbToUsd(recordDTO2);
        };
    }
}
