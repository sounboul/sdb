package ru.itmo.sdb.postgres.generator.generators;

import org.springframework.stereotype.Service;
import ru.itmo.sdb.postgres.core.models.Person;
import ru.itmo.sdb.postgres.core.models.Teacher;
import ru.itmo.sdb.postgres.core.models.University;

@Service
public class TeacherGenerator implements Generator<Teacher> {
    @Override
    public Teacher generate() {
        return new Teacher();
    }

    public Teacher generate(University university, Person person) {
        Teacher teacher = this.generate();

        teacher.setUniversity(university);
        teacher.setPerson(person);

        return teacher;
    }
}
