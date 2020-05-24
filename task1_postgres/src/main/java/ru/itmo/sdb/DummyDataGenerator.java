package ru.itmo.sdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.generators.DataGeneratorService;
import ru.itmo.sdb.generators.GeneratedData;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.Teacher;
import ru.itmo.sdb.repositories.DisciplineRepository;
import ru.itmo.sdb.repositories.PersonRepository;
import ru.itmo.sdb.repositories.SpecialtyRepository;
import ru.itmo.sdb.repositories.StudentRepository;
import ru.itmo.sdb.repositories.SubjectRepository;
import ru.itmo.sdb.repositories.TeacherRepository;
import ru.itmo.sdb.repositories.UniversityRepository;

import java.util.stream.Collectors;

@Service
public class DummyDataGenerator {
    private final DataGeneratorService dataGeneratorService;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final DisciplineRepository disciplineRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PersonRepository personRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public DummyDataGenerator(
            DataGeneratorService dataGeneratorService,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            TeacherRepository teacherRepository,
            DisciplineRepository disciplineRepository,
            SpecialtyRepository specialtyRepository,
            PersonRepository personRepository,
            UniversityRepository universityRepository) {
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
        final GeneratedData data = dataGeneratorService.generate(5);

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
