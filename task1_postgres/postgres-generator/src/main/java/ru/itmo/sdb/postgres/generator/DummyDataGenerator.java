package ru.itmo.sdb.postgres.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.postgres.generator.generators.*;
import ru.itmo.sdb.postgres.core.models.Student;
import ru.itmo.sdb.postgres.core.models.Teacher;
import ru.itmo.sdb.postgres.core.repositories.*;

import java.util.stream.Collectors;

@Service
public class DummyDataGenerator {
    private final DataGeneratorService dataGeneratorService;
    private final PostgresStudentRepository studentRepository;
    private final PostgresSubjectRepository subjectRepository;
    private final PostgresTeacherRepository teacherRepository;
    private final PostgresDisciplineRepository disciplineRepository;
    private final PostgresSpecialtyRepository specialtyRepository;
    private final PostgresPersonRepository personRepository;
    private final PostgresUniversityRepository universityRepository;

    @Autowired
    public DummyDataGenerator(
            DataGeneratorService dataGeneratorService,
            PostgresStudentRepository studentRepository,
            PostgresSubjectRepository subjectRepository,
            PostgresTeacherRepository teacherRepository,
            PostgresDisciplineRepository disciplineRepository,
            PostgresSpecialtyRepository specialtyRepository,
            PostgresPersonRepository personRepository,
            PostgresUniversityRepository universityRepository) {
        this.dataGeneratorService = dataGeneratorService;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.disciplineRepository = disciplineRepository;
        this.specialtyRepository = specialtyRepository;
        this.personRepository = personRepository;
        this.universityRepository = universityRepository;
    }

    public void generateAndSave() {
        final GeneratedData data = dataGeneratorService.generate(3);

        System.out.println("Teachers: " + data.getTeachers().size());
        System.out.println("Students: " + data.getStudents().size());
        System.out.println("Subjects: " + data.getSubjects().size());
        System.out.println("Universities: " + data.getUniversities().size());
        System.out.println("Specialties: " + data.getSpecialties().size());
        System.out.println("Disciplines: " + data.getDisciplines().size());

        universityRepository.saveAll(
                data.getUniversities()
        );

        disciplineRepository.saveAll(
                data.getDisciplines()
        );

        specialtyRepository.saveAll(
                data.getSpecialties()
        );

        personRepository.saveAll(
                data.getTeachers().stream().map(Teacher::getPerson).collect(Collectors.toList())
        );

        personRepository.saveAll(
                data.getStudents().stream().map(Student::getPerson).collect(Collectors.toList())
        );

        teacherRepository.saveAll(
                data.getTeachers()
        );

        studentRepository.saveAll(
                data.getStudents()
        );

        subjectRepository.saveAll(
                data.getSubjects()
        );
    }

}
