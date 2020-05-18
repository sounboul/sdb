package ru.itmo.sdb.generators;

import ru.itmo.sdb.models.*;

import java.util.List;

public class GeneratedData {
    private final List<BookBorrowEntity> bookBorrows;
    private final List<BookEntity> books;
    private final List<CityEntity> cities;
    private final List<ConferenceEntity> conferences;
    private final List<EditionEntity> editions;
    private final List<PersonEntity> persons;
    private final List<ProjectEntity> projects;
    private final List<ProjectParticipantEntity> projectParticipants;
    private final List<PublicationEntity> publications;
    private final List<PublicationAuthorsEntity> publicationAuthors;
    private final List<ReportEntity> reports;
    private final List<ReportAuthorsEntity> reportAuthors;

    public List<BookBorrowEntity> getBookBorrows() {
        return bookBorrows;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public List<CityEntity> getCities() {
        return cities;
    }

    public List<ConferenceEntity> getConferences() {
        return conferences;
    }

    public List<EditionEntity> getEditions() {
        return editions;
    }

    public List<PersonEntity> getPersons() {
        return persons;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public List<ProjectParticipantEntity> getProjectParticipants() {
        return projectParticipants;
    }

    public List<PublicationEntity> getPublications() {
        return publications;
    }

    public List<PublicationAuthorsEntity> getPublicationAuthors() {
        return publicationAuthors;
    }

    public List<ReportEntity> getReports() {
        return reports;
    }

    public List<ReportAuthorsEntity> getReportAuthors() {
        return reportAuthors;
    }

    public GeneratedData(List<BookBorrowEntity> bookBorrows,
                         List<BookEntity> books,
                         List<CityEntity> cities,
                         List<ConferenceEntity> conferences,
                         List<EditionEntity> editions,
                         List<PersonEntity> persons,
                         List<ProjectEntity> projects,
                         List<ProjectParticipantEntity> projectParticipants,
                         List<PublicationAuthorsEntity> publicationAuthors,
                         List<PublicationEntity> publications,
                         List<ReportEntity> reports,
                         List<ReportAuthorsEntity> reportAuthors) {
        this.bookBorrows = bookBorrows;
        this.books = books;
        this.cities = cities;
        this.conferences = conferences;
        this.editions = editions;
        this.persons = persons;
        this.projects = projects;
        this.projectParticipants = projectParticipants;
        this.publicationAuthors = publicationAuthors;
        this.publications = publications;
        this.reports = reports;
        this.reportAuthors = reportAuthors;
    }
}
