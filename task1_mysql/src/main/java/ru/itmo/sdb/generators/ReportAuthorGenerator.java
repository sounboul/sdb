package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.ReportAuthorsEntity;
import ru.itmo.sdb.models.ReportEntity;

import javax.persistence.Column;
import javax.persistence.Id;

@Service
public class ReportAuthorGenerator implements Generator<ReportAuthorsEntity> {
    private Faker faker;

    @Autowired
    public ReportAuthorGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public ReportAuthorsEntity generate() {
        final ReportAuthorsEntity reportAuthors = new ReportAuthorsEntity();

        reportAuthors.setPersonId(faker.number().randomDigit());
        reportAuthors.setReportId(faker.number().randomDigit());

        return reportAuthors;
    }
}
