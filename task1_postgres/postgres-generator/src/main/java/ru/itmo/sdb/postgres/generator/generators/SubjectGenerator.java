package ru.itmo.sdb.postgres.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.postgres.core.models.Discipline;
import ru.itmo.sdb.postgres.core.models.Specialty;
import ru.itmo.sdb.postgres.core.models.Subject;
import ru.itmo.sdb.postgres.core.models.Teacher;

@Service
public class SubjectGenerator implements Generator<Subject> {
    private Faker faker;

    @Autowired
    public SubjectGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Subject generate() {
        final Subject subject = new Subject();

        subject.setMaxPoints(faker.number().numberBetween(60, 100));
        subject.setTerm(faker.number().randomDigitNotZero());
        subject.setYear(faker.number().numberBetween(2000, 2020));
        return subject;
    }

    public Subject generate(Specialty specialty, Discipline discipline, Teacher teacher) {
        final Subject subject = this.generate();

        subject.setSpecialty(specialty);
        subject.setDiscipline(discipline);
        subject.setTeacher(teacher);

        return subject;
    }
}
