package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.CityEntity;

import java.util.Arrays;

@Service
public class CityGenerator implements Generator<CityEntity> {
    private Faker faker;
    private RandomHelper randomHelper;

    @Autowired
    public CityGenerator(Faker faker, RandomHelper randomHelper) {
        this.faker = faker;
        this.randomHelper = randomHelper;
    }

    @Override
    public CityEntity generate() {
        final CityEntity city = new CityEntity();

        city.setName(randomHelper.randomFrom(Arrays.asList(
                "San Jose",
                "New York",
                "Boston",
                "Moscow",
                "London",
                "Kemerovo",
                "Tashkent"
        )));

        return city;
    }
}
