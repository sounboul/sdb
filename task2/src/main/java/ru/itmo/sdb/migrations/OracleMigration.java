package ru.itmo.sdb.migrations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.migrations.conflicts.meta.MigrationReport;
import ru.itmo.sdb.migrations.conflicts.meta.Source;
import ru.itmo.sdb.models.Department;
import ru.itmo.sdb.models.Direction;
import ru.itmo.sdb.models.Discipline;
import ru.itmo.sdb.models.Mark;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Specialty;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.StudyGroup;
import ru.itmo.sdb.models.Subject;
import ru.itmo.sdb.models.Teacher;
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
import ru.itmo.sdb.repositories.StoreRepositoryHelper;

import java.util.*;

@Service
public class OracleMigration {
    private OracleDepartmentRepository oracleDepartmentRepository;
    private OracleDirectionRepository oracleDirectionRepository;
    private OracleMarkRepository oracleMarkRepository;
    private OracleStudentRepository oracleStudentRepository;
    private OracleStudyGroupRepository oracleStudyGroupRepository;
    private OracleSubjectRepository oracleSubjectRepository;
    private OracleTeacherRepository oracleTeacherRepository;
    private OracleDisciplineRepository oracleDisciplineRepository;
    private OracleSpecialtyRepository oracleSpecialtyRepository;
    private OraclePersonRepository oraclePersonRepository;

    private StoreRepositoryHelper repositoryHelper;

    @Autowired
    public OracleMigration(OracleDepartmentRepository departmentRepository,
                           OracleDirectionRepository directionRepository,
                           OracleMarkRepository markRepository,
                           OracleStudentRepository studentRepository,
                           OracleStudyGroupRepository studyGroupRepository,
                           OracleSubjectRepository subjectRepository,
                           OracleTeacherRepository teacherRepository,
                           OracleDisciplineRepository disciplineRepository,
                           OracleSpecialtyRepository specialtyRepository,
                           OraclePersonRepository personRepository,
                           StoreRepositoryHelper repositoryHelper) {
        this.oracleDepartmentRepository = departmentRepository;
        this.oracleDirectionRepository = directionRepository;
        this.oracleMarkRepository = markRepository;
        this.oracleStudentRepository = studentRepository;
        this.oracleStudyGroupRepository = studyGroupRepository;
        this.oracleSubjectRepository = subjectRepository;
        this.oracleTeacherRepository = teacherRepository;
        this.oracleDisciplineRepository = disciplineRepository;
        this.oracleSpecialtyRepository = specialtyRepository;
        this.oraclePersonRepository = personRepository;
        this.repositoryHelper = repositoryHelper;
    }

    public MigrationReport migrate() {
        Map<Long, Department> departments = new HashMap<>();
        oracleDepartmentRepository.findAll().forEach(x -> {
            Department department = new Department();
            department.setType(x.getType());
            department.setName(x.getName());
            departments.put(x.getId(), department);
        });


        Map<Long, Direction> directions = new HashMap<>();
        oracleDirectionRepository.findAll().forEach(x -> {
            Direction direction = new Direction();

            direction.setCode(x.getCode());
            direction.setStudyForm(x.getStudyForm());
            direction.setName(x.getName());

            directions.put(x.getId(), direction);
        });

        Map<Long, Discipline> disciplines = new HashMap<>();
        oracleDisciplineRepository.findAll().forEach(x -> {
            Discipline discipline = new Discipline();

            discipline.setName(x.getName());

            disciplines.put(x.getId(), discipline);
        });

        Map<Long, Person> people = new HashMap<>();
        oraclePersonRepository.findAll().forEach(x -> {
            Person person = new Person();

            person.setFirstName(x.getFirstName());
            person.setLastName(x.getLastName());
            person.setPatronymic(x.getPatronymic());
            person.setBirthDate(x.getBirthDate());

            people.put(x.getId(), person);
        });

        Map<Long, Specialty> specialties = new HashMap<>();
        oracleSpecialtyRepository.findAll().forEach(x -> {
            Specialty specialty = new Specialty();

            specialty.setDepartment(departments.get(x.getDepartment().getId()));
            specialty.setDirection(directions.get(x.getDirection().getId()));

            specialty.setName(x.getName());
            specialty.setQualification(x.getQualification());

            specialties.put(x.getId(), specialty);
        });

        Map<Long, StudyGroup> studyGroups = new HashMap<>();
        oracleStudyGroupRepository.findAll().forEach(x -> {
            StudyGroup studyGroup = new StudyGroup();

            studyGroup.setSpecialty(specialties.get(x.getSpecialty().getId()));
            studyGroup.setStartYear(x.getStartYear());
            studyGroup.setEndYear(x.getEndYear());
            studyGroup.setCourse(x.getCourse());
            studyGroup.setName(x.getName());

            studyGroups.put(x.getId(), studyGroup);
        });

        Map<Long, Student> students = new HashMap<>();
        oracleStudentRepository.findAll().forEach(x -> {
            Student student = new Student();
            student.setPerson(people.get(x.getPerson().getId()));
            student.setStudyGroup(studyGroups.get(x.getStudyGroup().getId()));
            students.put(x.getId(), student);
        });

        Map<Long, Teacher> teachers = new HashMap<>();
        oracleTeacherRepository.findAll().forEach(x -> {
            Teacher teacher = new Teacher();

            teacher.setPerson(people.get(x.getPerson().getId()));
            teacher.setDepartment(departments.get(x.getDepartment().getId()));
            teacher.setPosition(x.getPosition());
            teacher.setDismissalDate(x.getDismissalDate());
            teacher.setHiringDate(x.getHiringDate());

            teachers.put(x.getId(), teacher);
        });

        Map<Long, Subject> subjects = new HashMap<>();
        oracleSubjectRepository.findAll().forEach(x -> {
            Subject subject = new Subject();

            subject.setTeacher(teachers.get(x.getTeacher().getId()));
            subject.setDiscipline(disciplines.get(x.getDiscipline().getId()));
            subject.setSpecialty(specialties.get(x.getSpecialty().getId()));
            subject.setYear(x.getYear());
            subject.setMaxPoints(x.getMaxPoints());
            subject.setTerm(x.getTerm());

            subjects.put(x.getId(), subject);
        });

        List<Mark> marks = new ArrayList<>();
        oracleMarkRepository.findAll().forEach(x -> {
            Mark mark = new Mark();
            mark.setSubject(subjects.get(x.getSubject().getId()));
            mark.setStudent(students.get(x.getStudent().getId()));
            mark.setPublishDate(x.getPublishDate());
            mark.setDigit(x.getDigit());
            mark.setLetter(x.getLetter());

            marks.add(mark);
        });

        return new MigrationReport(Source.ORACLE, Arrays.asList(
                repositoryHelper.saveWithReport(departments.values(), Department.class),
                repositoryHelper.saveWithReport(disciplines.values(), Discipline.class),
                repositoryHelper.saveWithReport(directions.values(), Direction.class),
                repositoryHelper.saveWithReport(people.values(), Person.class),
                repositoryHelper.saveWithReport(specialties.values(), Specialty.class),
                repositoryHelper.saveWithReport(studyGroups.values(), StudyGroup.class),
                repositoryHelper.saveWithReport(students.values(), Student.class),
                repositoryHelper.saveWithReport(teachers.values(), Teacher.class),
                repositoryHelper.saveWithReport(subjects.values(), Subject.class),
                repositoryHelper.saveWithReport(marks, Mark.class)
        ));
    }
}
