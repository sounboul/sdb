package ru.itmo.sdb.postgres.generator.generators;

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
        return new FakeValuesService(new Locale("en"), randomService());
    }

    @Bean
    public Faker faker() {
        return new Faker(new Locale("en"));
    }

    @Bean
    public RandomService randomService() {
        return new RandomService();
    }
}
