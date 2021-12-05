package com.myjob.dinstesttask.service;

import com.myjob.dinstesttask.DinstesttaskApplication;
import com.myjob.dinstesttask.models.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LogManager.getLogger(DinstesttaskApplication.class);

    @Value("${kafka.producer.topic}") private String topic;

    @Autowired
    private KafkaTemplate<String, Data> kafkaTemplate;

    public void sendMsg(Data msg){

        logger.info("Kafka produc...");

        kafkaTemplate.send(topic, msg);
    }
}
