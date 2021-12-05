package com.myjob.dinstesttask.service;

import com.myjob.dinstesttask.DinstesttaskApplication;
import com.myjob.dinstesttask.dao.DataInt;
import com.myjob.dinstesttask.models.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainServiceImpl implements MainService {

    private DataInt dataInt;

    private static final Logger logger = LogManager.getLogger(DinstesttaskApplication.class);

    @Value("${database.firsttable}") private String FIRST_TABLE;
    @Value("${database.secondtable}") private String SECOND_TABLE;

    @Value("${kafka.producer.topic}") private String topic;

    @Autowired
    public MainServiceImpl(DataInt dataInt) {
        this.dataInt = dataInt;
    }

    @Autowired
    private Producer producer;

    @Override
    public void doWork() {

        logger.info("DB read (MainServiceImpl.doWork)...");

        List<Data> data = dataInt.read(FIRST_TABLE);
        for (Data d: data) {
            System.out.println(d.toString());
        }
    }

    @Override
    public void sendToKafka() {

        logger.info("Kafka send (MainServiceImpl.sendToKafka)...");

        List<Data> data = dataInt.read(FIRST_TABLE);
        for (Data d: data) {
            System.out.println("send message ");
            producer.sendMsg(d);
        }
    }
}
