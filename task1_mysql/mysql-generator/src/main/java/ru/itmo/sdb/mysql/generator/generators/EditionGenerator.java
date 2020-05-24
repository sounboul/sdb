package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.EditionEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EditionGenerator implements Generator<EditionEntity> {
    private final List<String>  languages = Arrays.asList("русский", "английский");
    private final List<String>  editionTypes = Arrays.asList("ВАК", "РИНЦ");
    private Faker faker;

    @Autowired
    public EditionGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public EditionEntity generate() {
        final EditionEntity edition = new EditionEntity();

        edition.setCityId(faker.number().randomDigit());
        edition.setLanguage(languages.get(new Random().nextInt(languages.size())));
        edition.setYear(faker.date().between(
                faker.date().past(360 * 10, TimeUnit.DAYS),
                faker.date().future(360 * 2, TimeUnit.DAYS)
        ).getYear());
        edition.setName(faker.gameOfThrones().house());
        edition.setType(editionTypes.get(new Random().nextInt(editionTypes.size())));
        edition.setVolume(faker.number().numberBetween(10, 500));

        return edition;
    }
}
