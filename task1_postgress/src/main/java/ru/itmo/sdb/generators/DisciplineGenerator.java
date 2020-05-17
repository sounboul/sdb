package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.Discipline;

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
        discipline.setControlForm(faker.pokemon().name());
        discipline.setLabs(faker.number().randomDigitNotZero());
        discipline.setLectures(faker.number().randomDigitNotZero());
        discipline.setPractices(faker.number().randomDigitNotZero());

        return discipline;
    }
}
