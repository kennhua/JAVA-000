package com.kennhua.dubbo.demo.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author liugenghua
 **/
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@MapperScan("com.kennhua.dubbo.demo.com.kennhua.dubbo.demo.provider.mapper")
public class ProviderApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
