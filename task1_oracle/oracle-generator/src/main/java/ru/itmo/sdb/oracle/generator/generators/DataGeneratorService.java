package ru.itmo.sdb.oracle.generator.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.oracle.core.models.Department;
import ru.itmo.sdb.oracle.core.models.Direction;
import ru.itmo.sdb.oracle.core.models.Discipline;
import ru.itmo.sdb.oracle.core.models.Mark;
import ru.itmo.sdb.oracle.core.models.Specialty;
import ru.itmo.sdb.oracle.core.models.Student;
import ru.itmo.sdb.oracle.core.models.StudyGroup;
import ru.itmo.sdb.oracle.core.models.Subject;
import ru.itmo.sdb.oracle.core.models.Teacher;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DataGeneratorService {
    private DepartmentGenerator departmentGenerator;
    private DirectionGenerator directionGenerator;
    private DisciplineGenerator disciplineGenerator;
    private MarkGenerator markGenerator;
    private PersonGenerator personGenerator;
    private SpecialtyGenerator specialtyGenerator;
    private StudentGenerator studentGenerator;
    private StudyGroupGeneration studyGroupGeneration;
    private SubjectGenerator subjectGenerator;
    private TeacherGenerator teacherGenerator;
    private RandomHelper randomHelper;

    @Autowired
    public DataGeneratorService(
            DepartmentGenerator departmentGenerator,
            DirectionGenerator directionGenerator,
            DisciplineGenerator disciplineGenerator,
            MarkGenerator markGenerator,
            PersonGenerator personGenerator,
            SpecialtyGenerator specialtyGenerator,
            StudentGenerator studentGenerator,
            StudyGroupGeneration studyGroupGeneration,
            SubjectGenerator subjectGenerator,
            TeacherGenerator teacherGenerator,
            RandomHelper randomHelper) {
        this.departmentGenerator = departmentGenerator;
        this.directionGenerator = directionGenerator;
        this.disciplineGenerator = disciplineGenerator;
        this.markGenerator = markGenerator;
        this.personGenerator = personGenerator;
        this.specialtyGenerator = specialtyGenerator;
        this.studentGenerator = studentGenerator;
        this.studyGroupGeneration = studyGroupGeneration;
        this.subjectGenerator = subjectGenerator;
        this.teacherGenerator = teacherGenerator;
        this.randomHelper = randomHelper;
    }

    public GeneratedData generate(int defaultSize) {
        List<Direction> directions = IntStream.rangeClosed(1, defaultSize)
                .boxed()
                .map(x -> directionGenerator.generate())
                .collect(Collectors.toList());
        List<Discipline> disciplines = IntStream.rangeClosed(1, defaultSize)
                .boxed()
                .map(x -> disciplineGenerator.generate())
                .collect(Collectors.toList());
        List<Department> departments = IntStream.rangeClosed(1, defaultSize)
                .boxed()
                .map(x -> departmentGenerator.generate())
                .collect(Collectors.toList());
        List<Specialty> specialties = IntStream.rangeClosed(1, directions.size() + departments.size())
                .boxed()
                .map(x -> specialtyGenerator.generate(
                        randomHelper.randomFrom(departments),
                        randomHelper.randomFrom(directions)
                ))
                .collect(Collectors.toList());
        List<StudyGroup> studyGroups = specialties.stream()
                .flatMap(specialty -> IntStream.rangeClosed(1, defaultSize)
                        .boxed()
                        .map(x -> studyGroupGeneration.generate(specialty))
                )
                .collect(Collectors.toList());
        List<Teacher> teachers = departments.stream()
                .flatMap(department -> IntStream.rangeClosed(1, defaultSize)
                        .boxed()
                        .map(x -> teacherGenerator.generate(department, personGenerator.generate()))
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
        List<Student> students = studyGroups.stream()
                .flatMap(studyGroup -> IntStream.rangeClosed(1, defaultSize)
                        .boxed()
                        .map(x -> studentGenerator.generate(personGenerator.generate(), studyGroup))
                )
                .collect(Collectors.toList());
        List<Mark> marks = subjects.stream()
                .flatMap(subject -> students.stream()
                        .filter(student -> student.getStudyGroup().getSpecialty() == subject.getSpecialty())
                        .map(student -> markGenerator.generate(student, subject))
                )
                .collect(Collectors.toList());


        return new GeneratedData(
                marks,
                students,
                subjects,
                teachers,
                studyGroups,
                specialties,
                departments,
                disciplines,
                directions
        );
    }
}
