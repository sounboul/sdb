package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.PublicationEntity;

import java.util.concurrent.TimeUnit;

@Service
public class PublicationGenerator implements Generator<PublicationEntity> {
    private Faker faker;

    @Autowired
    public PublicationGenerator(Faker faker) {
        this.faker = faker;
    }
    @Override
    public PublicationEntity generate() {
        final PublicationEntity publication = new PublicationEntity();

        publication.setCitationIndex(faker.number().randomDouble(2, 0, 50));
        publication.setEditionId(faker.number().randomDigit() + 1);
        publication.setName(faker.book().title());
        publication.setPublicationDate(
                new java.sql.Date(faker.date().past(15 * 360, TimeUnit.DAYS).getTime())
        );

        return publication;
    }
}
