package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.CityEntity;

@Service
public class CityGenerator implements Generator<CityEntity> {
    private Faker faker;

    @Autowired
    public CityGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public CityEntity generate() {
        final CityEntity city = new CityEntity();

        city.setName(faker.lordOfTheRings().location());

        return city;
    }
}
