package ru.itmo.sdb.mysql.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MySQLConfiguration.class)
public class DataGen {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DataGen.class, args);
        DummyDataGenerator dataGenerator = applicationContext.getBean(DummyDataGenerator.class);

        dataGenerator.generateAndSave();
    }
}
