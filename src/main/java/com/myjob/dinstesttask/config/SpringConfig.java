package com.myjob.dinstesttask.config;

import com.myjob.dinstesttask.models.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ImportResource("classpath:app-config.xml")
public class SpringConfig {

    @Value("${database.password}") private String password;
    @Value("${database.username}") private  String userName;
    @Value("${database.driverclassname}") private String driverClassName;
    @Value("${database.url}") private String url;

    @Value("${kafka.producer.bootstrapservers}") private String kafkaProducerServer;

    @Value("${kafka.consumer.bootstrapservers}") private String kafkaConsumerServer;
    @Value("${kafka.consumer.groupid}") private String kafkaGroupId;
    @Value("${kafka.consumer.topic}") private String kafkaTopic;

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public String topicName() {
        return kafkaTopic;
    }

    @Bean
    public String groupId() {
        return kafkaGroupId;
    }
}
