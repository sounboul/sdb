package ru.itmo.sdb.postgres.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.postgres.core.models.University;

@Service
public class UniversityGenerator implements Generator<University> {
    private final Faker faker;

    @Autowired
    public UniversityGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public University generate() {
        University university = new University();

        university.setName(faker.educator().university());

        return university;
    }
}
