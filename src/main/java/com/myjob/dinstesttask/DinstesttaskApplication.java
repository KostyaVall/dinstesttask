package com.myjob.dinstesttask;

import com.myjob.dinstesttask.config.SpringConfig;
import com.myjob.dinstesttask.service.MainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Scanner;

@EnableKafka
@SpringBootApplication
public class DinstesttaskApplication {

    private static final Logger logger = LogManager.getLogger(DinstesttaskApplication.class);

    public static void main(String[] args) {

        logger.info("Starting app...");

        System.out.println("Пожалуйста, введите код операции: ");
        System.out.println("1 - Выгрузить данные в Kafka; 2 - Загрузить данные в DB; 0 - Выход.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt();
            if(n == 1 || n == 2) {

                ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
                MainService service = context.getBean(MainService.class);

                logger.info("send to Kafka...");
                service.sendToKafka();

                break;
            } else if (n == 0) {
                break;
            } else {
                System.out.println("Введен некорретный код операции. Повторите ввод.");
            }
        }
        logger.info("Finish!");
    }
}
