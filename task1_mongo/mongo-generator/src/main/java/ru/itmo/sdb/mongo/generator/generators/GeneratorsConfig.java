package ru.itmo.sdb.mongo.generator.generators;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class GeneratorsConfig {
    @Bean
    public FakeValuesService fakeValuesService() {
        return new FakeValuesService(new Locale("en-EN"), randomService());
    }

    @Bean
    public Faker faker() {
        return new Faker(new Locale("en-EN"));
    }

    @Bean
    public RandomService randomService() {
        return new RandomService();
    }
}
