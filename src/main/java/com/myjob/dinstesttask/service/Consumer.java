package com.myjob.dinstesttask.service;

import com.myjob.dinstesttask.DinstesttaskApplication;
import com.myjob.dinstesttask.dao.DataInt;
import com.myjob.dinstesttask.models.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private DataInt dataInt;

    private static final Logger logger = LogManager.getLogger(DinstesttaskApplication.class);

    @Value("${database.secondtable}") private String SECOND_TABLE;

    @Autowired
    public Consumer(DataInt dataInt) {
        this.dataInt = dataInt;
    }

    @KafkaListener (topics = "#{@topicName}", groupId = "#{@groupId}")
    public void recordListener(Data data){

        logger.info("Kafka listen...");
        dataInt.wright(SECOND_TABLE, data);
    }
}
