package ru.itmo.sdb.migrations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.migrations.conflicts.meta.MigrationReport;
import ru.itmo.sdb.migrations.conflicts.meta.Source;
import ru.itmo.sdb.models.Discipline;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Specialty;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.StudyGroup;
import ru.itmo.sdb.models.Subject;
import ru.itmo.sdb.models.Teacher;
import ru.itmo.sdb.models.University;
import ru.itmo.sdb.postgres.core.repositories.PostgresDisciplineRepository;
import ru.itmo.sdb.postgres.core.repositories.PostgresPersonRepository;
import ru.itmo.sdb.postgres.core.repositories.PostgresSpecialtyRepository;
import ru.itmo.sdb.postgres.core.repositories.PostgresStudentRepository;
import ru.itmo.sdb.postgres.core.repositories.PostgresSubjectRepository;
import ru.itmo.sdb.postgres.core.repositories.PostgresTeacherRepository;
import ru.itmo.sdb.postgres.core.repositories.PostgresUniversityRepository;
import ru.itmo.sdb.repositories.StoreRepositoryHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostgresMigration {
    private PostgresStudentRepository postgresStudentRepository;
    private PostgresSubjectRepository postgresSubjectRepository;
    private PostgresTeacherRepository postgresTeacherRepository;
    private PostgresDisciplineRepository postgresDisciplineRepository;
    private PostgresSpecialtyRepository postgresSpecialtyRepository;
    private PostgresPersonRepository postgresPersonRepository;
    private PostgresUniversityRepository postgresUniversityRepository;

    private StoreRepositoryHelper repositoryHelper;

    @Autowired
    public PostgresMigration(
            PostgresStudentRepository studentRepository,
            PostgresSubjectRepository subjectRepository,
            PostgresTeacherRepository teacherRepository,
            PostgresDisciplineRepository disciplineRepository,
            PostgresSpecialtyRepository specialtyRepository,
            PostgresPersonRepository personRepository,
            PostgresUniversityRepository universityRepository,
            StoreRepositoryHelper repositoryHelper) {
        this.postgresStudentRepository = studentRepository;
        this.postgresSubjectRepository = subjectRepository;
        this.postgresTeacherRepository = teacherRepository;
        this.postgresDisciplineRepository = disciplineRepository;
        this.postgresSpecialtyRepository = specialtyRepository;
        this.postgresPersonRepository = personRepository;
        this.postgresUniversityRepository = universityRepository;
        this.repositoryHelper = repositoryHelper;
    }

    public MigrationReport migrate() {
        Map<Long, University> universities = new HashMap<>();
        postgresUniversityRepository.findAll()
                .forEach(x -> {
                    University university = new University();
                    university.setName(x.getName());
                    universities.put(x.getId(), university);
                });

        Map<Long, Discipline> disciplines = new HashMap<>();
        postgresDisciplineRepository.findAll()
                .forEach(x -> {
                    Discipline discipline = new Discipline();

                    discipline.setName(x.getName());
                    discipline.setPractices(x.getPractices());
                    discipline.setLectures(x.getLectures());
                    discipline.setLabs(x.getLabs());
                    discipline.setControlForm(x.getControlForm());

                    disciplines.put(x.getId(), discipline);
                });

        Map<Long, Person> people = new HashMap<>();
        postgresPersonRepository.findAll()
                .forEach(x -> {
                    Person person = new Person();

                    person.setFirstName(x.getFirstName());
                    person.setLastName(x.getLastName());
                    person.setPatronymic(x.getPatronymic());
                    person.setBirthDate(x.getBirthDate());

                    people.put(x.getId(), person);
                });

        Map<Long, Specialty> specialties = new HashMap<>();
        postgresSpecialtyRepository.findAll()
                .forEach(x -> {
                    Specialty specialty = new Specialty();

                    specialty.setName(x.getName());
                    specialty.setUniversity(universities.get(x.getUniversity()
                                                                     .getId()));

                    specialties.put(x.getId(), specialty);
                });

        Map<Long, Student> students = new HashMap<>();
        List<StudyGroup> studyGroups = new ArrayList<>();
        postgresStudentRepository.findAll()
                .forEach(x -> {
                    Student student = new Student();
                    student.setPerson(people.get(x.getPerson()
                                                         .getId()));

                    StudyGroup studyGroup = new StudyGroup();
                    studyGroup.setName(x.getStudyGroup());
                    studyGroup.setSpecialty(specialties.get(x.getSpecialty()
                                                                    .getId()));

                    student.setStudyGroup(studyGroup);
                    studyGroups.add(studyGroup);
                    students.put(x.getId(), student);
                });

        Map<Long, Teacher> teachers = new HashMap<>();
        postgresTeacherRepository.findAll()
                .forEach(x -> {
                    Teacher teacher = new Teacher();

                    teacher.setPerson(people.get(x.getPerson()
                                                         .getId()));
                    teachers.put(x.getId(), teacher);
                });

        Map<Long, Subject> subjects = new HashMap<>();
        postgresSubjectRepository.findAll()
                .forEach(x -> {
                    Subject subject = new Subject();

                    subject.setTeacher(teachers.get(x.getTeacher()
                                                            .getId()));
                    subject.setDiscipline(disciplines.get(x.getDiscipline()
                                                                  .getId()));
                    subject.setSpecialty(specialties.get(x.getSpecialty()
                                                                 .getId()));
                    subject.setYear(x.getYear());
                    subject.setMaxPoints(x.getMaxPoints());
                    subject.setTerm(x.getTerm());

                    subjects.put(x.getId(), subject);
                });


        return new MigrationReport(Source.POSTGRES, Arrays.asList(
                repositoryHelper.saveWithReport(universities.values(), University.class),
                repositoryHelper.saveWithReport(disciplines.values(), Discipline.class),
                repositoryHelper.saveWithReport(people.values(), Person.class),
                repositoryHelper.saveWithReport(specialties.values(), Specialty.class),
                repositoryHelper.saveWithReport(studyGroups, StudyGroup.class),
                repositoryHelper.saveWithReport(students.values(), Student.class),
                repositoryHelper.saveWithReport(teachers.values(), Teacher.class),
                repositoryHelper.saveWithReport(subjects.values(), Subject.class)
        ));
    }
}
