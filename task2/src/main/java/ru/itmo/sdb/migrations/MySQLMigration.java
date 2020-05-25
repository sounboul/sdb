package ru.itmo.sdb.migrations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.migrations.conflicts.meta.MigrationReport;
import ru.itmo.sdb.migrations.conflicts.meta.Source;
import ru.itmo.sdb.models.Book;
import ru.itmo.sdb.models.BookBorrow;
import ru.itmo.sdb.models.City;
import ru.itmo.sdb.models.Conference;
import ru.itmo.sdb.models.Edition;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Project;
import ru.itmo.sdb.models.ProjectParticipant;
import ru.itmo.sdb.models.Publication;
import ru.itmo.sdb.models.Report;
import ru.itmo.sdb.mysql.core.repositories.MySQLBookBorrowRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLBookRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLCityRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLConferenceRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLEditionRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLPersonRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLProjectParticipantRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLProjectRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLPublicationAuthorsRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLPublicationRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLReportAuthorRepository;
import ru.itmo.sdb.mysql.core.repositories.MySQLReportRepository;
import ru.itmo.sdb.repositories.StoreRepositoryHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MySQLMigration {
    private MySQLPersonRepository mysqlPersonRepository;
    private MySQLCityRepository mysqlCityRepository;
    private MySQLBookRepository mysqlBookRepository;
    private MySQLBookBorrowRepository mysqlBookBorrowRepository;
    private MySQLConferenceRepository mysqlConferenceRepository;
    private MySQLEditionRepository mysqlEditionRepository;
    private MySQLProjectRepository mysqlProjectRepository;
    private MySQLProjectParticipantRepository mysqlProjectParticipantRepository;
    private MySQLPublicationRepository mysqlPublicationRepository;
    private MySQLPublicationAuthorsRepository mysqlPublicationAuthorsRepository;
    private MySQLReportRepository mysqlReportRepository;
    private MySQLReportAuthorRepository mysqlReportAuthorRepository;


    private StoreRepositoryHelper repositoryHelper;

    @Autowired
    public MySQLMigration(
            MySQLPersonRepository personRepository,
            MySQLCityRepository mysqlCityRepository,
            MySQLBookRepository mysqlBookRepository,
            MySQLBookBorrowRepository mysqlBookBorrowRepository,
            MySQLConferenceRepository mysqlConferenceRepository,
            MySQLEditionRepository mysqlEditionRepository,
            MySQLProjectRepository mysqlProjectRepository,
            MySQLProjectParticipantRepository mysqlProjectParticipantRepository,
            MySQLPublicationRepository mysqlPublicationRepository,
            MySQLPublicationAuthorsRepository mysqlPublicationAuthorsRepository,
            MySQLReportRepository mysqlReportRepository,
            MySQLReportAuthorRepository mysqlReportAuthorRepository,
            StoreRepositoryHelper repositoryHelper) {
        this.mysqlPersonRepository = personRepository;
        this.mysqlCityRepository = mysqlCityRepository;
        this.mysqlBookRepository = mysqlBookRepository;
        this.mysqlBookBorrowRepository = mysqlBookBorrowRepository;
        this.mysqlConferenceRepository = mysqlConferenceRepository;
        this.mysqlEditionRepository = mysqlEditionRepository;
        this.mysqlProjectRepository = mysqlProjectRepository;
        this.mysqlProjectParticipantRepository = mysqlProjectParticipantRepository;
        this.mysqlPublicationRepository = mysqlPublicationRepository;
        this.mysqlPublicationAuthorsRepository = mysqlPublicationAuthorsRepository;
        this.mysqlReportRepository = mysqlReportRepository;
        this.mysqlReportAuthorRepository = mysqlReportAuthorRepository;
        this.repositoryHelper = repositoryHelper;
    }

    public MigrationReport migrate() {
        Map<Long, City> cities = new HashMap<>();
        mysqlCityRepository.findAll()
                .forEach(x -> {
                    City city = new City();
                    city.setName(x.getName());

                    cities.put(x.getId(), city);
                });

        Map<Long, Person> people = new HashMap<>();
        mysqlPersonRepository.findAll()
                .forEach(x -> {
                    Person person = new Person();

                    person.setFirstName(x.getFirstName());
                    person.setLastName(x.getLastName());
                    person.setPatronymic(x.getPatronymic());
                    person.setCity(cities.get(x.getFirstName().hashCode() % cities.size())); // ha-ha
                    person.setBirthDate(x.getBirthday());

                    people.put(x.getId(), person);
                });

        Map<Long, Conference> conferences = new HashMap<>();
        mysqlConferenceRepository.findAll()
                .forEach(x -> {
                    Conference conference = new Conference();

                    conference.setName(x.getName());
                    conference.setCity(cities.get(x.getCityId()));
                    conference.setStartDate(x.getStartDate());
                    conference.setFinishDate(x.getFinishDate());

                    conferences.put(x.getId(), conference);
                });

        Map<Long, Edition> editions = new HashMap<>();
        mysqlEditionRepository.findAll()
                .forEach(x -> {
                    Edition edition = new Edition();
                    edition.setCity(cities.get(x.getCityId()));
                    edition.setLanguage(x.getLanguage());
                    edition.setName(x.getName());
                    edition.setType(x.getType());
                    edition.setVolume(x.getVolume());
                    edition.setYear(x.getYear());

                    editions.put(x.getId(), edition);
                });

        Map<Long, Book> books = new HashMap<>();
        mysqlBookRepository.findAll()
                .forEach(x -> {
                    Book book = new Book();

                    book.setAuthor(x.getAuthor());
                    book.setEdition(editions.get(x.getEditionId()));
                    book.setIsbn(x.getIsbn());
                    book.setName(x.getName());

                    books.put(x.getId(), book);
                });

        Map<Long, BookBorrow> bookBorrows = new HashMap<>();
        mysqlBookBorrowRepository.findAll()
                .forEach(x -> {
                    BookBorrow bookBorrow = new BookBorrow();
                    bookBorrow.setBook(books.get(x.getBookId()));
                    bookBorrow.setPerson(people.get(x.getOwnerId()));
                    bookBorrow.setName(x.getName());
                    bookBorrow.setBorrowDate(x.getBorrowDate());
                    bookBorrow.setReturnDate(x.getReturnDate());
                    bookBorrows.put(x.getId(), bookBorrow);
                });

        Map<Long, Project> projects = new HashMap<>();
        mysqlProjectRepository.findAll()
                .forEach(x -> {
                    Project project = new Project();
                    project.setName(x.getName());
                    project.setStartDate(x.getStartDate());
                    project.setFinishDate(x.getFinishDate());

                    projects.put(x.getId(), project);
                });

        Map<Long, ProjectParticipant> projectParticipants = new HashMap<>();
        mysqlProjectParticipantRepository.findAll()
                .forEach(x -> {
                    ProjectParticipant projectParticipant = new ProjectParticipant();

                    projectParticipant.setPerson(people.get(x.getPersonId()));
                    projectParticipant.setProject(projects.get(x.getProjectId()));
                    projectParticipant.setRole(x.getRole());
                    projectParticipant.setStartDate(x.getStartDate());
                    projectParticipant.setFinishDate(x.getFinishDate());

                    projectParticipants.put(x.getId(), projectParticipant);
                });

        Map<Long, Publication> publications = new HashMap<>();
        mysqlPublicationRepository.findAll()
                .forEach(x -> {
                    Publication publication = new Publication();

                    publication.setAuthors(
                            StreamSupport.stream(
                                    mysqlPublicationAuthorsRepository.findByPublicationId(x.getId())
                                            .spliterator(), false)
                                    .map(author -> people.get(author.getPersonId()))
                                    .collect(Collectors.toList())
                    );
                    publication.setCitationIndex(x.getCitationIndex());
                    publication.setEdition(editions.get(x.getEditionId()));
                    publication.setName(x.getName());
                    publication.setPublicationDate(x.getPublicationDate());

                    publications.put(x.getId(), publication);
                });

        Map<Long, Report> reports = new HashMap<>();
        mysqlReportRepository.findAll()
                .forEach(x -> {
                    Report report = new Report();
                    report.setAuthors(
                            StreamSupport.stream(
                                    mysqlReportAuthorRepository.findByReportId(x.getId())
                                            .spliterator(), false)
                                    .map(author -> people.get(author.getPersonId()))
                                    .collect(Collectors.toList())
                    );

                    report.setConference(conferences.get(x.getConferenceId()));
                    report.setName(x.getName());

                    reports.put(x.getId(), report);
                });

        return new MigrationReport(Source.MYSQL, Arrays.asList(
                repositoryHelper.saveWithReport(people.values(), Person.class),
                repositoryHelper.saveWithReport(cities.values(), City.class),
                repositoryHelper.saveWithReport(conferences.values(), Conference.class),
                repositoryHelper.saveWithReport(editions.values(), Edition.class),
                repositoryHelper.saveWithReport(books.values(), Book.class),
                repositoryHelper.saveWithReport(bookBorrows.values(), BookBorrow.class),
                repositoryHelper.saveWithReport(projects.values(), Project.class),
                repositoryHelper.saveWithReport(projectParticipants.values(), ProjectParticipant.class),
                repositoryHelper.saveWithReport(publications.values(), Publication.class),
                repositoryHelper.saveWithReport(reports.values(), Report.class)
        ));
    }
}
