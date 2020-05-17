package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.Department;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Teacher;

import java.util.Date;

@Service
public class TeacherGenerator implements Generator<Teacher> {
    private Faker faker;

    @Autowired
    public TeacherGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Teacher generate() {
        Teacher teacher = new Teacher();

        Date hiringDate = faker.date().birthday(1, 20);
        java.sql.Date dismissalDate = faker.bool().bool() ? null : new java.sql.Date(faker.date().between(hiringDate, new Date()).getTime());
        teacher.setHiringDate(new java.sql.Date(hiringDate.getTime()));
        teacher.setDismissalDate(dismissalDate);
        teacher.setPosition(faker.job().seniority());

        return teacher;
    }

    public Teacher generate(Department department, Person person) {
        Teacher teacher = this.generate();

        teacher.setDepartment(department);
        teacher.setPerson(person);

        return teacher;
    }
}
