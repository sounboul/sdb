package ru.itmo.sdb.oracle.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.oracle.models.Mark;
import ru.itmo.sdb.oracle.models.Student;
import ru.itmo.sdb.oracle.models.Subject;

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
