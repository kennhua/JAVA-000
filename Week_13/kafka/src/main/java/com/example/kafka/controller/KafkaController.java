package com.example.kafka.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liugenghua
 **/
@RestController
public class KafkaController {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/send/{value}")
    public void send(@PathVariable String value) {
        this.kafkaTemplate.send("test32", value);
    }

    @KafkaListener(id = "test32", topics = "test32")
    public void listen(String value) {
        System.out.println("input value: " + value);
    }
}
