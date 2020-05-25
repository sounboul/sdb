package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.ConferenceEntity;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ConferenceGenerator implements Generator<ConferenceEntity> {
    private Faker faker;

    @Autowired
    public ConferenceGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public ConferenceEntity generate() {
        final ConferenceEntity conference = new ConferenceEntity();

        conference.setCityId(faker.number().randomDigit() + 1);
        conference.setName(faker.company().name());

        DateAndTime rootDate = faker.date();

        conference.setStartDate(
                new Date(rootDate.past(360 * 5, TimeUnit.DAYS).getTime()
        ));
        conference.setFinishDate(new Date(faker.date().between(
                conference.getStartDate(), faker.date().future(360 * 2, TimeUnit.DAYS)
                ).getTime()));
        return conference;
    }
}
