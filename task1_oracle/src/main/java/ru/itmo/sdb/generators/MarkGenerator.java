package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.Discipline;
import ru.itmo.sdb.models.Mark;
import ru.itmo.sdb.models.Specialty;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.Subject;
import ru.itmo.sdb.models.Teacher;

import java.sql.Date;

@Service
public class MarkGenerator implements Generator<Mark> {
    private Faker faker;

    @Autowired
    public MarkGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Mark generate() {
        final Mark mark = new Mark();

        mark.setLetter(faker.letterify("?", true));
        mark.setDigit(faker.number().numberBetween(1, 6));
        mark.setPublishDate(new Date(faker.date().birthday(0, 20).getTime()));

        return mark;
    }

    public Mark generate(Student student, Subject subject) {
        final Mark mark = this.generate();

        mark.setStudent(student);
        mark.setSubject(subject);
        return mark;
    }
}
