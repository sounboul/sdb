package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.Department;
import ru.itmo.sdb.models.Direction;
import ru.itmo.sdb.models.Discipline;
import ru.itmo.sdb.models.Specialty;

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
        specialty.setQualification(faker.gameOfThrones().dragon());

        return specialty;
    }

    public Specialty generate(Department department, Direction direction) {
        final Specialty specialty = this.generate();

        specialty.setDepartment(department);
        specialty.setDirection(direction);

        return specialty;
    }
}
