package ru.itmo.sdb.oracle.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.oracle.generator.generators.DataGeneratorService;
import ru.itmo.sdb.oracle.generator.generators.GeneratedData;
import ru.itmo.sdb.oracle.core.models.Student;
import ru.itmo.sdb.oracle.core.models.Teacher;
import ru.itmo.sdb.oracle.core.repositories.OracleDepartmentRepository;
import ru.itmo.sdb.oracle.core.repositories.OracleDirectionRepository;
import ru.itmo.sdb.oracle.core.repositories.OracleDisciplineRepository;
import ru.itmo.sdb.oracle.core.repositories.OracleMarkRepository;
import ru.itmo.sdb.oracle.core.repositories.OraclePersonRepository;
import ru.itmo.sdb.oracle.core.repositories.OracleSpecialtyRepository;
import ru.itmo.sdb.oracle.core.repositories.OracleStudentRepository;
import ru.itmo.sdb.oracle.core.repositories.OracleStudyGroupRepository;
import ru.itmo.sdb.oracle.core.repositories.OracleSubjectRepository;
import ru.itmo.sdb.oracle.core.repositories.OracleTeacherRepository;

import java.util.stream.Collectors;

@Service
public class DummyDataGenerator {
    private final DataGeneratorService dataGeneratorService;
    private final OracleDepartmentRepository departmentRepository;
    private final OracleDirectionRepository directionRepository;
    private final OracleMarkRepository markRepository;
    private final OracleStudentRepository studentRepository;
    private final OracleStudyGroupRepository studyGroupRepository;
    private final OracleSubjectRepository subjectRepository;
    private final OracleTeacherRepository teacherRepository;
    private final OracleDisciplineRepository disciplineRepository;
    private final OracleSpecialtyRepository specialtyRepository;
    private final OraclePersonRepository personRepository;

    @Autowired
    public DummyDataGenerator(DataGeneratorService dataGeneratorService,
                              OracleDepartmentRepository departmentRepository,
                              OracleDirectionRepository directionRepository,
                              OracleMarkRepository markRepository,
                              OracleStudentRepository studentRepository,
                              OracleStudyGroupRepository studyGroupRepository,
                              OracleSubjectRepository subjectRepository,
                              OracleTeacherRepository teacherRepository,
                              OracleDisciplineRepository disciplineRepository,
                              OracleSpecialtyRepository specialtyRepository,
                              OraclePersonRepository personRepository) {
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
        final GeneratedData data = dataGeneratorService.generate(3);

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
