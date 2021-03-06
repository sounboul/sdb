package ru.itmo.sdb.mongo.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mongo.core.entities.Dormitory;
import ru.itmo.sdb.mongo.core.entities.Room;
import ru.itmo.sdb.mongo.core.entities.Tenant;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TenantGenerator {
    @Autowired
    private Faker faker;

    @Autowired
    private RandomHelper randomHelper;

    @Autowired
    private IdGenerator idGenerator;

    public Tenant generate(Room room) {
        Tenant tenant = new Tenant();

        tenant.id = idGenerator.generate();
        tenant.payment_amount = faker.number().numberBetween(3000, 9000);
        tenant.payment_type = randomHelper.randomFrom(Arrays.asList("Бюджет", "Контракт"));
        tenant.firstName = faker.name().firstName();
        tenant.lastName = faker.name().lastName();
        tenant.patronymic = faker.name().firstName();
        tenant.birthday = new Date(faker.date().birthday(18, 75).getTime());
        tenant.room_id = room.id;

        return tenant;
    }
}
