package ru.itmo.sdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.generators.DataGeneratorService;
import ru.itmo.sdb.generators.GeneratedData;
import ru.itmo.sdb.generators.PersonGenerator;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.Teacher;
import ru.itmo.sdb.repositories.DepartmentRepository;
import ru.itmo.sdb.repositories.DirectionRepository;
import ru.itmo.sdb.repositories.DisciplineRepository;
import ru.itmo.sdb.repositories.MarkRepository;
import ru.itmo.sdb.repositories.PersonRepository;
import ru.itmo.sdb.repositories.SpecialtyRepository;
import ru.itmo.sdb.repositories.StudentRepository;
import ru.itmo.sdb.repositories.StudyGroupRepository;
import ru.itmo.sdb.repositories.SubjectRepository;
import ru.itmo.sdb.repositories.TeacherRepository;

import java.util.stream.Collectors;

@Service
public class DummyDataGenerator {
    private final DataGeneratorService dataGeneratorService;
    private final DepartmentRepository departmentRepository;
    private final DirectionRepository directionRepository;
    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final DisciplineRepository disciplineRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PersonRepository personRepository;

    @Autowired
    public DummyDataGenerator(DataGeneratorService dataGeneratorService,
                              DepartmentRepository departmentRepository,
                              DirectionRepository directionRepository,
                              MarkRepository markRepository,
                              StudentRepository studentRepository,
                              StudyGroupRepository studyGroupRepository,
                              SubjectRepository subjectRepository,
                              TeacherRepository teacherRepository,
                              DisciplineRepository disciplineRepository,
                              SpecialtyRepository specialtyRepository,
                              PersonRepository personRepository) {
        this.dataGeneratorService = dataGeneratorService;
        this.departmentRepository = departmentRepository;
        this.directionRepository = directionRepository;
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.disciplineRepository = disciplineRepository;
        this.specialtyRepository = specialtyRepository;
        this.personRepository = personRepository;
    }

    public void generateAndSave() {
        final GeneratedData data = dataGeneratorService.generate(5);

        System.out.println("Departments: " + data.getDepartments().size());
        System.out.println("Teachers: " + data.getTeachers().size());
        System.out.println("Students: " + data.getStudents().size());
        System.out.println("Subjects: " + data.getSubjects().size());
        System.out.println("Study groups: " + data.getStudyGroups().size());
        System.out.println("Specialties: " + data.getSpecialties().size());
        System.out.println("Disciplines: " + data.getDisciplines().size());
        System.out.println("Directions: " + data.getDirections().size());
        System.out.println("Marks: " + data.getMarks().size());
        departmentRepository.saveAll(
                data.getDepartments()
        );

        directionRepository.saveAll(
                data.getDirections()
        );

        disciplineRepository.saveAll(
                data.getDisciplines()
        );

        specialtyRepository.saveAll(
                data.getSpecialties()
        );

        studyGroupRepository.saveAll(
                data.getStudyGroups()
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

        markRepository.saveAll(
                data.getMarks()
        );
    }

}
