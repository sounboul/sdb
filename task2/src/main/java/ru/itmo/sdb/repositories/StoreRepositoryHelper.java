package ru.itmo.sdb.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.migrations.conflicts.MigrationMeta;
import ru.itmo.sdb.models.Admonition;
import ru.itmo.sdb.models.Book;
import ru.itmo.sdb.models.BookBorrow;
import ru.itmo.sdb.models.City;
import ru.itmo.sdb.models.Conference;
import ru.itmo.sdb.models.Department;
import ru.itmo.sdb.models.Direction;
import ru.itmo.sdb.models.Discipline;
import ru.itmo.sdb.models.Dormitory;
import ru.itmo.sdb.models.Edition;
import ru.itmo.sdb.models.Identifiable;
import ru.itmo.sdb.models.Mark;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Project;
import ru.itmo.sdb.models.ProjectParticipant;
import ru.itmo.sdb.models.Publication;
import ru.itmo.sdb.models.Report;
import ru.itmo.sdb.models.Room;
import ru.itmo.sdb.models.Specialty;
import ru.itmo.sdb.models.Student;
import ru.itmo.sdb.models.StudyGroup;
import ru.itmo.sdb.models.Subject;
import ru.itmo.sdb.models.Teacher;
import ru.itmo.sdb.models.Tenant;
import ru.itmo.sdb.models.University;
import ru.itmo.sdb.models.Visit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StoreRepositoryHelper {
    private Map<Class, CrudRepository> mapping = new HashMap<>();

    @Autowired
    public StoreRepositoryHelper(AdmonitionRepository admonitionRepository,
                                 BookBorrowRepository bookBorrowRepository,
                                 BookRepository bookRepository,
                                 CityRepository cityRepository,
                                 ConferenceRepository conferenceRepository,
                                 DepartmentRepository departmentRepository,
                                 DirectionRepository directionRepository,
                                 DisciplineRepository disciplineRepository,
                                 DormitoryRepository dormitoryRepository,
                                 EditionRepository editionRepository,
                                 MarkRepository markRepository,
                                 PersonRepository personRepository,
                                 ProjectRepository projectRepository,
                                 ProjectParticipantRepository projectParticipantRepository,
                                 PublicationRepository publicationRepository,
                                 ReportRepository reportRepository,
                                 RoomRepository roomRepository,
                                 SpecialtyRepository specialtyRepository,
                                 StudentRepository studentRepository,
                                 StudyGroupRepository studyGroupRepository,
                                 SubjectRepository subjectRepository,
                                 TeacherRepository teacherRepository,
                                 TenantRepository tenantRepository,
                                 UniversityRepository universityRepository,
                                 VisitRepository visitRepository) {
        mapping.put(Admonition.class, admonitionRepository);
        mapping.put(BookBorrow.class, bookBorrowRepository);
        mapping.put(Book.class, bookRepository);
        mapping.put(City.class, cityRepository);
        mapping.put(Conference.class, conferenceRepository);
        mapping.put(Department.class, departmentRepository);
        mapping.put(Direction.class, directionRepository);
        mapping.put(Discipline.class, disciplineRepository);
        mapping.put(Dormitory.class, dormitoryRepository);
        mapping.put(Edition.class, editionRepository);
        mapping.put(Mark.class, markRepository);
        mapping.put(Person.class, personRepository);
        mapping.put(Project.class, projectRepository);
        mapping.put(ProjectParticipant.class, projectParticipantRepository);
        mapping.put(Publication.class, publicationRepository);
        mapping.put(Report.class, reportRepository);
        mapping.put(Room.class, roomRepository);
        mapping.put(Specialty.class, specialtyRepository);
        mapping.put(Student.class, studentRepository);
        mapping.put(StudyGroup.class, studyGroupRepository);
        mapping.put(Subject.class, subjectRepository);
        mapping.put(Teacher.class, teacherRepository);
        mapping.put(Tenant.class, tenantRepository);
        mapping.put(University.class, universityRepository);
        mapping.put(Visit.class, visitRepository);
    }

    public <T> CrudRepository<T, Long> get(Class<T> clazz) {
        return mapping.get(clazz);
    }

    public <T extends Identifiable> MigrationMeta<T> saveWithReport(Iterable<T> data, Class<T> dataClass) {
        final Iterable<T> savedData = this.get(dataClass)
                .saveAll(data);
        final List<Long> ids = StreamSupport.stream(savedData.spliterator(), false)
                .map(Identifiable::getId)
                .collect(Collectors.toList());

        return new MigrationMeta<T>(dataClass, ids);
    }


}
