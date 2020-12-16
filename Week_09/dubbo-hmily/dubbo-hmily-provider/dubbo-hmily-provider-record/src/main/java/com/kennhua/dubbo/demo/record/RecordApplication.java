package com.kennhua.dubbo.demo.record;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author liugenghua
 **/
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class RecordApplication {

    public static void main(final String[] args) {
        SpringApplication.run(RecordApplication.class, args);
    }
}
