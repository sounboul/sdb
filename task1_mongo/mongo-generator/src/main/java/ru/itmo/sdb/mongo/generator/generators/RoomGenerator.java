package ru.itmo.sdb.mongo.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mongo.core.entities.Dormitory;
import ru.itmo.sdb.mongo.core.entities.Room;

import java.util.concurrent.TimeUnit;

@Service
public class RoomGenerator {
    @Autowired
    private Faker faker;

    @Autowired
    private IdGenerator idGenerator;

    public Room generate(Dormitory dormitory) {
        final Room room = new Room();

        room.id = idGenerator.generate();
        room.bugs = faker.bool().bool();
        room.capacity = faker.number().numberBetween(1, 6);
        room.disinfection_date = faker.date().past(365, TimeUnit.DAYS);
        room.dormitory_id = dormitory.id;
        room.number = faker.regexify("[1-9][0-9][0-9][A-D]");

        return room;
    }
}
