package ru.itmo.sdb.mongo.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mongo.core.entities.Dormitory;
import ru.itmo.sdb.mongo.core.entities.Visit;

import java.util.concurrent.TimeUnit;

@Service
public class VisitGenerator {
    @Autowired
    private Faker faker;

    @Autowired
    private RandomHelper randomHelper;

    @Autowired
    private IdGenerator idGenerator;

    public Visit generate(Dormitory dormitory) {
        Visit visit = new Visit();

        visit.id = idGenerator.generate();
        visit.exit_date = faker.date().past(365, TimeUnit.DAYS);
        visit.enter_date = faker.date().past(12, TimeUnit.HOURS, visit.exit_date);
        visit.dormitory_id = dormitory.id;
        visit.visitorFirstName = faker.name().firstName();
        visit.visitorLastName = faker.name().firstName();
        visit.visitorPatronymic = faker.name().firstName();
        return visit;
    }
}
