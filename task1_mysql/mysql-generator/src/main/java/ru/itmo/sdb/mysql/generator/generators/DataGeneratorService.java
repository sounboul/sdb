package ru.itmo.sdb.mysql.generator.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.BookBorrowEntity;
import ru.itmo.sdb.mysql.core.models.BookEntity;
import ru.itmo.sdb.mysql.core.models.CityEntity;
import ru.itmo.sdb.mysql.core.models.ConferenceEntity;
import ru.itmo.sdb.mysql.core.models.EditionEntity;
import ru.itmo.sdb.mysql.core.models.PersonEntity;
import ru.itmo.sdb.mysql.core.models.ProjectEntity;
import ru.itmo.sdb.mysql.core.models.ProjectParticipantEntity;
import ru.itmo.sdb.mysql.core.models.PublicationAuthorsEntity;
import ru.itmo.sdb.mysql.core.models.PublicationEntity;
import ru.itmo.sdb.mysql.core.models.ReportAuthorsEntity;
import ru.itmo.sdb.mysql.core.models.ReportEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DataGeneratorService {
    private BookBorrowGenerator bookBorrowGenerator;
    private BookGenerator bookGenerator;
    private CityGenerator cityGenerator;
    private ConferenceGenerator conferenceGenerator;
    private EditionGenerator editionGenerator;
    private PersonGenerator personGenerator;
    private ProjectGenerator projectGenerator;
    private ProjectParticipantGenerator projectParticipantGenerator;
    private PublicationAuthorsGenerator publicationAuthorsGenerator;
    private PublicationGenerator publicationGenerator;
    private ReportAuthorGenerator reportAuthorGenerator;
    private ReportGenerator reportGenerator;


    @Autowired
    public DataGeneratorService(BookBorrowGenerator bookBorrowGenerator,
                                BookGenerator bookGenerator,
                                CityGenerator cityGenerator,
                                ConferenceGenerator conferenceGenerator,
                                EditionGenerator editionGenerator,
                                ProjectGenerator projectGenerator,
                                ProjectParticipantGenerator projectParticipantGenerator,
                                PublicationAuthorsGenerator publicationAuthorsGenerator,
                                PublicationGenerator publicationGenerator,
                                ReportAuthorGenerator reportAuthorGenerator,
                                ReportGenerator reportGenerator,
                                PersonGenerator personGenerator) {
        this.bookBorrowGenerator = bookBorrowGenerator;
        this.bookGenerator = bookGenerator;
        this.cityGenerator = cityGenerator;
        this.conferenceGenerator = conferenceGenerator;
        this.editionGenerator = editionGenerator;
        this.projectGenerator = projectGenerator;
        this.projectParticipantGenerator = projectParticipantGenerator;
        this.publicationAuthorsGenerator = publicationAuthorsGenerator;
        this.publicationGenerator = publicationGenerator;
        this.reportAuthorGenerator = reportAuthorGenerator;
        this.reportGenerator = reportGenerator;
        this.personGenerator = personGenerator;
    }

    public GeneratedData generate(int defaultSize) {
        List<BookEntity> bookEntities = IntStream.rangeClosed(0, defaultSize)
                .boxed()
                .map(x -> bookGenerator.generate())
                .collect(Collectors.toList());
        List<BookBorrowEntity> bookBorrowEntities = IntStream.rangeClosed(0, defaultSize)
                .boxed()
                .map(x -> bookBorrowGenerator.generate())
                .collect(Collectors.toList());
        List<CityEntity> cities = IntStream.rangeClosed(0, defaultSize)
                .boxed()
                .map(x -> cityGenerator.generate())
                .collect(Collectors.toList());
        List<ConferenceEntity> conferences = IntStream.rangeClosed(1, defaultSize)
                .boxed()
                .map(x -> conferenceGenerator.generate())
                .collect(Collectors.toList());
        List<EditionEntity> editions = IntStream.rangeClosed(0, defaultSize)
                        .boxed()
                        .map(x -> editionGenerator.generate())
                .collect(Collectors.toList());
        List<PersonEntity> persons = IntStream.rangeClosed(0, defaultSize)
                        .boxed()
                        .map(x -> personGenerator.generate())
                .collect(Collectors.toList());
        List<ProjectEntity> projects = IntStream.rangeClosed(0, defaultSize)
                        .boxed()
                        .map(x -> projectGenerator.generate())
                .collect(Collectors.toList());
        List<ProjectParticipantEntity> projectsParticipants = IntStream.rangeClosed(1, defaultSize)
                        .boxed()
                        .map(x -> projectParticipantGenerator.generate())
                .collect(Collectors.toList());
        List<PublicationEntity> publications = IntStream.rangeClosed(0, defaultSize * 50)
                        .mapToObj(x -> publicationGenerator.generate())
                .collect(Collectors.toList());
        List<PublicationAuthorsEntity> publicationAuthors = IntStream.rangeClosed(1, defaultSize)
                        .mapToObj(x -> publicationAuthorsGenerator.generate())
                .collect(Collectors.toList());
        List<ReportEntity> reports = IntStream.rangeClosed(0, defaultSize)
                .mapToObj(x -> reportGenerator.generate())
                .collect(Collectors.toList());
        List<ReportAuthorsEntity> reportAuthors = IntStream.rangeClosed(1, defaultSize)
                .mapToObj(x -> reportAuthorGenerator.generate())
                .collect(Collectors.toList());


        return new GeneratedData(
                bookBorrowEntities,
                bookEntities,
                cities,
                conferences,
                editions,
                persons,
                projects,
                projectsParticipants,
                publicationAuthors,
                publications,
                reports,
                reportAuthors
                );
    }
}
