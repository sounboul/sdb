package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.ReportEntity;

@Service
public class ReportGenerator implements Generator<ReportEntity>{
    private Faker faker;

    @Autowired
    public ReportGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public ReportEntity generate() {
        final ReportEntity report = new ReportEntity();

        report.setConferenceId(faker.number().randomDigit());
        report.setName(faker.ancient().hero());

        return report;
    }
}
