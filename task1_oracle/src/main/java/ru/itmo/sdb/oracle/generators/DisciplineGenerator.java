package ru.itmo.sdb.oracle.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.oracle.models.Discipline;

@Service
public class DisciplineGenerator implements Generator<Discipline> {
    private Faker faker;

    @Autowired
    public DisciplineGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Discipline generate() {
        final Discipline discipline = new Discipline();

        discipline.setName(faker.gameOfThrones().house());

        return discipline;
    }
}
