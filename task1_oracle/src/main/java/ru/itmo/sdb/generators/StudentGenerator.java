package ru.itmo.sdb.generators;

import org.springframework.stereotype.Component;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.StudyGroup;

@Component
public class StudentGenerator implements Generator<Student> {
    @Override
    public Student generate() {
        return new Student();
    }

    public Student generate(Person person, StudyGroup studyGroup) {
        final Student student = this.generate();

        student.setPerson(person);
        student.setStudyGroup(studyGroup);
        return student;
    }
}
