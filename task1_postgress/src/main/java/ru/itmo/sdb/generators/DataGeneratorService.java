package ru.itmo.sdb.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.Discipline;
import ru.itmo.sdb.models.Specialty;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.Subject;
import ru.itmo.sdb.models.Teacher;
import ru.itmo.sdb.models.University;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DataGeneratorService {
    private DisciplineGenerator disciplineGenerator;
    private PersonGenerator personGenerator;
    private SpecialtyGenerator specialtyGenerator;
    private StudentGenerator studentGenerator;
    private SubjectGenerator subjectGenerator;
    private TeacherGenerator teacherGenerator;
    private UniversityGenerator universityGenerator;
    private RandomHelper randomHelper;

    @Autowired
    public DataGeneratorService(
            DisciplineGenerator disciplineGenerator,
            PersonGenerator personGenerator,
            SpecialtyGenerator specialtyGenerator,
            StudentGenerator studentGenerator,
            SubjectGenerator subjectGenerator,
            TeacherGenerator teacherGenerator,
            UniversityGenerator universityGenerator,
            RandomHelper randomHelper) {
        this.disciplineGenerator = disciplineGenerator;
        this.personGenerator = personGenerator;
        this.specialtyGenerator = specialtyGenerator;
        this.studentGenerator = studentGenerator;
        this.subjectGenerator = subjectGenerator;
        this.teacherGenerator = teacherGenerator;
        this.universityGenerator = universityGenerator;
        this.randomHelper = randomHelper;
    }

    public GeneratedData generate(int defaultSize) {
        List<University> universities = IntStream.rangeClosed(1, defaultSize)
                .boxed()
                .map(x -> universityGenerator.generate())
                .collect(Collectors.toList());
        List<Discipline> disciplines = IntStream.rangeClosed(1, defaultSize)
                .boxed()
                .map(x -> disciplineGenerator.generate())
                .collect(Collectors.toList());
        List<Specialty> specialties = universities.stream()
                .flatMap(university -> IntStream.rangeClosed(1, defaultSize)
                        .boxed()
                        .map(x -> specialtyGenerator.generate(university))
                )
                .collect(Collectors.toList());
        List<Teacher> teachers = universities.stream()
                .flatMap(university -> IntStream.rangeClosed(1, defaultSize * 2)
                        .boxed()
                        .map(x -> teacherGenerator.generate(university, personGenerator.generate()))
                )
                .collect(Collectors.toList());
        List<Subject> subjects = specialties.stream()
                .flatMap(specialty -> IntStream.rangeClosed(1, defaultSize)
                        .boxed()
                        .map(x -> subjectGenerator.generate(
                                specialty,
                                randomHelper.randomFrom(disciplines),
                                randomHelper.randomFrom(teachers)
                        ))
                )
                .collect(Collectors.toList());
        List<Student> students = universities.stream()
                .flatMap(university -> IntStream.rangeClosed(1, defaultSize)
                        .boxed()
                        .map(x -> studentGenerator.generate(
                                personGenerator.generate(),
                                university,
                                randomHelper.randomFrom(specialties)))
                )
                .collect(Collectors.toList());


        return new GeneratedData(
                students,
                subjects,
                teachers,
                specialties,
                disciplines,
                universities
        );
    }
}
