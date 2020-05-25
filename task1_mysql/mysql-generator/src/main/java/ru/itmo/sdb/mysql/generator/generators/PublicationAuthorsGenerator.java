package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.PublicationAuthorsEntity;

@Service
public class PublicationAuthorsGenerator implements Generator<PublicationAuthorsEntity> {
    private Faker faker;

    @Autowired
    public PublicationAuthorsGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public PublicationAuthorsEntity generate() {
        PublicationAuthorsEntity publicationAuthors = new PublicationAuthorsEntity();

        publicationAuthors.setPersonId(faker.number().randomDigit() + 1);
        publicationAuthors.setPublicationId(faker.number().randomDigit() + 1);

        return publicationAuthors;
    }
}
