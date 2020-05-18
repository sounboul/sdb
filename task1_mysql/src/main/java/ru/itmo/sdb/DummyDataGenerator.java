package ru.itmo.sdb;

import org.springframework.stereotype.Service;
import ru.itmo.sdb.generators.DataGeneratorService;
import ru.itmo.sdb.generators.GeneratedData;
import ru.itmo.sdb.repositories.*;

@Service
public class DummyDataGenerator {
    private final DataGeneratorService dataGeneratorService;
    private final BookRepository bookRepository;
    private final BookBorrowRepository bookBorrowRepository;
    private final CityRepository cityRepository;
    private final ConferenceRepository conferenceRepository;
    private final EditionRepository editionRepository;
    private final PersonRepository personRepository;
    private final ProjectRepository projectRepository;
    private final ProjectParticipantRepository projectParticipantRepository;
    private final PublicationRepository publicationRepository;
    private final ReportRepository reportRepository;
    private final ReportAuthorRepository reportAuthorRepository;

    public DummyDataGenerator(DataGeneratorService dataGeneratorService, BookRepository bookRepository,
                              BookBorrowRepository bookBorrowRepository,
                              CityRepository cityRepository,
                              ConferenceRepository conferenceRepository, EditionRepository editionRepository,
                              PersonRepository personRepository,
                              ProjectRepository projectRepository,
                              ProjectParticipantRepository projectParticipantRepository,
                              PublicationRepository publicationRepository,
                              ReportRepository reportRepository,
                              ReportAuthorRepository reportAuthorRepository) {
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
