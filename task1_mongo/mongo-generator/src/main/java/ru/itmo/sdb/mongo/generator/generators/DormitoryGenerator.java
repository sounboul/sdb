package ru.itmo.sdb.mongo.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mongo.core.entities.Dormitory;

@Service
public class DormitoryGenerator {
    @Autowired
    private Faker faker;

    @Autowired
    private IdGenerator idGenerator;

    public Dormitory generate() {
        final Dormitory dormitory = new Dormitory();

        dormitory.id = idGenerator.generate();
        dormitory.address = faker.address().streetAddress();
        dormitory.city = faker.lordOfTheRings().location();
        dormitory.name = faker.address().state();

        return dormitory;
    }
}
