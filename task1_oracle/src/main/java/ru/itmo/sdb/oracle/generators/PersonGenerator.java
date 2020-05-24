package ru.itmo.sdb.oracle.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.oracle.models.Person;

import java.sql.Date;

@Service
public class PersonGenerator implements Generator<Person> {
    @Autowired
    private Faker faker;

    @Override
    public Person generate() {
        final Person person = new Person();

        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setBirthDate(new Date(faker.date().birthday(18, 75).getTime()));
        person.setPatronymic(faker.name().firstName()); // this is fine

        return person;
    }
}
