package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.Specialty;
import ru.itmo.sdb.models.University;

@Service
public class SpecialtyGenerator implements Generator<Specialty> {
    private Faker faker;

    @Autowired
    public SpecialtyGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Specialty generate() {
        final Specialty specialty = new Specialty();

        specialty.setName(faker.gameOfThrones().city());
        specialty.setCode(faker.gameOfThrones().dragon());
        specialty.setNewStandard(faker.bool().bool());

        return specialty;
    }

    public Specialty generate(University university) {
        final Specialty specialty = this.generate();

        specialty.setUniversity(university);

        return specialty;
    }
}
