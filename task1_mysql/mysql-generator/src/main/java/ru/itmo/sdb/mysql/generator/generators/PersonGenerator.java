package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.PersonEntity;

import java.sql.Date;

@Service
public class PersonGenerator implements Generator<PersonEntity> {
    private Faker faker;

    @Autowired
    public PersonGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public PersonEntity generate() {
        final PersonEntity person = new PersonEntity();

        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setBirthday(new Date(faker.date().birthday(18, 75).getTime()));

        return person;
    }
}
