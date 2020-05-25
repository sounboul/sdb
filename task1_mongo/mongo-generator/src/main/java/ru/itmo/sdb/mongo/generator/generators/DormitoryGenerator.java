package ru.itmo.sdb.mongo.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mongo.core.entities.Dormitory;

import java.util.Arrays;

@Service
public class DormitoryGenerator {
    @Autowired
    private Faker faker;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private RandomHelper randomHelper;

    public Dormitory generate() {
        final Dormitory dormitory = new Dormitory();

        dormitory.id = idGenerator.generate();
        dormitory.address = faker.address().streetAddress();
        dormitory.name = faker.address().state();

        dormitory.city = randomHelper.randomFrom(Arrays.asList(
                "San Jose",
                "New York",
                "Boston",
                "Moscow",
                "London",
                "Kemerovo",
                "Tashkent"
        ));

        return dormitory;
    }
}
