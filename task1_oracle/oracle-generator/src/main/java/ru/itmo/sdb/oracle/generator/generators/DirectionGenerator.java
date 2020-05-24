package ru.itmo.sdb.oracle.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.oracle.core.models.Direction;

import java.util.Arrays;
import java.util.List;

@Service
public class DirectionGenerator implements Generator<Direction> {
    private static final List<String> studyForm = Arrays.asList("очная", "заочная");

    private Faker faker;

    private RandomHelper randomHelper;

    @Autowired
    public DirectionGenerator(Faker faker, RandomHelper randomHelper) {
        this.faker = faker;
        this.randomHelper = randomHelper;
    }

    @Override
    public Direction generate() {
        final Direction direction = new Direction();

        direction.setName(faker.educator().course());
        direction.setStudyForm(randomHelper.randomFrom(studyForm));
        direction.setCode(faker.regexify("[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}"));

        return direction;
    }
}
