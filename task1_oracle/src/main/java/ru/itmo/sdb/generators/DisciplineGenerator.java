package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.Direction;
import ru.itmo.sdb.models.Discipline;

import java.util.Arrays;
import java.util.List;

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
