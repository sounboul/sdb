package ru.itmo.sdb.mysql.generator;

import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.repositories.*;
import ru.itmo.sdb.mysql.generator.generators.DataGeneratorService;
import ru.itmo.sdb.mysql.generator.generators.GeneratedData;

@Service
public class DummyDataGenerator {
    private final DataGeneratorService dataGeneratorService;
    private final MySQLBookRepository bookRepository;
    private final MySQLBookBorrowRepository bookBorrowRepository;
    private final MySQLCityRepository cityRepository;
    private final MySQLConferenceRepository conferenceRepository;
    private final MySQLEditionRepository editionRepository;
    private final MySQLPersonRepository personRepository;
    private final MySQLProjectRepository projectRepository;
    private final MySQLProjectParticipantRepository projectParticipantRepository;
    private final MySQLPublicationRepository publicationRepository;
    private final MySQLReportRepository reportRepository;
    private final MySQLReportAuthorRepository reportAuthorRepository;

    public DummyDataGenerator(DataGeneratorService dataGeneratorService, MySQLBookRepository bookRepository,
                              MySQLBookBorrowRepository bookBorrowRepository,
                              MySQLCityRepository cityRepository,
                              MySQLConferenceRepository conferenceRepository, MySQLEditionRepository editionRepository,
                              MySQLPersonRepository personRepository,
                              MySQLProjectRepository projectRepository,
                              MySQLProjectParticipantRepository projectParticipantRepository,
                              MySQLPublicationRepository publicationRepository,
                              MySQLReportRepository reportRepository,
                              MySQLReportAuthorRepository reportAuthorRepository) {
        this.dataGeneratorService = dataGeneratorService;
        this.bookRepository = bookRepository;
        this.bookBorrowRepository = bookBorrowRepository;
        this.cityRepository = cityRepository;
        this.conferenceRepository = conferenceRepository;
        this.editionRepository = editionRepository;
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
        this.projectParticipantRepository = projectParticipantRepository;
        this.publicationRepository = publicationRepository;
        this.reportRepository = reportRepository;
        this.reportAuthorRepository = reportAuthorRepository;
    }

    public void generateAndSave() {
        final GeneratedData data = dataGeneratorService.generate(10);
        bookRepository.saveAll(data.getBooks());
        bookBorrowRepository.saveAll(data.getBookBorrows());
        cityRepository.saveAll(data.getCities());
        conferenceRepository.saveAll(data.getConferences());
        editionRepository.saveAll(data.getEditions());
        personRepository.saveAll(data.getPersons());
        publicationRepository.saveAll(data.getPublications());
        projectRepository.saveAll(data.getProjects());
        projectParticipantRepository.saveAll(data.getProjectParticipants());
        reportRepository.saveAll(data.getReports());
        reportAuthorRepository.saveAll(data.getReportAuthors());
    }


}
