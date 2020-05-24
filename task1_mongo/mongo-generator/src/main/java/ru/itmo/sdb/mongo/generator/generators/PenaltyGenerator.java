package ru.itmo.sdb.mongo.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mongo.core.entities.Dormitory;
import ru.itmo.sdb.mongo.core.entities.Penalty;
import ru.itmo.sdb.mongo.core.entities.Room;
import ru.itmo.sdb.mongo.core.entities.Tenant;

import java.util.concurrent.TimeUnit;

@Service
public class PenaltyGenerator {
    @Autowired
    private Faker faker;

    @Autowired
    private IdGenerator idGenerator;

    public Penalty generate(Dormitory dormitory, Tenant tenant) {
        final Penalty penalty = new Penalty();

        penalty.id = idGenerator.generate();
        penalty.reason = faker.pokemon().name();
        penalty.report_date = faker.date().past(365, TimeUnit.DAYS);
        penalty.dormitory_id = dormitory.id;
        penalty.tenant_id = tenant.id;

        return penalty;
    }
}
