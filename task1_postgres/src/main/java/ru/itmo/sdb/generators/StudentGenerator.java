package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Specialty;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.University;

@Component
public class StudentGenerator implements Generator<Student> {
    private Faker faker;

    @Autowired
    public StudentGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Student generate() {
        Student student = new Student();
        student.setStudyGroup(faker.regexify("[A-Z][0-9]{4}"));
        return student;
    }

    public Student generate(Person person, University university, Specialty specialty) {
        final Student student = this.generate();

        student.setPerson(person);
        student.setUniversity(university);
        student.setSpecialty(specialty);

        return student;
    }
}
